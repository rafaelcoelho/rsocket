package com.personal.rsocket.rsocket;

import lombok.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

class RsocketApplicationTests {

    private static final int TIMES = 100000;
    private static final List<Car> CARS = new ArrayList<>(Byte.MAX_VALUE * TIMES);

    @Test
    void reduce() {

        for (int i = 0; i < Byte.MAX_VALUE * TIMES; i++) {
            Car car = new Car("black" + i, "bmw_" + 1, of("sound", "alarm", "gps"));
            CARS.add(car);
        }

        System.out.println("cars = " + CARS.size());

        var carsCollected = CARS
                .stream()
                .collect(Store::builder,
                        Store.StoreBuilder::car,
                        (a, b) -> a.cars(b.build().getCars()))
                .build();

        System.out.println("carsCollected = " + (carsCollected.getCars().size() == CARS.size()));

        Assertions.assertThat(carsCollected.getCars())
                .hasSameSizeAs(CARS);
    }

    @Data
    @AllArgsConstructor
    static class Car {
        private String color;
        private String brand;
        private List<String> additions;
    }

    @Builder
    @Getter
    static class Store {
        @Singular
        private List<Car> cars;
    }
}
