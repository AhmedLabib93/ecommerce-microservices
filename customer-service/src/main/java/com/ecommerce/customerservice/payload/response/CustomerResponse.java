package com.ecommerce.customerservice.payload.response;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private long id;
	private String firstName;
	private String lastName;

	@Email
	private String email;
}
