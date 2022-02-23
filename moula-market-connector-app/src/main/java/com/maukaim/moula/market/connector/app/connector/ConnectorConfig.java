package com.maukaim.moula.market.connector.app.connector;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.service.binance.POCBinanceConnectorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {

    @Bean
    public ConnectorService binanceConnector() {
        return new POCBinanceConnectorServiceImpl();
    }
}
