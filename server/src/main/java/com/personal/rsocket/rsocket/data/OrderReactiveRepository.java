package com.personal.rsocket.rsocket.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReactiveRepository extends ReactiveCrudRepository<Order, String> {}
