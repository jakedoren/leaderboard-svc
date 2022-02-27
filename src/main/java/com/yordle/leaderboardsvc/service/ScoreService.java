package com.yordle.leaderboardsvc.service;

import com.yordle.leaderboardsvc.model.Score;
import com.yordle.leaderboardsvc.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, MongoTemplate mongoTemplate) {
        this.scoreRepository = scoreRepository;
        this.mongoTemplate = mongoTemplate;
    }

    private String getLocalTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("EST"));
        return sdf.format(calendar.getTime());
    }

    public Score createScore(Score score) {
        score.setDate(getLocalTime(new Date()));
        return scoreRepository.insert(score);
    }

    public List<Score> getAllUserScores(String username) {
        return scoreRepository.findAllByUsername(username);
    }

    public List<Score> getTodaysScore() {
        String localDateAndTime = getLocalTime(new Date());
        String localDate = localDateAndTime.substring(0, 10);
        Query query = new Query();
        query.addCriteria(Criteria.where("date").regex(localDate));
        return mongoTemplate.find(query, Score.class);
    }

}
