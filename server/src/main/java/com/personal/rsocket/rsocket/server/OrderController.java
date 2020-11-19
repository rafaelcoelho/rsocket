package com.personal.rsocket.rsocket.server;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.personal.rsocket.rsocket.data.Order;
import com.personal.rsocket.rsocket.data.OrderReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class OrderController
{
    @Autowired
    private OrderReactiveRepository orderRepository;

    @MessageMapping("orders")
    public Flux<Order> orders()
    {
        log.info("Received request to read all orders");

        return this.orderRepository
                .findAll()
                .log();
    }

    @MessageMapping("order")
    public Mono<Order> order(final String orderId)
    {
        log.info("Received orderId to read an orders by id: {}", orderId);

        return this.orderRepository
                .findById(orderId)
                .log();
    }
}
