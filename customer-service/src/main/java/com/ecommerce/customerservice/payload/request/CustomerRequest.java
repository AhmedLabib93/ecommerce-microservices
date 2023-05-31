package com.ecommerce.customerservice.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

	@NotBlank(message = "Customer first name can't be empty")
	@Size(min=2, message = "First name must be 2 characters at least")
	private String firstName;

	@NotBlank(message = "Customer last name can't be empty")
	@Size(min=2, message = "Last name must be 2 characters at least")
	private String lastName;

	@NotBlank(message = "Customer email can't be empty")
	@Email(message = "Invalid customer email format")
	private String email;
}
