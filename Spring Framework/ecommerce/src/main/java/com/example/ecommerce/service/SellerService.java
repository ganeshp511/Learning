package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateCompanyResponseDto;
import com.example.ecommerce.dto.CreateResponseDto;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Company;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repo.CategoryRepo;
import com.example.ecommerce.repo.CompanyRepo;
import com.example.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for seller operations.
 * Handles business logic for product creation and related seller actions.
 */
@Service
public class SellerService {
    /** Repository for Company entities */
    @Autowired
    private CompanyRepo companyRepo;
    /** Repository for Category entities */
    @Autowired
    private CategoryRepo categoryRepo;
    /** Repository for Product entities */
    @Autowired
    private ProductRepo productRepo;

    /**
     * Creates a new product based on the provided ProductDTO.
     * Sets company and category associations, then saves the product.
     * @param productDTO Data for the product to be created
     * @return Response DTO with creation status and product ID
     */
    public CreateResponseDto createProduct(ProductDTO productDTO){
        Product product=new Product();
        // Set product fields from DTO
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        product.setActive(productDTO.getActive());
        product.setImageURL(productDTO.getImageURL());

        // Set company association
        Company company = companyRepo.findById(productDTO.getCompanyID()).get();
        product.setCompany(company);

        // Set category association
        Category category = categoryRepo.findById(productDTO.getCategoryID()).get();
        product.setCategory(category);
        product = productRepo.save(product);

        // Prepare response
        CreateResponseDto createResponseDto=new CreateResponseDto();
        createResponseDto.setMessage("Product created successfully");
        createResponseDto.setId(product.getId());
        return createResponseDto;
    }
}
