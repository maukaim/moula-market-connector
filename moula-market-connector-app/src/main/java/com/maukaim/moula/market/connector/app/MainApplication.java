package com.maukaim.moula.market.connector.app;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.api.HelloWorldConfig;
import com.maukaim.moula.market.connector.app.connector.ConnectorManager;
import com.maukaim.moula.market.connector.app.connector.config.ConnectorManagerConfig;
import com.maukaim.moula.market.connector.app.connector.model.DataRequest;
import com.maukaim.moula.market.connector.app.connector.model.DataType;
import com.maukaim.moula.market.connector.service.binance.HelloWorldAdapter;
import com.maukaim.moula.market.connector.service.binance.POCBinanceConnectorServiceImpl;

import java.io.Closeable;
import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println(HelloWorldAdapter.enrichFriendly(HelloWorldConfig.ENGLISH));

        ConnectorManager manager = ConnectorManagerConfig.getManager();
        DataRequest request = new DataRequest(Exchange.GEMINI, "btcusdt", DataType.DEPTH);
        ConnectReport request1 = manager.request(request);
    }
}
