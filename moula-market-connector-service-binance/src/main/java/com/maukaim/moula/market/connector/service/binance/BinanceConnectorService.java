package com.maukaim.moula.market.connector.service.binance;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.DataPublisher;
import com.maukaim.moula.market.connector.api.Exchange;

public abstract class BinanceConnectorService extends ConnectorService {
    public BinanceConnectorService(DataPublisher publisher) {
        super(publisher);
    }

    @Override
    public Exchange getSupportedExchange() {
        return Exchange.BINANCE;
    }
}
