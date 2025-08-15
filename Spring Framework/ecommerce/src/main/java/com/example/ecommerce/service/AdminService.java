package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateRequestDto;
import com.example.ecommerce.dto.CreateResponseDto;
import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.dto.SellerDTO;
import com.example.ecommerce.entity.Company;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repo.CompanyRepo;
import com.example.ecommerce.repo.UserRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private UserRepo userRepo;


    public CreateResponseDto createCompany(CreateRequestDto companyRequestDto) {
        Company company = new Company();
        company.setName(companyRequestDto.getName());
        company.setActive(true);
        entityManager.persist(company);
        CreateResponseDto createCompanyResponseDto = new CreateResponseDto();
        createCompanyResponseDto.setId(company.getId());
        return createCompanyResponseDto;
    }

    @Transactional
    public CreateResponseDto createSeller(SellerDTO sellerDTO){
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
        CreateResponseDto response =  new CreateResponseDto();
        response.setId(seller.getId());
        return response;
    }
    public List<SellerDTO> getAllSellers(){
        List<User> userList = userRepo.findByRole(Role.SELLER);
        List<SellerDTO> result = new ArrayList<>();
        for(User user: userList){
            SellerDTO sellerDTO = SellerDTO.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .companyId(user.getCompany().getId())
                    .build();
            result.add(sellerDTO);
        }
        return result;
    }

    public ResponseDTO deleteSeller(Long id) throws NotFoundException {
        User user = userRepo.findById(id).orElseThrow(()->new NotFoundException("Seller Does not exist. Please correct sellerId"));
        userRepo.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg("Seller Deleted");
        responseDTO.setStatusCode("123-D");
        return responseDTO;
    }

}
