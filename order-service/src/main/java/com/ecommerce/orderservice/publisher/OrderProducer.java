package com.ecommerce.orderservice.publisher;

import com.ecommerce.orderservice.payload.response.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order.routing.key}")
    private String orderRoutingKey;

    @Value("${rabbitmq.email.routing.key}")
    private String emailRoutingKey;


    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderResponse orderResponse){
        LOGGER.info(String.format("Order Event: %s", orderResponse.toString()));
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderResponse);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderResponse);
    }
}
