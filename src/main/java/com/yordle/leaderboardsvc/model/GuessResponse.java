package com.yordle.leaderboardsvc.model;

public class GuessResponse {
    private final Matches matches;
    private final boolean correct;

    public GuessResponse(Matches matches, boolean correct) {
        this.matches = matches;
        this.correct = correct;
    }

    public Matches getMatches() {
        return matches;
    }

    public boolean isCorrect() {
        return correct;
    }
}
