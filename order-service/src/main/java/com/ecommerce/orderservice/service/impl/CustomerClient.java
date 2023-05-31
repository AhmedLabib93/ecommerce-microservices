package com.ecommerce.orderservice.service.impl;


import com.ecommerce.orderservice.payload.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url="http://localhost:8080",value="CUSTOMER-SERVICE")
public interface CustomerClient {

    @GetMapping("/ecommerce/v1/customers/{customer-email}")
    public Optional<Customer> getCustomerByEmail(@PathVariable(name = "customer-email") String email);
}
