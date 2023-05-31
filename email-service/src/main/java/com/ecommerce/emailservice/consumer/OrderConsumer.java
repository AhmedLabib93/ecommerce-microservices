package com.ecommerce.emailservice.consumer;

import com.ecommerce.emailservice.payload.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues="${rabbitmq.order.routing.key}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order recieved: %s",orderEvent.toString()));
        //send email to customer
    }
}
