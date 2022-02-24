package com.maukaim.moula.market.connector.app.connection;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class ConnectionTerminatorConfig {

    static ScheduledExecutorService getExecutor() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((thread, throwable) -> {
                    System.out.println("While closing connection -> {}" + throwable.getMessage() + throwable);
                })
                .setNameFormat("ConnectionCloser-%d")
                .build();
        return Executors.newSingleThreadScheduledExecutor(namedThreadFactory);
    }
}
