package com.maukaim.moula.market.connector.api;


public class ConnectReport {

    public ConnectReport(Boolean successful, long timeoutMS, String comment) {
        this.successful = successful;
        this.timeoutMS = timeoutMS;
        this.comment = comment;
    }

    private final Boolean successful;
    private final long timeoutMS;
    private final String comment;

    public Boolean getSuccessful() {
        return successful;
    }

    public long getTimeoutMS() {
        return timeoutMS;
    }

    public String getComment() {
        return comment;
    }
}
