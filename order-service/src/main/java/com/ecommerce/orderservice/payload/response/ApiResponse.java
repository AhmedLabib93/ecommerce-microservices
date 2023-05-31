package com.ecommerce.orderservice.payload.response;

import com.ecommerce.orderservice.payload.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private Customer customer;
    private OrderResponse orderResponse;

}
