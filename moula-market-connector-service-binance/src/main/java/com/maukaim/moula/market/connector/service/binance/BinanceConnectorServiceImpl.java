package com.maukaim.moula.market.connector.service.binance;

import com.maukaim.moula.market.connector.api.DataPublisher;
import com.maukaim.moula.market.connector.service.binance.config.BinanceConfig;
import com.maukaim.moula.market.connector.service.binance.config.OkHttp3Config;
import com.maukaim.moula.market.connector.service.binance.websocket.BinanceApiCallback;
import com.maukaim.moula.market.connector.service.binance.websocket.BinanceApiWebSocketListener;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class BinanceConnectorServiceImpl extends BinanceConnectorService {

    private final WsCallback wsCallback = new WsCallback();

    public BinanceConnectorServiceImpl(DataPublisher publisher) {
        super(publisher);
    }


    @Override
    public Closeable subOrderBookUpdates(String currPair) {
        Request request = new Request.Builder().url(BinanceConfig.getConnectUrl(currPair,"depth")).build();
        BinanceApiCallback<DepthEvent> callback = new BinanceApiCallback<DepthEvent>() {
            @Override
            public void onResponse(DepthEvent response) {
                //TODO: ici envoyer le machin dans les buts de kafka !
            }
        };
        WebSocketListener wsListener = new BinanceApiWebSocketListener<>(callback, DepthEvent.class);
        WebSocket webSocket = OkHttp3Config.getSharedClient().newWebSocket(request, wsListener);
        return () -> {
            final int code = 1000;
            wsListener.onClosing(webSocket, code, null);
            webSocket.close(code, null);
            wsListener.onClosed(webSocket, code, null);
        };

    }

    private final class WsCallback implements BinanceApiCallback<DepthEvent> {

        private final AtomicReference<Consumer<DepthEvent>> handler = new AtomicReference<>();

        @Override
        public void onResponse(DepthEvent depthEvent) {
            try {
                handler.get().accept(depthEvent);
            } catch (final Exception e) {
                System.err.println("Exception caught processing depth event");
                e.printStackTrace(System.err);
            }
        }

        @Override
        public void onFailure(Throwable cause) {
            System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
            //TODO: relancer les baiiils
        }

        private void setHandler(final Consumer<DepthEvent> handler) {
            this.handler.set(handler);
        }
    }
}
