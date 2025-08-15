package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateCompanyRequestDto;
import com.example.ecommerce.dto.CreateCompanyResponseDto;
import com.example.ecommerce.dto.SellerDTO;
import com.example.ecommerce.entity.Company;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repo.CompanyRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Transactional
public class AdminService {
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    EntityManager entityManager;

    public CreateCompanyResponseDto createCompany(CreateCompanyRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setActive(true);
        entityManager.persist(company);
        CreateCompanyResponseDto createCompanyResponseDto = new CreateCompanyResponseDto();
        createCompanyResponseDto.setId(company.getId());
        return createCompanyResponseDto;
    }

    @Transactional
    public CreateCompanyResponseDto createSeller(SellerDTO sellerDTO){
        Company company = companyRepo.findById(sellerDTO.getCompanyId()).get();
        if(company == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Compnay Does not exist");
        }
        User seller = new User();
        seller.setName(sellerDTO.getName());
        seller.setCompany(company);
        seller.setEmail(sellerDTO.getEmail());
        seller.setRole(Role.SELLER);
        entityManager.persist(seller);
        CreateCompanyResponseDto response =  new CreateCompanyResponseDto();
        response.setId(seller.getId());
        return response;
    }
}
