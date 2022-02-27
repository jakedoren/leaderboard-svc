package com.yordle.leaderboardsvc.repository;

import com.yordle.leaderboardsvc.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScoreRepository extends MongoRepository<Score, String> {
    List<Score> findAllByUsername(String username);
}
