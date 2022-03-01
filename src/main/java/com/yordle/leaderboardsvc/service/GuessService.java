package com.yordle.leaderboardsvc.service;

import com.yordle.leaderboardsvc.model.Matches;
import com.yordle.leaderboardsvc.model.Word;
import com.yordle.leaderboardsvc.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuessService {
    private final WordRepository wordRepository;

    public GuessService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    private Word getDailyWord() {
        return wordRepository.findByisDailyWord(true);
    }

    public boolean validateGuess(String guess) {
        Word dailyWord = getDailyWord();
        return guess.equals(dailyWord.getWord());
    }

    private int indexOf(char[] array, char key) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == key)
                return i;
        return -1;
    }

    public Matches getMatches(String guess) {
        Word dailyWord = getDailyWord();
        char[] guessArray = guess.toCharArray();
        char[] dailyWordArray = dailyWord.getWord().toCharArray();
        System.out.println(guessArray);
        ArrayList<Character> matchedLetters = new ArrayList<>();
        for(char c : guessArray) {
            if(dailyWord.getWord().contains(String.valueOf(c))) {
                matchedLetters.add(c);
            }
        }
        ArrayList<Integer> partialMatches = new ArrayList<>();
        ArrayList<Integer> exactMatches = new ArrayList<>();
        if(!matchedLetters.isEmpty()) {
            for (Character matchedLetter : matchedLetters) {
                int guessIndex = indexOf(guessArray, matchedLetter);
                if(guessArray[guessIndex] == dailyWordArray[guessIndex]) {
                    exactMatches.add(guessIndex);
                } else {
                    partialMatches.add(guessIndex);
                }
            }
        }
        return new Matches(partialMatches, exactMatches);
    }

}
