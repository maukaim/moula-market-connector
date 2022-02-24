package com.maukaim.moula.market.connector.app.request;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.app.connection.model.ConnectionTimeout;
import com.maukaim.moula.market.connector.app.connection.model.TerminationRequest;
import com.maukaim.moula.market.connector.app.connection.ConnectionTerminator;
import com.maukaim.moula.market.connector.app.connection.ConnectionTerminatorImpl;
import com.maukaim.moula.market.connector.app.request.exceptions.DataTypeNotSupported;
import com.maukaim.moula.market.connector.app.request.exceptions.ExchangeNotSupported;
import com.maukaim.moula.market.connector.app.request.model.DataRequest;
import com.maukaim.moula.market.connector.app.request.model.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class RequestManagerImpl implements RequestManager {

    private final Map<Exchange, ConnectorService> connectorServiceMap;
    private final ConnectionTerminator connectionService;

    @Autowired
    public RequestManagerImpl(List<ConnectorService> connectorServiceList) {
        this.connectionService = new ConnectionTerminatorImpl();
        this.connectorServiceMap = connectorServiceList.stream()
                .collect(Collectors
                        .toMap(ConnectorService::getSupportedExchange, co -> co));
        System.out.println(this.connectorServiceMap);

    }



    @Override
    public ConnectReport request(DataRequest dataRequest) {
        Optional<ConnectionTimeout> optionalClosingEvent = this.connectionService.rollIfHas(dataRequest);
        ConnectionTimeout connectionTimeout = optionalClosingEvent.orElseGet(()->process(dataRequest));
        return new ConnectReport(true,
                connectionTimeout.terminationEvent().getDelay(TimeUnit.MILLISECONDS),
                "OK");
    }

    private ConnectionTimeout process (DataRequest dataRequest){
        Exchange exchange = dataRequest.exchange();
        if (this.connectorServiceMap.containsKey(exchange)) {
            ConnectorService connectorService = this.connectorServiceMap.get(exchange);
            Closeable requestResult = this.requestDataType(connectorService, dataRequest.dataType(), dataRequest.symbol());
            TerminationRequest terminationRequest = new TerminationRequest(dataRequest,
                    connectorService.getTimeOutLimit(),
                    requestResult);
            return this.connectionService.scheduleTermination(terminationRequest);
        }else{
            throw new ExchangeNotSupported(exchange);
        }
    }

    private Closeable requestDataType(ConnectorService connector, DataType dataType, String symbol) {
        switch (dataType) {
            case DEPTH:
                return connector.subOrderBookUpdates(symbol);
            default:
                throw new DataTypeNotSupported(String
                        .format("The application does not support %s requests yet.", dataType));
        }
    }
}
