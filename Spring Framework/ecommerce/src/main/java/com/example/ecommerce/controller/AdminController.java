package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CreateRequestDto;
import com.example.ecommerce.dto.CreateResponseDto;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.dto.SellerDTO;
import com.example.ecommerce.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    public static Logger LOGGER= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("/company")
    public ResponseEntity<CreateResponseDto> createCompany(@RequestBody CreateRequestDto companyRequest){
        LOGGER.info("Creating company");
        return ResponseEntity.ok(adminService.createCompany(companyRequest));
    }

    @PostMapping("/seller")
    public ResponseEntity<CreateResponseDto> createSeller(@RequestBody SellerDTO sellerRequest){
        return ResponseEntity.ok(adminService.createSeller(sellerRequest));
    }

    @GetMapping("/seller")
    public ResponseEntity<List<SellerDTO>> getAllSellers(){
        LOGGER.info("Getting all sellers");
        return ResponseEntity.ok(adminService.getAllSellers());
    }

    @DeleteMapping("/seller/{id}")
    public ResponseEntity<ResponseDTO> deleteSeller(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(adminService.deleteSeller(id));
    }



}
