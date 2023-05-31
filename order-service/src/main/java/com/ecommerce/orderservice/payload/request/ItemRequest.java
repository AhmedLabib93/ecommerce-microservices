package com.ecommerce.orderservice.payload.request;

import jakarta.validation.constraints.DecimalMin;
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
public class ItemRequest {

    @NotBlank(message = "Item name can't be empty")
    @Size(min=2, message = "Item name must be 2 characters at least")
    private String name;

    @DecimalMin("1.0")
    private double price;

}
