package com.maukaim.moula.market.connector.api;

import java.io.Closeable;

public interface ConnectorService {
    Exchange getSupportedExchange();


    Closeable subOrderBook(String currPair);
}
