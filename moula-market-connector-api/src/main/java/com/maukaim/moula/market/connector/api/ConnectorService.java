package com.maukaim.moula.market.connector.api;

import java.io.Closeable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class ConnectorService {
    protected final DataPublisher dataPublisher;

    public ConnectorService(DataPublisher publisher){
        this.dataPublisher = publisher;
    }
    public abstract Exchange getSupportedExchange();

    /**
     * Create a connection to get order book updates
     * @param currPair
     * @return
     */
    public abstract Closeable subOrderBookUpdates(String currPair);

    /**
     * Allow ConnectorService(s) to customize the time required for consumers to ping the application
     * before the connection get terminated. Useful when there is some consumption limit to respect and
     * the ConnectorService prefers to be stingy.
     * @return time in Milliseconds
     */
    public long getTimeOutLimit(){
        return Duration.of(10, ChronoUnit.SECONDS).toMillis();
    }

    public void publish(String data){
        this.dataPublisher.publish(data);
    }
}
