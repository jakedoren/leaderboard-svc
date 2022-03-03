package com.yordle.leaderboardsvc.model;

import java.util.ArrayList;

public class Matches {
    private ArrayList<Integer> partialMatchIndexes;
    private ArrayList<Integer> exactMatchIndexes;

    public void setPartialMatchIndexes(ArrayList<Integer> partialMatchIndexes) {
        this.partialMatchIndexes = partialMatchIndexes;
    }

    public void setExactMatchIndexes(ArrayList<Integer> exactMatchIndexes) {
        this.exactMatchIndexes = exactMatchIndexes;
    }

    public ArrayList<Integer> getPartialMatchIndexes() {
        return partialMatchIndexes;
    }

    public ArrayList<Integer> getExactMatchIndexes() {
        return exactMatchIndexes;
    }
}
