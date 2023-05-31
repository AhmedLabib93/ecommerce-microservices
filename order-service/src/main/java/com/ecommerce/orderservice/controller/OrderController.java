package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.payload.request.OrderRequest;
import com.ecommerce.orderservice.payload.response.OrderResponse;
import com.ecommerce.orderservice.publisher.OrderProducer;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecommerce/v1/orders")
public class OrderController {

    private OrderProducer orderProducer;
    private OrderService orderService;

    public OrderController(OrderProducer orderProducer, OrderService orderService) {
        this.orderProducer = orderProducer;
        this.orderService = orderService;
    }

    /*
    @PostMapping("/cart/{order-id}")
    public ResponseEntity<String> replaceOrder(@RequestParam long id) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order s in pending status");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

        return ResponseEntity.ok("Order placed...");
    }
     */


    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        ResponseEntity<OrderResponse> response = new ResponseEntity<OrderResponse>(
                orderService.saveOrder(orderRequest), HttpStatus.CREATED);
        if (response.hasBody())
            orderProducer.sendMessage(response.getBody());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/customer/{customer-email}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByCustomerEmail(
            @PathVariable(name = "customer-email") String email) {
        return ResponseEntity.ok(orderService.getAllOrdersByCustomerEmail(email));
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable(name = "order-id") long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{order-id}")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody OrderRequest orderRequest,
                                                     @PathVariable(name = "order-id") long id) {
        return new ResponseEntity<OrderResponse>(
                orderService.updateOrder(orderRequest, id),
                HttpStatus.CREATED);
    }


    @PutMapping("/{order-id}/add/{item-id}")
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable(name = "order-id") long orderId,
                                                        @PathVariable(name = "item-id") long itemId) {
        return new ResponseEntity<OrderResponse>(
                orderService.addItemToOrder(orderId, itemId),
                HttpStatus.CREATED);
    }

    @PutMapping("/{order-id}/remove/{item-id}")
    public ResponseEntity<OrderResponse> removeItemFromOrder(@PathVariable(name = "order-id") long orderId,
                                                             @PathVariable(name = "item-id") long itemId) {
        return new ResponseEntity<OrderResponse>(
                orderService.removeItemFromOrder(orderId, itemId),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "order-id") long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order removed successfully.");
    }

    @DeleteMapping("/customer/{customer-email}")
    public ResponseEntity<String> deleteOrdersByCustomerEmail(@PathVariable(name = "customer-email") String email) {
        orderService.deleteOrdersByCustomerEmail(email);
        return ResponseEntity.ok("Orders associated with customer email " + email + " deleted.");
    }
}
