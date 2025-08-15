package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;


public class CreateCompanyResponseDto {
    private Long id;
    private String errorCode;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
