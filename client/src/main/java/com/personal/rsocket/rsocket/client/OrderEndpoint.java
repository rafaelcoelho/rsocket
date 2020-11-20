package com.personal.rsocket.rsocket.client;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.personal.rsocket.rsocket.data.Order;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderEndpoint
{
    private final RSocketRequester requesterBuilder;

    @GetMapping (
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/order/stream/monitor/{id}"
    )
    public Publisher<Order> orderStream(@PathVariable String id)
    {
        return this.requesterBuilder
                .route("monitor")
                .data(id)
                .retrieveFlux(Order.class);
    }

    @GetMapping (
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/order/stream"
    )
    public Publisher<Order> orderStream()
    {
        return this.requesterBuilder
                .route("orders")
                .retrieveFlux(Order.class);
    }

    @GetMapping (
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/order/{id}"
    )
    public Publisher<Order> orderById(@PathVariable String id)
    {
        return this.requesterBuilder
                .route("order")
                .data(id)
                .retrieveMono(Order.class);
    }
}
