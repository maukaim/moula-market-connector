package com.maukaim.moula.market.connector.app.web;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.app.connector.ConnectorManager;
import com.maukaim.moula.market.connector.app.connector.model.DataRequest;
import com.maukaim.moula.market.connector.app.connector.model.DataType;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exchanges")
public class MarketConnectorController {

    private final ConnectorManager connectorManager;

    public MarketConnectorController(
            @Autowired ConnectorManager connectorManager) {
        this.connectorManager = connectorManager;
    }

    @GetMapping(value = "/request")
    public ResponseEntity<ConnectReport> request(@RequestParam Exchange exchange,
                                                   @RequestParam String symbol,
                                                   @RequestParam DataType dataType) {
        DataRequest request = new DataRequest(exchange, symbol, dataType);
        try {
            return ResponseEntity.ok(this.connectorManager.request(request));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
