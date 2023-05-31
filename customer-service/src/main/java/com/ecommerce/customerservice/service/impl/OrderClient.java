package com.ecommerce.customerservice.service.impl;

import com.ecommerce.customerservice.payload.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url="http://localhost:8081",value="ORDER-SERVICE")
public interface OrderClient {

    @GetMapping("/ecommerce/v1/orders/{customer-email}")
    public List<Order> getOrdersByCustomerEmail(@PathVariable(name = "customer-email") String email);

    @DeleteMapping("/ecommerce/v1/orders/{customer-email}")
    public ResponseEntity<String> deleteOrdersByCustomerEmail(@PathVariable(name = "customer-email") String email);
}

