package com.lcaohoanq.Spring_Snake_Game.controller

import com.lcaohoanq.Spring_Snake_Game.entity.Score
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScoreController(private val scoreRepository: ScoreRepository) {

    @GetMapping("/scores")
    fun getScore(): List<Score> {
        return scoreRepository.findAll();
    }

}