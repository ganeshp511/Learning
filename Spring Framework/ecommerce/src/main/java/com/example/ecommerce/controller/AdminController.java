package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CreateCompanyRequestDto;
import com.example.ecommerce.dto.CreateCompanyResponseDto;
import com.example.ecommerce.dto.SellerDTO;
import com.example.ecommerce.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    public static Logger LOGGER= LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @PostMapping("/company")
    public ResponseEntity<CreateCompanyResponseDto> createCompany(@RequestBody CreateCompanyRequestDto companyRequest){
        LOGGER.info("Creating company");
        return ResponseEntity.ok(adminService.createCompany(companyRequest));
    }

    @PostMapping("/seller")
    public ResponseEntity<CreateCompanyResponseDto> createSeller(@RequestBody SellerDTO sellerRequest){
        return ResponseEntity.ok(adminService.createSeller(sellerRequest));
    }



}
