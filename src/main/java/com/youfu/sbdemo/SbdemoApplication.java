package com.youfu.sbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.youfu.sbdemo.mapper")
public class SbdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }
}
