package com.beardactal.springbootrabbitmqexample.publisher;

import com.beardactal.springbootrabbitmqexample.dto.Order;
import com.beardactal.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Value("${queue.name}")
    private String queueName;

    @Value("${exchange.name}")
    private String exchangeName;

    @Value("${routing.key.name}")
    private String routingKeyName;

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId((UUID.randomUUID().toString()));
        // service layer calls
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restaurantName + ".");
        template.convertAndSend(exchangeName, routingKeyName, orderStatus);
        return "Success";
    }
}
