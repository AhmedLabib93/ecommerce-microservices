package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.payload.request.OrderRequest;
import com.ecommerce.orderservice.payload.response.ApiResponse;
import com.ecommerce.orderservice.payload.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderService {

    OrderResponse saveOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(long id);

    List<OrderResponse> getAllOrders();

    List<OrderResponse> getAllOrdersByCustomerEmail(String email);

    void deleteOrdersByCustomerEmail(String email);

    OrderResponse updateOrder(OrderRequest orderRequest, long id);

    OrderResponse addItemToOrder(long orderId, long itemId);

    OrderResponse removeItemFromOrder(long orderId, long itemId);

    void deleteOrder(long id);
}
