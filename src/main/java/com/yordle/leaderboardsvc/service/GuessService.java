package com.yordle.leaderboardsvc.service;

import com.yordle.leaderboardsvc.model.Matches;
import com.yordle.leaderboardsvc.model.Word;
import com.yordle.leaderboardsvc.repository.WordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        return guess.equals(dailyWord.getWord()) && dailyWord.isDailyWord();
    }

    private List<Integer> indexOfAll(char[] array, char letter) {
        final List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if(array[i] == letter) {
                indexList.add(i);
            }
        }
        return indexList;
    }

    public Matches getMatches(String guess) throws ResponseStatusException {
        if(guess.length() != 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Word must be 5 characters");
        }
        Word dailyWord = getDailyWord();
        char[] guessArray = guess.toCharArray();
        char[] dailyWordArray = dailyWord.getWord().toCharArray();
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
                List<Integer> guessIndexes = indexOfAll(guessArray, matchedLetter);
                for(Integer index : guessIndexes) {
                    if(guessArray[index] == dailyWordArray[index] && !exactMatches.contains(index)) {
                        exactMatches.add(index);
                    } else if(!partialMatches.contains(index) && !exactMatches.contains(index)){
                        partialMatches.add(index);
                    }
                }
            }
        }
        Matches matches = new Matches();
        if(!partialMatches.isEmpty()) {
            matches.setPartialMatchIndexes(partialMatches);
        }
        if(!exactMatches.isEmpty()) {
            matches.setExactMatchIndexes(exactMatches);
        }
        return matches;
    }

}
