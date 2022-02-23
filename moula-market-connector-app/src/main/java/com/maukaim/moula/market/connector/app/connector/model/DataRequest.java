package com.maukaim.moula.market.connector.app.connector.model;

import com.maukaim.moula.market.connector.api.Exchange;

public record DataRequest(Exchange exchange,
                          String symbol,
                          DataType dataType) {
}

