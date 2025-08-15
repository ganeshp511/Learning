package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CreateCompanyResponseDto;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CreateCompanyResponseDto> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(sellerService.createProduct(productDTO));
    }
}
