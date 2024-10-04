package com.lcaohoanq.Spring_Snake_Game.service;

import com.lcaohoanq.Spring_Snake_Game.model.Score;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.repository.ScoreRepository;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService implements IScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    @Override
    public Score updateScore(Long userId, int newScore) {

        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d not found", userId));
        }

        User user = existingUser.get();
        Score score = user.getScore();

        //check the current score vs newScore, update the max_score if needed
        if (newScore > score.getMax_score()) {
            score.setMax_score(newScore);
        }

        // Save the updated score (assuming you have a ScoreRepository)
        scoreRepository.save(score);
        return score;
    }

}
