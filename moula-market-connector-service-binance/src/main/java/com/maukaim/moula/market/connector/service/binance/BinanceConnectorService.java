package com.maukaim.moula.market.connector.service.binance;

import com.maukaim.moula.market.connector.api.ConnectorService;
import com.maukaim.moula.market.connector.api.Exchange;

public interface BinanceConnectorService extends ConnectorService {
    @Override
    default Exchange getSupportedExchange() {
        return Exchange.BINANCE;
    }
}
