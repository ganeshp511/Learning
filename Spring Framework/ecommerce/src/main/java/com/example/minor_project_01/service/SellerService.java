package com.example.minor_project_01.service;

import com.example.minor_project_01.dto.CreateResponseDTO;
import com.example.minor_project_01.dto.ProductDTO;
import com.example.minor_project_01.dto.ResponseDTO;
import com.example.minor_project_01.entity.Category;
import com.example.minor_project_01.entity.Company;
import com.example.minor_project_01.entity.Product;
import com.example.minor_project_01.exception.NotFoundException;
import com.example.minor_project_01.repo.CategoryRepo;
import com.example.minor_project_01.repo.CompanyRepo;
import com.example.minor_project_01.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {


    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    public CreateResponseDTO createProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        product.setActive(productDTO.getActive());
        product.setImageUrl(productDTO.getImageUrl());

        Company company = companyRepo.findById(productDTO.getCompanyId()).get();
        product.setCompany(company);

        Category category = categoryRepo.findById(productDTO.getCategoryId()).get();
        product.setCategory(category);
        product = productRepo.save(product);
        CreateResponseDTO createResponseDTO = new CreateResponseDTO();
        createResponseDTO.setMessage("Product created successfully");
        createResponseDTO.setId(product.getId());
        return createResponseDTO;
    }

    public List<ProductDTO> getProducts(){
        List<Product> products = productRepo.findAll();
        List<ProductDTO> result = new ArrayList<>();
        for(Product product: products){
            ProductDTO productDTO = ProductDTO.buildDTOFromProduct(product);
            result.add(productDTO);
        }
        return result;
    }

    @Transactional
    public ResponseDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepo.findById(id).get();
        if(product == null){

        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        product.setActive(productDTO.getActive());
        product.setImageUrl(product.getImageUrl());
        //productRepo.save(product);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg("Updated Product");
        responseDTO.setStatusCode("123-U");
        return responseDTO;
    }


    public ResponseDTO deleteProduct(Long id) throws NotFoundException {
        Product product = productRepo.findById(id).orElseThrow(()-> new NotFoundException("Product Id is worng"));
        productRepo.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMsg("Product Deleted");
        responseDTO.setStatusCode("123-D");
        return responseDTO;
    }

}
