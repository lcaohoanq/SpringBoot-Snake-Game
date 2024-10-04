package com.lcaohoanq.springbootsnakegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {"com.lcaohoanq.springbootsnakegame",
    "com.lcaohoanq.springbootsnakegame.listener"})
public class SpringSnakeGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSnakeGameApplication.class, args);
    }

}
