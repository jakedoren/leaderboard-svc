package com.yordle.leaderboardsvc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("words")
public class Word {
    @Id
    private String id;
    private final String word;
    private final boolean isDailyWord;

    public Word(String word, boolean isDailyWord) {
        this.word = word;
        this.isDailyWord = isDailyWord;
    }

    public String getWord() {
        return word;
    }

    public boolean isDailyWord() {
        return isDailyWord;
    }
}
