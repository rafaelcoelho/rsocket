package com.personal.rsocket.rsocket.data;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDataInitializer
{
    private final OrderReactiveRepository orderRepository;

    @EventListener (ApplicationReadyEvent.class)
    public void start()
    {
        final Flux<Order> orders = Flux
                .just("Apple", "Samsung", "LG", "Motorola", "ZTE", "ASUS")
                .map(brand -> new Order(null, 10, brand, "Some Description"))
                .flatMap(this.orderRepository::save);

        this.orderRepository
                .deleteAll()
                .thenMany(orders)
                .thenMany(this.orderRepository.findAll())
                .subscribe(it -> log.info("Data is {}", it));
    }
}
