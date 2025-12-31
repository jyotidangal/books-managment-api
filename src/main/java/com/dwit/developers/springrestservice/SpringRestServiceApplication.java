package com.dwit.developers.springrestservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.dwit.developers.springrestservice.mapper")
@SpringBootApplication
public class SpringRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestServiceApplication.class, args);
    }

}
