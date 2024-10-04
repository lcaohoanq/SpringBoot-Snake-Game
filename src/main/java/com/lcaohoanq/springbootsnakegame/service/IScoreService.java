package com.lcaohoanq.springbootsnakegame.service;

import com.lcaohoanq.springbootsnakegame.model.Score;

public interface IScoreService {

    Score updateScore(Long userId, int newScore) throws Exception;

}
