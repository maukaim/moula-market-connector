package com.maukaim.moula.market.connector.app.connection;

import com.maukaim.moula.market.connector.app.connection.model.ConnectionTimeout;
import com.maukaim.moula.market.connector.app.connection.model.TerminationRequest;
import com.maukaim.moula.market.connector.app.request.model.DataRequest;

import java.util.Optional;

public interface ConnectionTerminator {
    ConnectionTimeout scheduleTermination(TerminationRequest request);
    Optional<ConnectionTimeout> rollIfHas(DataRequest dataRequest);
}
