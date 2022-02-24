package com.maukaim.moula.market.connector.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(MainApplication.class, args);
    }
}
