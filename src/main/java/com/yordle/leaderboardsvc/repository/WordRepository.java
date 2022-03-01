package com.yordle.leaderboardsvc.repository;

import com.yordle.leaderboardsvc.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<Word, String> {
    Word findByisDailyWord(boolean isDailyWord);
}
