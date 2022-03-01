package com.yordle.leaderboardsvc.controller;

import com.yordle.leaderboardsvc.model.Matches;
import com.yordle.leaderboardsvc.model.Score;
import com.yordle.leaderboardsvc.service.GuessService;
import com.yordle.leaderboardsvc.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderBoardController {

    private final ScoreService scoreService;
    private final GuessService guessService;

    @Autowired
    public LeaderBoardController(ScoreService scoreService, GuessService guessService) {
        this.scoreService = scoreService;
        this.guessService = guessService;
    }

    @GetMapping("/attempted")
    public Boolean attemptedToday(@RequestParam String username) {
        return scoreService.attemptedToday(username);
    }

    @GetMapping("/today")
    public List<Score> getTodaysScores() {
        return scoreService.getTodaysScores();
    }

    @PostMapping("/guess")
    public Matches validateGuess(@RequestParam String guess) {
        return guessService.getMatches(guess);
    }

    @PostMapping("/create/score")
    public Score createScore(@RequestBody Score score) {
        return scoreService.createScore(score);
    }

    @GetMapping("/user/allscores")
    public List<Score> getAllUserScores(@RequestParam String username) {
        return scoreService.getAllUserScores(username);
    }

}
