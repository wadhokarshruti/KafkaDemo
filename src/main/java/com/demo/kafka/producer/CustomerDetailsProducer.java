package com.demo.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class CustomerDetailsProducer {
    public static final Logger logger = LoggerFactory.getLogger(CustomerDetailsProducer.class);
    public static final String TOPIC = "Customer";

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "KafkaExampleProducer");
        props.put("key.serializer", LongSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<Long, String> producer = new KafkaProducer<>(props);
        ProducerRecord<Long, String> record = new ProducerRecord<>("customer", 1L, "Test 1");
        try {
            producer.send(record).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        producer.flush();
        //this.kafkaTemplate.send(TOPIC, message);
    }
}
