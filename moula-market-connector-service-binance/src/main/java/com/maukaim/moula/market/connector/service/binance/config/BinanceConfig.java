package com.maukaim.moula.market.connector.service.binance.config;

public class BinanceConfig {
    public static final String BINANCE_WS = "wss://stream.binance.com:9443/ws/%s@%s";

    public static String getConnectUrl(String symbol, String dataType){
        return String.format(BINANCE_WS, symbol, dataType);
    }
}
