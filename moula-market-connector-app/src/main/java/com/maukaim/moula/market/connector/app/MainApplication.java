package com.maukaim.moula.market.connector.app;

import com.maukaim.moula.market.connector.api.HelloWorldConfig;
import com.maukaim.moula.market.connector.service.binance.HelloWorldAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(MainApplication.class, args);
        System.out.println(HelloWorldAdapter.enrichFriendly(HelloWorldConfig.ENGLISH));
    }
}
