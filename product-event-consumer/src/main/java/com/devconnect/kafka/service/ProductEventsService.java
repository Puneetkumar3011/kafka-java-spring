package com.devconnect.kafka.service;

import com.devconnect.kafka.domain.ProductEvent;
import com.devconnect.kafka.repositories.ProductEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductEventsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ProductEventsRepository productEventsRepository;

    public void processProductEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        ProductEvent productEvent = objectMapper.readValue(consumerRecord.value(), ProductEvent.class);
        log.info("productEvent : {} ", productEvent);

        switch(productEvent.getProductActionType()){
            case NEW:
                save(productEvent);
                break;
            case UPDATE:
                validate(productEvent);
                save(productEvent);
                break;
            default:
                log.info("Invalid Product Event Type");
        }

    }

    private void validate(ProductEvent productEvent) {
        if(productEvent.getProductEventId()==null){
            throw new IllegalArgumentException("Product Event Id does not exist");
        }

        Optional<ProductEvent> productEventOptional = productEventsRepository.findById(productEvent.getProductEventId());
        if(!productEventOptional.isPresent()){
            throw new IllegalArgumentException("Not a valid Product Event");
        }
        log.info("Validation is successful for the product Event : {} ", productEventOptional.get());
    }

    private void save(ProductEvent productEvent) {
        Optional<ProductEvent> productEventOptional = productEventsRepository.findById(productEvent.getProductEventId());
        if(productEventOptional.isPresent()){
            productEvent.getProduct().setProductEvent(productEventOptional.get());
        } else {
            productEvent.getProduct().setProductEvent(productEvent);
        }
        productEventsRepository.save(productEvent);
        log.info("Persisted the product Event {} ", productEvent);
    }
}
