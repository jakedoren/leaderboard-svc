package com.yordle.leaderboardsvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("scores")
public class Score {
    @Id
    private String id;
    private String username;
    private byte attempts;
    private String date;

    public Score(String username, byte attempts, String date) {
        this.username = username;
        this.attempts = attempts;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getAttempts() {
        return attempts;
    }

    public void setAttempts(byte attempts) {
        this.attempts = attempts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
