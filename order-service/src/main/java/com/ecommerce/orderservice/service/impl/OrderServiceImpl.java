package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.entity.Item;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.ECommerceException;
import com.ecommerce.orderservice.exception.ResourceNotFound;
import com.ecommerce.orderservice.payload.Customer;
import com.ecommerce.orderservice.payload.request.OrderRequest;
import com.ecommerce.orderservice.payload.response.ApiResponse;
import com.ecommerce.orderservice.payload.response.OrderResponse;
import com.ecommerce.orderservice.repository.ItemRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private ItemRepository itemRepository;

    private CustomerClient customerClient;

    private ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ItemRepository itemRepository,
                            CustomerClient customerClient,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerClient = customerClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        customerClient.getCustomerByEmail(orderRequest.getCustomerEmail())
                .orElseThrow(() -> new ResourceNotFound("Customer email not exist " + orderRequest.getCustomerEmail()));
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setPrice(0);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Order id not found " + id));
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map((order)
                -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerEmail(String email) {
        customerClient.getCustomerByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("Customer email not exist " + email));
        return orderRepository.findAllByCustomerEmail(email).stream().map((order)
                -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteOrdersByCustomerEmail(String email) {
        orderRepository.deleteAllByCustomerEmail(email);
    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Order id not found " + id));

        order.setId(id);
        order.setStatus(orderRequest.getStatus());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setName(orderRequest.getName());

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponse.class);
    }

    @Override
    public OrderResponse addItemToOrder(long orderId, long itemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFound("Order id not found " + orderId));
        Item item = itemRepository.findById(itemId).orElseThrow(()
                -> new ResourceNotFound("Item id not found " + itemId));

        order.getItems().add(item);
        order.setPrice(order.getPrice() + item.getPrice());

        Order updatedOrder = orderRepository.save(order);

        return modelMapper.map(updatedOrder, OrderResponse.class);
    }

    @Override
    public OrderResponse removeItemFromOrder(long orderId, long itemId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new ResourceNotFound("Order id not found " + orderId));
        Item item = itemRepository.findById(itemId).orElseThrow(()
                -> new ResourceNotFound("Item id not found " + itemId));

        if(!order.getItems().contains(item))
            throw new ECommerceException(HttpStatus.BAD_REQUEST, "Item does not exist in Order");

        order.getItems().remove(item);
        order.setPrice(order.getPrice() + item.getPrice());

        Order updatedOrder = orderRepository.save(order);

        return modelMapper.map(updatedOrder, OrderResponse.class);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new ResourceNotFound("Order id not found " + id));
        orderRepository.delete(order);
    }

}
