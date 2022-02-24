package com.maukaim.moula.market.connector.app.request;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.app.request.model.DataRequest;

/**
 * Ici on s'inscrit aux data de marchê
 * Si successful ca envoi la duree de validité
 */
public interface RequestManager {
    ConnectReport request(DataRequest dataRequest);
}
