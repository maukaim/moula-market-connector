package com.maukaim.moula.market.connector.app.request.model;

import com.maukaim.moula.market.connector.api.Exchange;

public record DataRequest(Exchange exchange,
                          String symbol,
                          DataType dataType) {
}

