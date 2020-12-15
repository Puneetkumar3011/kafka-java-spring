package com.devconnect.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

//@Component
@Slf4j
public class ProductEventsConsumerManualOffset implements AcknowledgingMessageListener<Integer,String> {

    @Override
    @KafkaListener(topics = {"devconnect-product-event"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
        log.info("ConsumerRecord : {} ", consumerRecord );
        acknowledgment.acknowledge();
    }
}
