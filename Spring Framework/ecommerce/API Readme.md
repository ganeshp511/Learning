# API Documentation

This document describes the REST API endpoints available in the ecommerce Spring Boot project.

---

## Admin APIs

### Create Company
- **URL:** `/api/admin/company`
- **Method:** POST
- **Request Body:** `CreateCompanyRequestDto`
- **Response:** `CreateCompanyResponseDto`
- **Description:** Create a new company.

### Create Seller
- **URL:** `/api/admin/seller`
- **Method:** POST
- **Request Body:** `SellerDTO`
- **Response:** `CreateCompanyResponseDto`
- **Description:** Create a new seller.

---

## Seller APIs

### Create Product
- **URL:** `/api/seller/product`
- **Method:** POST
- **Request Body:** `ProductDTO`
- **Response:** `CreateCompanyResponseDto`
- **Description:** Create a new product.

### Add a Product
POST /api/seller/products
Request body: Product details (e.g., name, description, price, stock, category)
Response: Product ID or success message

### View All Products by Seller
GET /api/seller/products
Response: List of all products added by the seller
### Update Product Details
PUT /api/seller/products/{productId}
Request body: Fields to update (e.g., price, stock)
Response: Success or error message

### Delete a Product
DELETE /api/seller/products/{productId}
Response: Success or error message
---

### Customer APIs

Browse Products
GET /api/products?keyword=laptop
Response: List of available products

View Product Details
GET /api/products/{productId}
Response: Product details

Add Order Item
POST /api/customers/order-item
Response: Orderld

View Order History
GET /api/customers/orders
Response: List of previous orders

## Notes
- All endpoints expect and return JSON.
- Authentication and authorization details are not covered in this document.
- DTOs (`CreateCompanyRequestDto`, `SellerDTO`, `ProductDTO`, `CreateCompanyResponseDto`) are defined in the `com.example.ecommerce.dto` package.