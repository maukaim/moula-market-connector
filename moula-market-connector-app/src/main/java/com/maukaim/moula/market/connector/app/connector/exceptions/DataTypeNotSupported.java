package com.maukaim.moula.market.connector.app.connector.exceptions;

import com.maukaim.moula.market.connector.api.Exchange;

public class DataTypeNotSupported extends RuntimeException{
    public DataTypeNotSupported(String msg){
        super(msg);
    }
}
