package com.maukaim.moula.market.connector.app.connector;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.app.connector.model.DataRequest;

/**
 * Ici on s'inscrit aux data de marchê
 * Si successful ca envoi la duree de validité
 */
public interface ConnectorManager {
    ConnectReport request(DataRequest dataRequest);
}
