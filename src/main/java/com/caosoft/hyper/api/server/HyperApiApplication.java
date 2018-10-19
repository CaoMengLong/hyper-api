package com.caosoft.hyper.api.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.caosoft.hyper.api.server.mapper")
public class HyperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyperApiApplication.class, args);
    }
}
