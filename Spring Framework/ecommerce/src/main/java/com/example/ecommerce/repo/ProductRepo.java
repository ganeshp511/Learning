package com.example.ecommerce.repo;

import com.example.ecommerce.entity.Company;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCompany(Company company);

    // Select from Product where name like '%keyword%'
    List<Product> findByNameContaining(String name, Pageable pageable);
}
