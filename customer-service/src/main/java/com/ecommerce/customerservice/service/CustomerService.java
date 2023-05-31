package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.payload.request.CustomerRequest;
import com.ecommerce.customerservice.payload.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse saveCustomer(CustomerRequest customerDto);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerByEmail(String email);

    CustomerResponse updateCustomer(CustomerRequest customerDto, long id);

    void deleteCustomer(long id);
}
