package com.beardactal.springbootrabbitmqexample.consumer;

import com.beardactal.springbootrabbitmqexample.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class User {

    private static final String queueName = "beardactal_queue";

    @RabbitListener(queues = queueName)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.print("Message received from queue: " + orderStatus);
    }
}
