package com.maukaim.moula.market.connector.app.request;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.DataPublisher;
import com.maukaim.moula.market.connector.service.binance.POCBinanceConnectorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestManagerConfig {

    @Bean
    public ConnectorService binanceConnector() {
        return new POCBinanceConnectorServiceImpl(new DataPublisher() {
            @Override
            public void publish(String data) {
                System.out.println("hehe " + data);
            }
        });
    }
}
