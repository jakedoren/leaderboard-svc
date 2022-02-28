package com.yordle.leaderboardsvc.controller;

import com.yordle.leaderboardsvc.model.Score;
import com.yordle.leaderboardsvc.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderBoardController {

    private final ScoreService scoreService;

    @Autowired
    public LeaderBoardController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public List<Score> getTodaysScores() {
        return scoreService.getTodaysScores();
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
    }

    @GetMapping("/user/allscores")
    public List<Score> getAllUserScores(@RequestParam String username) {
        return scoreService.getAllUserScores(username);
    }

}
