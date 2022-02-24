package com.maukaim.moula.market.connector.app.connection.model;

import com.maukaim.moula.market.connector.app.request.model.DataRequest;

import java.io.Closeable;

public record TerminationRequest(
        DataRequest dataRequest,
        Long timeoutMS,
        Closeable closeable) {
}
