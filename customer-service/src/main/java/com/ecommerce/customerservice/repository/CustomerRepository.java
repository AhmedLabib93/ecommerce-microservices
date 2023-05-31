package com.ecommerce.customerservice.repository;

import com.ecommerce.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByEmail(String email);
}
