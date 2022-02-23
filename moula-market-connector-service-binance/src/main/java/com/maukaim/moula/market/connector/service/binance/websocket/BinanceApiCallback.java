package com.maukaim.moula.market.connector.service.binance.websocket;

public interface BinanceApiCallback<T> {

    /**
     * Called whenever a response comes back from the Binance API.
     *
     * @param response the expected response object
     */
    void onResponse(T response);

    /**
     * Called whenever an error occurs.
     *
     * @param cause the cause of the failure
     */
    default void onFailure(Throwable cause) {}
}
