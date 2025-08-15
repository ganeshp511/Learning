package com.example.ecommerce.controller;
import com.example.ecommerce.dto.CreateResponseDto;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for seller-related operations.
 * Provides endpoints for sellers to interact with products.
 */
@RestController
@RequestMapping("/api/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    /**
     * Endpoint to create a new product.
     * @param productDTO Product data from the request body
     * @return ResponseEntity with creation result
     */
    @PostMapping("/product")
    public ResponseEntity<CreateResponseDto> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(sellerService.createProduct(productDTO));
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(sellerService.getProducts());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(sellerService.updateProduct(id,productDTO));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(sellerService.deleteProduct(id));
    }

//    @PutMapping("/order/{id}/accept")
//    public ResponseEntity<ResponseDTO>  submitOrder(@PathVariable Long id){
//
//    }
}
