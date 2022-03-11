package com.yordle.leaderboardsvc;

import com.yordle.leaderboardsvc.model.Matches;
import com.yordle.leaderboardsvc.model.Word;
import com.yordle.leaderboardsvc.repository.WordRepository;
import com.yordle.leaderboardsvc.service.GuessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuessServiceTest {
    private GuessService guessService;

    @BeforeEach
    public void setup(){
        WordRepository wordRepositoryMock = mock(WordRepository.class);
        guessService = new GuessService(wordRepositoryMock);
        when(wordRepositoryMock.findByisDailyWord(true)).thenReturn(new Word("betty", true));
    }

    @Test
    public void validateGuess() {
        Assertions.assertTrue(guessService.validateGuess("betty"));
        Assertions.assertFalse(guessService.validateGuess("chest"));
    }

    @Test
    @DisplayName("Throws exception for a faulty input of the getMatchs function")
    public void throwsExceptionForFaultyInput() {
        List<String> badInputs = Arrays.asList("longstring", "fivecs", "two", "he");
        for(String input :  badInputs) {
            Assertions.assertThrows(ResponseStatusException.class, () -> {
                guessService.getMatches(input);
            });
        }
    }

    @Test
    @DisplayName("Returns the indexes of the characters that match when the word is exactly correct")
    public void getMatchesReturnsExactIndexes() {
        Matches bettyMatches = guessService.getMatches("betty");
        ArrayList<Integer> exactBettyMatchIndexes = bettyMatches.getExactMatchIndexes();
        ArrayList<Integer> partialBettyMatchIndexes = bettyMatches.getPartialMatchIndexes();
        Assertions.assertFalse(bettyMatches.getExactMatchIndexes().isEmpty());
        Assertions.assertNull(partialBettyMatchIndexes);
        boolean containsMultipleIndexes = exactBettyMatchIndexes.contains(2) && exactBettyMatchIndexes.contains(3);
        Assertions.assertTrue(containsMultipleIndexes);
    }

    @Test
    @DisplayName("Returns the indexes of the characters that exactly match when the word is almost correct")
    public void getGetMatchesReturnsExactIndexesOfPartiallyCorrectWord() {
        Matches guessMatches = guessService.getMatches("berty");
        ArrayList<Integer> exactMatchIndexes = guessMatches.getExactMatchIndexes();
        ArrayList<Integer> partialMatchIndexes = guessMatches.getPartialMatchIndexes();
        Assertions.assertNull(partialMatchIndexes);
        Assertions.assertFalse(exactMatchIndexes.isEmpty());
        List<Integer> exactIndexes = Arrays.asList(0, 1, 3, 4);
        Assertions.assertTrue(exactMatchIndexes.containsAll(exactIndexes));
    }

    @Test
    @DisplayName("Returns partial and exact matches of a word that contains both partial and exact matches")
    public void getMatchesReturnsIndexes() {
        Matches guessMatches = guessService.getMatches("beast");
        ArrayList<Integer> exactMatchIndexes = guessMatches.getExactMatchIndexes();
        ArrayList<Integer> partialMatchIndexes = guessMatches.getPartialMatchIndexes();
        List<Integer> exactIndexes = Arrays.asList(0, 1);
        Assertions.assertTrue(exactMatchIndexes.containsAll(exactIndexes));
        Assertions.assertTrue(partialMatchIndexes.contains(4));
    }

    @Test
    @DisplayName(("Test guess with numerous partial matches"))
    public void getMatchesWithMultiplePartialMatches() {
        Matches guessMatches = guessService.getMatches("ttxzy");
        ArrayList<Integer> exactMatchIndexes = guessMatches.getExactMatchIndexes();
        ArrayList<Integer> partialMatchIndexes = guessMatches.getPartialMatchIndexes();
        List<Integer> partialIndexes = Arrays.asList(0, 1);
        Assertions.assertTrue(partialMatchIndexes.containsAll(partialIndexes));
        Assertions.assertTrue(exactMatchIndexes.contains(4));
    }

}
