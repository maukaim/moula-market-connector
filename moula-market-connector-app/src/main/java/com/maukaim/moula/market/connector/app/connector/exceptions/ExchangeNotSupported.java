package com.maukaim.moula.market.connector.app.connector.exceptions;

import com.maukaim.moula.market.connector.api.Exchange;

public class ExchangeNotSupported extends RuntimeException{
    public ExchangeNotSupported(Exchange exchange){
        super(String.format("No support for %s exchange.", exchange));
    }
}
