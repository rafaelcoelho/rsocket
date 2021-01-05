package com.personal.rsocket.rsocket.client;

import com.personal.rsocket.rsocket.data.Order;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.protobuf.ProtobufDecoder;
import org.springframework.http.codec.protobuf.ProtobufEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@ShellComponent
public class OrderProtoBuf {
    private final RSocketRequester requesterBuilder;

    @Autowired
    public OrderProtoBuf(RSocketRequester.Builder builder) {
        requesterBuilder = builder
                .rsocketStrategies(it -> it.encoder(new ProtobufEncoder())
                        .decoder(new ProtobufDecoder())
                        .build())
                .setupRoute("protobuf-client")
                .setupData(UUID.randomUUID().toString())
                .tcp("localhost", 7000);
    }

    @GetMapping(
            produces = MediaType.TEXT_EVENT_STREAM_VALUE,
            value = "/order/protobuf/stream/monitor/{id}"
    )
    public Publisher<Order> orderStream(@PathVariable String id) {

        return this.requesterBuilder
                .route("monitor-protobuf")
                .data(id)
                .retrieveFlux(Order.class);
    }
}
