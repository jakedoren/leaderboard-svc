package com.yordle.leaderboardsvc.model;

public class GuessResponse {
    private final String message;
    private final Matches matches;
    private final boolean correct;

    public GuessResponse(String message, byte[] partialMatchIndexes, byte[] exactMatchIndexes, Matches matches, boolean correct) {
        this.message = message;
        this.matches = matches;
        this.correct = correct;
    }

    public Matches getMatches() {
        return matches;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCorrect() {
        return correct;
    }
}
