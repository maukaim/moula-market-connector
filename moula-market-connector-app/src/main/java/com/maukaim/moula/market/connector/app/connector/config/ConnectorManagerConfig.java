package com.maukaim.moula.market.connector.app.connector.config;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.app.connector.ConnectorManager;
import com.maukaim.moula.market.connector.app.connector.ConnectorManagerImpl;
import com.maukaim.moula.market.connector.service.binance.POCBinanceConnectorServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConnectorManagerConfig {

    public List<ConnectorService> getConnectorServices(){
        return List.of(new POCBinanceConnectorServiceImpl());
    }

    public Map<Exchange, ConnectorService> getMap(){
        return this.getConnectorServices().stream()
                .collect(Collectors
                        .toMap(ConnectorService::getSupportedExchange, co-> co));
    }

    public static ConnectorManager getManager(){
        ConnectorManagerConfig connectorManagerConfig = new ConnectorManagerConfig();
        return new ConnectorManagerImpl(connectorManagerConfig.getMap());
    }
}
