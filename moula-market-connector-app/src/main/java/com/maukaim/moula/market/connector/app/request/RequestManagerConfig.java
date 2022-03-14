package com.maukaim.moula.market.connector.app.request;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.DataPublisher;
import com.maukaim.moula.market.connector.app.publisher.KafkaPublisher;
import com.maukaim.moula.market.connector.service.binance.POCBinanceConnectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestManagerConfig {

    private final DataPublisher dataPublisher;

    public RequestManagerConfig(@Autowired KafkaPublisher publisher){
        this.dataPublisher = publisher;
    }

    @Bean
    public ConnectorService binanceConnector() {
        return new POCBinanceConnectorServiceImpl(this.dataPublisher);
    }
}
