package com.maciek.algorithm;

import java.util.List;

public abstract class BackTrack extends CSPAlgorithm {

    public BackTrack(int n) {
        super(n);
    }

    protected abstract boolean isValidSubSolution(List<Integer> subSolution);

    public int run() {
        backTrack(getInitialSolution());
        return foundSolutions.size();
    }

    private void backTrack(List<Integer> subSolution) {
        logProgress(subSolution);

        if (isValidSubSolution(subSolution)) {
            if (isFullSolution(subSolution)) {
                saveSolution(subSolution);
            } else {
                triggerBackTracksForAllValues(subSolution);
            }
        }

    }

    private void triggerBackTracksForAllValues(List<Integer> subSolution) {
        allKnownValues.forEach(value -> {
            List<Integer> nextSolution = getNextSolution(subSolution, value);
            backTrack(nextSolution);
        });
    }

    //heuristic or domain calcuation?
//    private void triggerBackTracksForAllValues(List<Integer> chosenRows) {
//        List<Integer> freeRows = getFreeRows(chosenRows);
//        freeRows.forEach(freeRow -> {
//            List<Integer> nextChosenRows = new ArrayList<>(chosenRows);
//            nextChosenRows.add(freeRow);
//            backTrack(nextChosenRows);
//        });
//    }

    protected abstract List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue);

}
