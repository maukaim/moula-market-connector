package com.maukaim.moula.market.connector.app.connector;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.app.connector.exceptions.DataTypeNotSupported;
import com.maukaim.moula.market.connector.app.connector.exceptions.ExchangeNotSupported;
import com.maukaim.moula.market.connector.app.connector.model.DataRequest;
import com.maukaim.moula.market.connector.app.connector.model.DataType;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ConnectorManagerImpl implements ConnectorManager {

    private final Map<Exchange, ConnectorService> connectorServiceMap;

    @Autowired
    public ConnectorManagerImpl(List<ConnectorService> connectorServiceList) {
        this.connectorServiceMap =connectorServiceList.stream()
                .collect(Collectors
                        .toMap(ConnectorService::getSupportedExchange, co-> co));
        System.out.println(this.connectorServiceMap);
    }


    @Override
    public ConnectReport request(DataRequest dataRequest) {
        Exchange exchange = dataRequest.exchange();
        if (this.connectorServiceMap.containsKey(exchange)) {
            ConnectorService connectorService = this.connectorServiceMap.get(exchange);
            Closeable requestResult = this.requestDataType(connectorService, dataRequest.dataType(), dataRequest.symbol());
            //IDEA: schedule le closing de la requestResult et en deduire le ConnectReport (cf Mohawk style)
            return new ConnectReport(true,
                    Duration.of(5, ChronoUnit.MINUTES).toMillis(),
                    "OK");
        }
        throw new ExchangeNotSupported(exchange);
    }

    private Closeable requestDataType(ConnectorService connector, DataType dataType, String symbol) {
        switch (dataType) {
            case DEPTH:
                return connector.subOrderBook(symbol);
            default:
                throw new DataTypeNotSupported(String
                        .format("The application does not support %s requests yet.", dataType));
        }
    }
}
