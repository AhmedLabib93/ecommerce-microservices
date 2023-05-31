package com.ecommerce.orderservice.payload.response;

import com.ecommerce.orderservice.entity.Item;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;
    private String name;
    private double price;
    private String status;
    private List<Item> items;
    private String customerEmail;

}
