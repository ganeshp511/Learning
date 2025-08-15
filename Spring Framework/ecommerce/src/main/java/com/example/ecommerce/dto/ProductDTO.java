package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for Product entity. Used for transferring product data between client and server.
 */
@Setter
@Getter
public class ProductDTO {
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
    /** Associated company ID */
    private Long companyID;
    /** Associated category ID */
    private Long categoryID;

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
     * Gets the company ID.
     * @return company ID
     */
    public Long getCompanyID() {
        return companyID;
    }

    /**
     * Sets the company ID.
     * @param companyID company ID
     */
    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    /**
     * Gets the category ID.
     * @return category ID
     */
    public Long getCategoryID() {
        return categoryID;
    }

    /**
     * Sets the category ID.
     * @param categoryID category ID
     */
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}
