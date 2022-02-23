package com.maukaim.moula.market.connector.service.binance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maukaim.moula.market.connector.service.binance.domain.market.OrderBookEntryDeserializer;
import com.maukaim.moula.market.connector.service.binance.domain.market.OrderBookEntrySerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonDeserialize(using = OrderBookEntryDeserializer.class)
@JsonSerialize(using = OrderBookEntrySerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookEntry {

    private String price;
    private String qty;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("price", price)
                .append("qty", qty)
                .toString();
    }
}
