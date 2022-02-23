package com.maukaim.moula.market.connector.api;

public record ConnectReport(Boolean successful, long timeoutMS, String comment) {
}
