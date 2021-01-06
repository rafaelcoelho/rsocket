package com.personal.rsocket.rsocket.client;

import com.personal.rsocket.rsocket.data.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import reactor.core.Disposable;

import java.util.UUID;

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
                .tcp("localhost", 7000);
    }

    @Bean
    public RSocketRequester requester()
    {
        return this.requesterBuilder;
    }

    @ShellMethod("Send one request and receive a stream of orders")
    public void stream()
    {
        disposable = this.requesterBuilder
                .route("orders")
                .retrieveFlux(Order.class)
                .subscribe(it -> log.info("Response: {} \n(Type 's' to stop.", it));
    }

    @ShellMethod("Send one request and receive a one order")
    public void getOrder(String orderId)
    {
        disposable = this.requesterBuilder
                .route("order")
                .data(orderId)
                .retrieveMono(Order.class)
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
