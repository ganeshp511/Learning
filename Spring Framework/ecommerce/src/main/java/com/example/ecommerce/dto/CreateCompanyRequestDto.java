package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


public class CreateCompanyRequestDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
