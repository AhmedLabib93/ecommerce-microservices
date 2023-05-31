package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.payload.request.CustomerRequest;
import com.ecommerce.customerservice.payload.response.CustomerResponse;
import com.ecommerce.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customer) {
		return new ResponseEntity<CustomerResponse>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CustomerResponse>> getCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{customer-email}")
	public ResponseEntity<CustomerResponse> getCustomerByEmail(@PathVariable(name = "customer-email") String email) {
		return ResponseEntity.ok(customerService.getCustomerByEmail(email));
	}

	@PutMapping("/{customer-id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest,
														   @PathVariable(name = "customer-id") long id) {
		return new ResponseEntity<CustomerResponse>(
				customerService.updateCustomer(customerRequest, id),
				HttpStatus.CREATED);

	}

	@DeleteMapping("/{customer-id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable(name = "customer-id") long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.ok("Customer removed successfully.");
	}

}
