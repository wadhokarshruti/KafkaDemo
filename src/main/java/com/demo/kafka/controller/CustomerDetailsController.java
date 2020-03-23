package com.demo.kafka.controller;

import com.demo.kafka.consumer.CustomerDetailsConsumer;
import com.demo.kafka.producer.CustomerDetailsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafkaDemo")
public class CustomerDetailsController {
    private final CustomerDetailsProducer producer;
    private final CustomerDetailsConsumer consumer;

    @Autowired
    public CustomerDetailsController(CustomerDetailsProducer producer, CustomerDetailsConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @GetMapping(value = "publisher/{message}")
    public String sendMessageToKafkaTopic(@PathVariable String message){
        this.producer.sendMessage(message);
        return "Published successfully!!";
    }
}
