package com.maukaim.moula.market.connector.service.binance.config;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OkHttp3Config {

    private static final OkHttpClient sharedClient;

    static {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(500);
        dispatcher.setMaxRequests(500);
        sharedClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .pingInterval(20, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpClient getSharedClient() {
        return sharedClient;
    }
}
