# KafkaDemo
Kafka setup on Docker and Integration with Springboot
Have you been hearing Kafka a lot lately? Do you have a use case to implement using Kafka? Are you struggling with local setup to integrate Kafka with Springboot?  Well here is a quick tour to get you started !! 

Before you jump in, be mindful that below article is focused on getting your hands dirty, and for the Kafka theory you can find ample of resources online.

Prerequisites
Running Docker instance 
Any IDE, I used IntelliJ
Familiarity with Springboot
Let us now get started to create Zookeeper and Kafka one node cluster with default configurations on Docker. Our quest is to make Kafka instance available on host machine at localhost:9092

We will start creating a network for our project in Docker

>docker network create kafka-demo

 Start the Zookeeper instance on Docker, which should be accessible on port 32181 when we start Kafka
 
>docker run -d --net=kafka-demo --name=zookeeper-demo -e ZOOKEEPER_CLIENT_PORT=32181  confluentinc/cp-zookeeper:4.0.0

Verify Zookeeper stared normally

>docker logs zookeeper-demo

Now that the zookeeper is running fine which by the way Kafka uses at the back end, we can start single node Kafka on Docker with default configurations.

>docker run –-network=kafka-demo -d -p 9092:9092 –-name=kafka-demo -e KAFKA_ZOOKEEPER_CONNECT=zookeeper-demo:32181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:4.0.0

Verify Kafka stared normally

>docker logs kafka-demo

Our local single node Kafka is ready to be used by Springboot, in below steps we target to Produce data on topic 'customer' and Consume same. Code is available at github repository in case you would like to download and directly try out. 

1- Create/ import a Springboot project into IntelliJ. (There are multiple ways to create Springboot application, do it in your preferred way.)

2- Make sure to add below spring-kafka dependency in project's pom.xml

<dependency>
   <groupId>org.springframework.kafka</groupId>
   <artifactId>spring-kafka</artifactId>
</dependency>
3-   
