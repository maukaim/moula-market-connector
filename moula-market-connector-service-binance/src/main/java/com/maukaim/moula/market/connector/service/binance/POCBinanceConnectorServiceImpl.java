package com.maukaim.moula.market.connector.service.binance;

import com.maukaim.moula.market.connector.service.binance.config.BinanceConfig;
import com.maukaim.moula.market.connector.service.binance.config.OkHttp3Config;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;

public class POCBinanceConnectorServiceImpl implements BinanceConnectorService {
    @Override
    public Closeable subOrderBook(String currPair) {

        Request request = new Request.Builder().url(BinanceConfig.getConnectUrl(currPair, "depth")).build();
        WebSocketListener wsListener = new MoulaWebSocketListener();

        WebSocket webSocket = OkHttp3Config.getSharedClient().newWebSocket(request, wsListener);
        return () -> {
            final int code = 1000;
            webSocket.close(code, "");
        };
    }

    private static class MoulaWebSocketListener extends okhttp3.WebSocketListener {

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            System.out.println(text);
        }
    }
}
