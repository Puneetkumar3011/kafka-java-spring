package com.devconnect.kafka.consumer;

import com.devconnect.kafka.service.ProductEventsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductEventsConsumer {

    @Autowired
    private ProductEventsService productEventsService;

    @KafkaListener(topics = {"devconnect-product-event"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord : {} ", consumerRecord );
        productEventsService.processProductEvent(consumerRecord);

    }
}
