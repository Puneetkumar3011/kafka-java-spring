# Setting Up Kafka

## Download Apache Kafka binary from https://kafka.apache.org/downloads to your local machine (Eg:- C:\softwares\kafka_2.13-2.6.0)

<details><summary>Mac Local Setup</summary>
<p>

- Make sure you are navigated inside the bin directory.

## Start Zookeeper and Kafka Broker

-   Start up the Zookeeper.

```
./zookeeper-server-start.sh ../config/zookeeper.properties
```

- Add the below properties in the server.properties

```
listeners=PLAINTEXT://localhost:9092
auto.create.topics.enable=false (optional. be default its true and when unknown topic passed by producer, it creates one) 
```

-   Start up the Kafka Broker

```
./kafka-server-start.sh ../config/server.properties
```

## How to create a topic ?

```
./kafka-topics.sh --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

## How to instantiate a Console Producer?

### Without Key

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
```

### With Key

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

## How to instantiate a Console Consumer?

### Without Key

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

### With Key

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```

### With Consumer Group

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```
</p>

</details>

<details><summary>Windows Local Setup</summary>
<p>

- Make sure you are inside the **bin/windows** directory.

## Start Zookeeper and Kafka Broker

-   Start up the Zookeeper.

```
.\zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

-   Start up the Kafka Broker.

```
.\kafka-server-start.bat ..\..\config\server.properties
```

## How to create a topic ?

```
.\kafka-topics.bat --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

## How to instantiate a Console Producer?

### Without Key

```
.\kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
```

### With Key

```
.\kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

## How to instantiate a Console Consumer?

### Without Key

```
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

### With Key

```
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```

### With Consumer Group

```
.\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```
</p>

</details>

## Setting Up Multiple Kafka Brokers

- The first step is to add a new **server.properties**.

- We need to add more properties file to start up a multi broker set up.

- I added server-1.properties and server-2.properties files in C:\softwares\kafka_2.13-2.6.0\config. Then made following changes in each files

```
broker.id=<unique-broker-d>
listeners=PLAINTEXT://localhost:<unique-port>
log.dirs=/tmp/<unique-kafka-folder>
auto.create.topics.enable=false
```

- Example config will be like below.

```
broker.id=1
listeners=PLAINTEXT://localhost:9093
log.dirs=/tmp/kafka-logs-1
auto.create.topics.enable=false
```

### Starting up the new Broker

- Provide the new **server.properties** thats added.

```
.\kafka-server-start.bat ../config/server-1.properties
```

```
.\kafka-server-start.bat ../config/server-2.properties
```

- Now we have 3 running instances of Apache Brokers. As we run each instance it gets registered with Zoo Keeper.

##### Producer on all new broker instances
```
.\kafka-console-producer.bat --broker-list localhost:9092, localhost:9093, localhost:9094 --topic test-topic-1
```

##### Consumer on all new broker instances
```
.\kafka-console-consumer.bat --bootstrap-server localhost:9092, localhost:9093, localhost:9094 --topic test-topic-1 --from-beginning
```

# Advanced Kafka CLI operations:

<details><summary>Mac</summary>
<p>

## List the topics in a cluster

```
./kafka-topics.sh --zookeeper localhost:2181 --list
```

## Describe topic

- The below command can be used to describe all the topics.

```
./kafka-topics.sh --zookeeper localhost:2181 --describe
```

- The below command can be used to describe a specific topic.

```
./kafka-topics.sh --zookeeper localhost:2181 --describe --topic <topic-name>
```

## Alter the min insync replica
```
./kafka-topics.sh --alter --zookeeper localhost:2181 --topic product-events --config min.insync.replicas=2
```

## Delete a topic

```
./kafka-topics.sh --zookeeper localhost:2181 --delete --topic test-topic
```
## How to view consumer groups

```
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```

### Consumer Groups and their Offset

```
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group console-consumer-27773
```

## Viewing the Commit Log

```
./kafka-run-class.sh kafka.tools.DumpLogSegments --deep-iteration --files /tmp/kafka-logs/test-topic-0/00000000000000000000.log
```

## Setting the Minimum Insync Replica

```
./kafka-configs.sh --alter --zookeeper localhost:2181 --entity-type topics --entity-name test-topic --add-config min.insync.replicas=2
```
</p>
</details>


<details><summary>Windows</summary>
<p>

- Make sure you are inside the **bin/windows** directory.

## List the topics in a cluster

```
kafka-topics.bat --zookeeper localhost:2181 --list
```

## Describe topic

- The below command can be used to describe all the topics.

```
kafka-topics.bat --zookeeper localhost:2181 --describe
```

- The below command can be used to describe a specific topic.

```
kafka-topics.bat --zookeeper localhost:2181 --describe --topic <topic-name>
```

## Alter the min insync replica
```
kafka-topics.bat --alter --zookeeper localhost:2181 --topic product-events --config min.insync.replicas=2
```


## Delete a topic

```
kafka-topics.bat --zookeeper localhost:2181 --delete --topic <topic-name>
```


## How to view consumer groups

```
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
```

### Consumer Groups and their Offset

```
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --describe --group console-consumer-27773
```

## Viewing the Commit Log

```
kafka-run-class.bat kafka.tools.DumpLogSegments --deep-iteration --files /tmp/kafka-logs/test-topic-0/00000000000000000000.log
```

</details>

# API Details:
<details>
<p>
 
 ##### Local URL to create product: http://localhost:6061/createProduct
 ##### http request type: POST
 ##### Input Payload:
 
 ```
 {
     "productEventId":0,
     "product":{"productId":0,"productName":"New Product","productDescription":"Kafka Using Spring Boot"}
 }
 ```

 ##### Local URL to create update product: http://localhost:6061/updateProduct
 ##### http request type: PUT
 ##### Input Payload:
 
 ```
 {
     "productEventId":1,
     "product":{"productId":1,"productName":"New Product","productDescription":"New Product description"}
 }
 ```
 </p>
</details>

# H2 Console:
<details>
<p>
 
 ##### H2 Console url: http://localhost:6062/h2-console/
 ##### JDBC URL: jdbc:h2:mem:product-consumer-db
 
 </p>
</details>
