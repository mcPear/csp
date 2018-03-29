package com.maciek.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class CSPAlgorithm {

    protected final int n;
    protected final List<List<Integer>> foundSolutions;
    protected final List<Integer> allKnownValues;

    protected CSPAlgorithm(int n) {
        this.n = n;
        this.foundSolutions = new ArrayList<>();
        this.allKnownValues = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            allKnownValues.add(i);
        }
    }

    public void logProgress(List<Integer> chosenRows) {
        if(new HashSet<>(chosenRows).size() == 2){
            System.out.println(chosenRows.get(0));
        }
        if (chosenRows.size() == 1) {
            System.out.println("" + (chosenRows.get(0) * 100 / n) + "%");
        }
    }

    protected abstract boolean isFullSolution(List<Integer> chosenRows);

    protected void saveSolution(List<Integer> chosenRows) {
        foundSolutions.add(chosenRows);
    }

    protected abstract List<Integer> getInitialSolution();

    protected abstract List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue);

}
