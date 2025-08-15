package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity class representing a Product in the ecommerce system.
 * Maps to the 'products' table in the database.
 */
@Entity
@Table(name = "products")
@Data
@Getter
@Setter
public class Product {
    /** Primary key for Product */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Product name */
    private String name;

    /** Product description */
    private String description;

    /** Product price */
    private Double price;

    /** Available stock */
    private Integer stock;

    /** Product active status */
    private Boolean active;

    /** Image URL for the product */
    private String imageURL;

    /** Timestamp for product creation */
    private LocalDateTime createdTime;

    /** Timestamp for last update */
    private LocalDateTime updatedTime;

    /** Associated company */
    @ManyToOne
    private Company company;

    /** Associated category */
    @ManyToOne
    private Category category;

    /**
     * Gets the product ID.
     * @return product ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the product ID.
     * @param id product ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the product name.
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     * @param name product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product description.
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     * @param description product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product price.
     * @return product price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     * @param price product price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the available stock.
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Sets the available stock.
     * @param stock product stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Gets the active status.
     * @return active status
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets the active status.
     * @param active product status
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Gets the image URL.
     * @return image URL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the image URL.
     * @param imageURL product image URL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Gets the creation timestamp.
     * @return created time
     */
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the creation timestamp.
     * @param createdTime creation time
     */
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Gets the update timestamp.
     * @return updated time
     */
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets the update timestamp.
     * @param updatedTime update time
     */
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * Gets the associated company.
     * @return company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Sets the associated company.
     * @param company company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Gets the associated category.
     * @return category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the associated category.
     * @param category category
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
