package com.maukaim.moula.market.connector.app;

import com.maukaim.moula.market.connector.api.HelloWorldConfig;
import com.maukaim.moula.market.connector.service.binance.HelloWorldAdapter;

public class MainApplication {

    public static void main(String[] args) {
        System.out.println(HelloWorldAdapter.enrichFriendly(HelloWorldConfig.ENGLISH));
    }
}
