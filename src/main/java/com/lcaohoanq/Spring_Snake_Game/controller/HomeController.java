package com.lcaohoanq.Spring_Snake_Game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "${v1API}")
@RestController
public class HomeController {

    @GetMapping("")
    public String home(){
        return "Hello World from Spring Snake Game";
    }

}
