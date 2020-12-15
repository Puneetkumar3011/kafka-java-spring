package com.devconnect.kafka.repositories;

import com.devconnect.kafka.domain.ProductEvent;
import org.springframework.data.repository.CrudRepository;

public interface ProductEventsRepository extends CrudRepository<ProductEvent,Integer> {
}
