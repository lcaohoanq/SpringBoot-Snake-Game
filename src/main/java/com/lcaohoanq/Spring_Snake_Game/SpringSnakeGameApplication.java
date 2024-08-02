package com.lcaohoanq.Spring_Snake_Game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.lcaohoanq.Spring_Snake_Game",
    "com.lcaohoanq.Spring_Snake_Game.listener"})
public class SpringSnakeGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSnakeGameApplication.class, args);
    }

}
