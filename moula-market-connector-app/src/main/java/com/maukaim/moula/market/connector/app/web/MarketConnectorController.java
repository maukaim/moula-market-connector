package com.maukaim.moula.market.connector.app.web;

import com.maukaim.moula.market.connector.api.ConnectReport;
import com.maukaim.moula.market.connector.api.Exchange;
import com.maukaim.moula.market.connector.app.request.RequestManager;
import com.maukaim.moula.market.connector.app.request.model.DataRequest;
import com.maukaim.moula.market.connector.app.request.model.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exchanges")
public class MarketConnectorController {

    private final RequestManager connectorManager;

    public MarketConnectorController(
            @Autowired RequestManager connectorManager) {
        this.connectorManager = connectorManager;
    }

    @GetMapping(value = "/dataRequest")
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

    //TODO: Y a quelque chose qui va pas dans ce model !!!
    @GetMapping(value = "/ping")
    public ResponseEntity<ConnectReport> ping(@RequestParam Exchange exchange,
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
