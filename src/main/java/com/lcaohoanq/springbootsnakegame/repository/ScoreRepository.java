package com.lcaohoanq.springbootsnakegame.repository;

import com.lcaohoanq.springbootsnakegame.model.Score;
import com.lcaohoanq.springbootsnakegame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByUser(User user);

}
