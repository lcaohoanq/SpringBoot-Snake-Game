package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.model.Score;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/scores")
    List<Score> all() {
        return scoreRepository.findAll();
    }
}
