package com.yordle.leaderboardsvc;

import com.yordle.leaderboardsvc.model.Word;
import com.yordle.leaderboardsvc.repository.WordRepository;
import com.yordle.leaderboardsvc.service.GuessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuessServiceTest {
    private GuessService guessService;
    private WordRepository wordRepositoryMock;

    @BeforeEach
    public void setup(){
        wordRepositoryMock = mock(WordRepository.class);
        guessService = new GuessService(wordRepositoryMock);
    }

    @Test
    public void validateGuess() {
        when(wordRepositoryMock.findByisDailyWord(true)).thenReturn(new Word("betty", false));
        assertTrue(guessService.validateGuess("betty"));
    }
}
