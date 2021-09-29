package com.cunshan.springboot.lab01.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {  //需要把 main函数放到java目录下的package目录
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
