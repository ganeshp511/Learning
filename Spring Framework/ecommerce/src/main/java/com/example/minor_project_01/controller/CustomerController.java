package com.example.minor_project_01.controller;


import com.example.minor_project_01.dto.AddToOrderDto;
import com.example.minor_project_01.dto.OrderDetailDto;
import com.example.minor_project_01.dto.ProductDTO;
import com.example.minor_project_01.dto.ResponseDTO;
import com.example.minor_project_01.exception.NotFoundException;
import com.example.minor_project_01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;



    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProductsByKeyword(@RequestParam String keyword, @RequestParam Integer pageSize, @RequestParam Integer pageNo){
        Pageable pageable = Pageable.ofSize(pageSize)
                .withPage(pageNo);
        return ResponseEntity.ok(customerService.getProductByKeyword(keyword,pageable));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getProduct(id));
    }

    @PostMapping("/order-item")
    public ResponseEntity<OrderDetailDto> addToOrder(@RequestBody AddToOrderDto addToOrderDto) throws NotFoundException {
        OrderDetailDto response = customerService.addToOrder(addToOrderDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/order/{id}/submit")
    public ResponseEntity<ResponseDTO>  submitOrder(@PathVariable Long id){
        return ResponseEntity.ok(customerService.submitOrder(id));
    }

//    @GetMapping("/orders")
//    public ResponseEntity<List<OrderDetailDto>> getAllOrders(){
//
//    }

}
