package com.lcaohoanq.springbootsnakegame.controller;

import com.lcaohoanq.springbootsnakegame.dto.request.UpdateScoreRequest;
import com.lcaohoanq.springbootsnakegame.model.Score;
import com.lcaohoanq.springbootsnakegame.model.User;
import com.lcaohoanq.springbootsnakegame.service.IScoreService;
import com.lcaohoanq.springbootsnakegame.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${v1API}/scores")
public class ScoreController {

    private final IUserService userService;
    private final IScoreService scoreService;

    @PutMapping("users/{id}")
    public ResponseEntity<?> updateUserScore(@PathVariable("id") Long userId,
        @RequestBody UpdateScoreRequest updateScoreRequest) {
        try {
            User user = userService.findById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            Score score = scoreService.updateScore(userId, updateScoreRequest.getNewScore());
            log.info("Score updated for user with id: {}", userId);
            return ResponseEntity.ok().body(score);
        } catch (Exception e) {
            log.error("Error updating score for user with id: {}", userId);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/users/email")
    public ResponseEntity<?> updateUserScoreByEmail(
        @RequestParam(value = "email") String email,
        @RequestParam(value = "newScore") int newScore) {
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            Score score = scoreService.updateScore(user.getId(), newScore);
            log.info("Score updated for user with email: {}", email);
            return ResponseEntity.ok().body(score);
        } catch (Exception e) {
            log.error("Error updating score for user with email: {}", email);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
