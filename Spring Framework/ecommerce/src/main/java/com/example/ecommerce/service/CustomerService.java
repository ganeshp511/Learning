package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repo.OrderRepo;
import com.example.ecommerce.repo.ProductRepo;
import com.example.ecommerce.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    public List<ProductDTO> getProductByKeyword(String keyword, Pageable pageable){
        List<Product> productList = productRepo.findByNameContaining(keyword,pageable);
        List<ProductDTO> result = new ArrayList<>();
        for(Product product: productList){
            ProductDTO productDTO = ProductDTO.buildDTOFromProduct(product);
            result.add(productDTO);
        }
        return result;
    }

    public ProductDTO getProduct(Long id){
        Product product = productRepo.findById(id).get();
        ProductDTO productDTO = ProductDTO.buildDTOFromProduct(product);
        return productDTO;
    }

    @Transactional
    public OrderDetailDTO addToOrder(AddToOrderDto addToOrderDto) throws NotFoundException {
        Product product = productRepo.findById(addToOrderDto.getProductId()).orElseThrow(()->  new NotFoundException("Product Does not exist"));


        User user = userRepo.findById(addToOrderDto.getUserId()).get();
        List<Order> orderList = orderRepo.findByStatusAndUser(OrderStatus.DRAFT,user);
        Order existingOrder;
        if(!orderList.isEmpty()){
            existingOrder = orderList.get(0);
        }
        else {
            existingOrder = Order.builder()
                    .status(OrderStatus.DRAFT)
                    .totalAmount(0.0)
                    .user(user)
                    .orderItems(new ArrayList<>())
                    .build();
        }
        OrderItem orderItem = OrderItem.builder()
                .order(existingOrder)
                .price(product.getPrice())
                .quantity(addToOrderDto.getQuantity())
                .product(product)
                .build();
        existingOrder.getOrderItems().add(orderItem);
        if(product.getStock() < orderItem.getQuantity()){
            //
        }
        //Pricing
        double totalItemsPrice = product.getPrice() * orderItem.getQuantity();
        existingOrder.setTotalAmount(existingOrder.getTotalAmount() + totalItemsPrice);
        existingOrder = orderRepo.save(existingOrder);
        product.setStock(product.getStock()-orderItem.getQuantity());

        OrderDetailDTO OrderDetailDTO = new OrderDetailDTO();
        OrderDetailDTO.setOrderId(existingOrder.getId());
        OrderDetailDTO.setOrderTotalPrice(existingOrder.getTotalAmount());
        List<OrderItemDTO> OrderItemDTOList = new ArrayList<>();
        for(OrderItem orderItem1 : existingOrder.getOrderItems()){
            OrderItemDTOList.add(OrderItemDTO.mapOrderItemToDto(orderItem1));
        }
        OrderDetailDTO.setOrderItems(OrderItemDTOList);
        return OrderDetailDTO;
    }

    public ResponseDTO submitOrder(Long orderId){
        Order order = orderRepo.findById(orderId).get();
        ResponseDTO responseDTO = new ResponseDTO();
        if(order.getOrderStatus().equals(OrderStatus.DRAFT)){
            order.setOrdertatus(OrderStatus.PLACED);
            orderRepo.save(order);
            responseDTO.setMsg("Submitted the Order");
            responseDTO.setStatusCode("123-OS");
            /*
            Trigger Email to Seller.
            Seller Accept Order with Accept API.
             */
        }
        else {
            responseDTO.setMsg("Failed: The Order is not in DRAFT state");
            responseDTO.setStatusCode("145-OS");
        }
        return responseDTO;
    }

}
