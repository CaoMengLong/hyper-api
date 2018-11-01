package com.caosoft.hyper.api.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //用于扫描所有的Servlet、filter、listener
@MapperScan("com.caosoft.hyper.api.server.mapper")
public class HyperApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyperApiApplication.class, args);
    }
}
