package com.devconnect.kafka.controller;

import com.devconnect.kafka.domain.Product;
import com.devconnect.kafka.domain.ProductActionType;
import com.devconnect.kafka.domain.ProductEvent;
import com.devconnect.kafka.producer.ProductEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class ProductEventController {
    @Autowired
    ProductEventProducer productEventProducer;

    @PostMapping("/productevent")
    public ResponseEntity<ProductEvent> postProductEvent(@RequestBody @Valid ProductEvent productEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        /* invoke kafka producer */
        productEventProducer.sendProductEventAsynchronous(productEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(productEvent);
    }

    @PutMapping("/productevent")
    public ResponseEntity<?> putProductEvent(@RequestBody @Valid ProductEvent productEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        /* input validation */
        if(productEvent.getProductEventId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the ProductEventId");
        }

        productEvent.setProductActionType(ProductActionType.UPDATE);
        /* invoke kafka producer */
        productEventProducer.sendProductEventAsynchronousWithRecordHeader(productEvent);
        return ResponseEntity.status(HttpStatus.OK).body(productEvent);
    }

}
