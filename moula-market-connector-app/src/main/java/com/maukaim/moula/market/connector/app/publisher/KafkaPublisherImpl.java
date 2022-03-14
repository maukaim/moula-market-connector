package com.maukaim.moula.market.connector.app.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublisherImpl implements KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaPublisherImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    /**
     * IDEA: Peut etre ici pour determiner le return de publish du
     * IDEA: DataPublisher et declarer le nom du topic kafka, la key a verifier, le host, etc
     * @param data
     */
    @Override
    public void publish(String data) {
        this.kafkaTemplate.send("test", data);
    }
}
