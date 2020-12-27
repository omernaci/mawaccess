package com.omernaci.achecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AcheckerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcheckerServiceApplication.class, args);
    }

}
