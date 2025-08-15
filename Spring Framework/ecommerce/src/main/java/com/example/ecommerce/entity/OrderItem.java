package com.example.ecommerce.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;
    @ManyToOne
    private Product product;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private Order order;
}
