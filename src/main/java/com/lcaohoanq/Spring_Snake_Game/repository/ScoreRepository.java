package com.lcaohoanq.Spring_Snake_Game.repository;

import com.lcaohoanq.Spring_Snake_Game.entity.Score;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByUser(User user);

}
