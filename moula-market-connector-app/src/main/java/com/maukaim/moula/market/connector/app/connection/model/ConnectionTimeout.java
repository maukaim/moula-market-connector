package com.maukaim.moula.market.connector.app.connection.model;

import java.util.concurrent.ScheduledFuture;

public record ConnectionTimeout(TerminationRequest terminationRequest, ScheduledFuture<?> terminationEvent) {
}
