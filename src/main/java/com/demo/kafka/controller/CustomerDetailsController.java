package com.demo.kafka.controller;

import com.demo.kafka.consumer.CustomerDetailsConsumer;
import com.demo.kafka.producer.CustomerDetailsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "publisher")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }

    @PostMapping(value = "consumer")
    public void readFromKafkaTopic(@RequestParam("message") String message){
        this.consumer.customerDetailConsumer(message);
    }
}
