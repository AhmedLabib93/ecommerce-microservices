package com.ecommerce.orderservice.exception;

import org.springframework.http.HttpStatus;

public class ECommerceException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public ECommerceException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ECommerceException(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }
}
