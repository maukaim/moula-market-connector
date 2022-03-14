package com.maukaim.moula.market.connector.app;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;


public class MainApplicationTests {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092" ;
    public static final String TOPIC = "test";

    @Test
    public void hehe() throws ExecutionException, InterruptedException {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();
        ProducerRecord<Long, String> hello_mama = new ProducerRecord<>(TOPIC, 23L, "Ba mama");
        RecordMetadata recordMetadata = producer.send(hello_mama).get();
        System.out.println(recordMetadata);
        producer.flush();
        producer.close();
        
    }

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }
}
