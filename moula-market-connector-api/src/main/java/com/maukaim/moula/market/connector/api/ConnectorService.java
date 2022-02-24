package com.maukaim.moula.market.connector.api;

import java.io.Closeable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public interface ConnectorService {
    Exchange getSupportedExchange();

    /**
     * Create a connection to get order book updates
     * @param currPair
     * @return
     */
    Closeable subOrderBookUpdates(String currPair);

    /**
     * Allow ConnectorService(s) to customize the time required for consumers to ping the application
     * before the connection get terminated. Useful when there is some consumption limit to respect and
     * the ConnectorService prefers to be stingy.
     * @return time in Milliseconds
     */
    default long getTimeOutLimit(){
        return Duration.of(5, ChronoUnit.MINUTES).toMillis();
    }
}
