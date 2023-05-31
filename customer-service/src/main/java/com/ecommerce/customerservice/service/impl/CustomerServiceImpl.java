package com.ecommerce.customerservice.service.impl;

import com.ecommerce.customerservice.exception.ResourceNotFound;
import com.ecommerce.customerservice.payload.request.CustomerRequest;
import com.ecommerce.customerservice.payload.response.CustomerResponse;
import com.ecommerce.customerservice.repository.CustomerRepository;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer saved = customerRepository.save(modelMapper.map(customerRequest, Customer.class));
        return modelMapper.map(saved, CustomerResponse.class);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest, long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Customer not found with id " + id));

        customer.setId(id);
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());

        Customer updated = customerRepository.save(customer);
        return modelMapper.map(updated, CustomerResponse.class);
    }

    @Override
    public void deleteCustomer(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Customer not found with id " + id));
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()
                -> new ResourceNotFound("Customer not found with email " + email));


        return modelMapper.map(customer, CustomerResponse.class);
    }


    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map((customer)
                -> modelMapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
    }

}
