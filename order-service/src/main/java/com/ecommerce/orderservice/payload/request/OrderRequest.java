package com.ecommerce.orderservice.payload.request;

import com.ecommerce.orderservice.entity.Item;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotBlank(message="Order name can't be empty")
    @Size(min=2)
    private String name;

    @Builder.Default
    private String status = "PENDING";

    @NotBlank(message = "Customer email can't be empty")
    @Email(message = "Invalid customer email format")
    private String customerEmail;
}
