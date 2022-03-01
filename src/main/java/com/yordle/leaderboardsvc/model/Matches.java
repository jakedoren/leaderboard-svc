package com.yordle.leaderboardsvc.model;

import java.util.ArrayList;

public class Matches {
    private final ArrayList<Integer> partialMatchIndexes;
    private final ArrayList<Integer> exactMatchIndexes;

    public Matches(ArrayList<Integer> partialMatchIndexes, ArrayList<Integer> exactMatchIndexes) {
        this.partialMatchIndexes = partialMatchIndexes;
        this.exactMatchIndexes = exactMatchIndexes;
    }

    public ArrayList<Integer> getPartialMatchIndexes() {
        return partialMatchIndexes;
    }

    public ArrayList<Integer> getExactMatchIndexes() {
        return exactMatchIndexes;
    }

}
