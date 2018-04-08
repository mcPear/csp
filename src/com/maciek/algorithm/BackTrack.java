package com.maciek.algorithm;

import java.util.List;

public abstract class BackTrack extends CSPAlgorithm {

    public BackTrack(int n, Options options) {
        super(n, options);
    }

    protected abstract boolean isValidSubSolution(List<Integer> subSolution);

    public Result run() {
        long start = System.currentTimeMillis();
        backTrack(getInitialSolution());
        if (options.countExecutionTime) {
            executionTimeMillis = System.currentTimeMillis() - start;
        }
        return getResult();
    }

    private void backTrack(List<Integer> subSolution) {
        if (options.logProgress) {
            logProgress(subSolution);
        }
        if (options.countRecursiveCalls) recursiveCallsCount++;
        if (options.stopAtFirstSolution && !foundSolutions.isEmpty()) {
            return;
        }

        if (isValidSubSolution(subSolution)) {
            if (isFullSolution(subSolution)) {
                saveSolution(subSolution);
            } else {
                triggerBackTracksForAllValues(subSolution);
            }
        } else if (options.countReturns) {
            returnsCount++;
        }

    }

    private void triggerBackTracksForAllValues(List<Integer> subSolution) {
        allKnownValues.forEach(value -> {
            List<Integer> nextSolution = getNextSolution(subSolution, value, -1);
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


}
