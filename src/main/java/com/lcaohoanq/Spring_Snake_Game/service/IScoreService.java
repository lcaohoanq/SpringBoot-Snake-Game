package com.lcaohoanq.Spring_Snake_Game.service;

import com.lcaohoanq.Spring_Snake_Game.model.Score;

public interface IScoreService {

    Score updateScore(Long userId, int newScore) throws Exception;

}
