package com.personal.rsocket.rsocket.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.personal.rsocket.rsocket.data.Order;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;

@Slf4j
@ShellComponent
public class OrderClient
{

    private final RSocketRequester requesterBuilder;
    private Disposable disposable;

    @Autowired
    public OrderClient(RSocketRequester.Builder builder)
    {
        requesterBuilder = builder
                .setupRoute("shell-client")
                .setupData(UUID.randomUUID().toString())
                .connectTcp("localhost", 7000)
                .block();
    }

    @ShellMethod("Send one request and receive a stream of orders")
    public void stream()
    {
        disposable = this.requesterBuilder
                .route("orders-stream")
                .data(new Order(1, 10, "Mobile Phone", "Samsung Galaxy Note prince starting from 10"))
                .retrieveFlux(Order.class)
                .subscribe(it -> log.info("Response: {} \n(Type 's' to stop.", it));
    }

    @ShellMethod("Stops Streams or Channels.")
    public void s() {
        if (disposable != null) {
            log.info("Stopping the current stream.");
            disposable.dispose();
            log.info("Stream stopped.");
        }
    }
}
