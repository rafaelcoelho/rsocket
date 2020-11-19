package com.personal.rsocket.rsocket.server;

import java.time.Duration;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.personal.rsocket.rsocket.data.Order;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Controller
public class OrderController
{
    @MessageMapping("orders-stream")
    public Flux<Order> orders(final Order request)
    {
        log.info("Received stream request: {}", request);

        return Flux
                .interval(Duration.ofSeconds(1))
                .map(it -> new Order(10, request.getPrice() + it.intValue(), request.getName(), request.getDescription() + " Price is changing !!!"))
                .log();
    }
}
