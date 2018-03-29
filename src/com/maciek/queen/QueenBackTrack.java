package com.maciek.queen;

import com.maciek.algorithm.BackTrack;
import com.maciek.algorithm.Options;

import java.util.*;

public class QueenBackTrack extends BackTrack {

    public QueenBackTrack(int n, Options options) {
        super(n, options);
    }

    @Override
    protected boolean isValidSubSolution(List<Integer> subSolution) {
        if (subSolution.isEmpty()) {
            return true;
        }
        List<Integer> chosenRowsHead = new ArrayList<>(subSolution).subList(0, subSolution.size() - 1);
        Integer theLastChosenRow = subSolution.get(subSolution.size() - 1);
        int column = subSolution.size();

        Set<Integer> unavailableRows = new HashSet<>(chosenRowsHead);
        for (int i = 0; i < chosenRowsHead.size(); i++) {
            unavailableRows.add(chosenRowsHead.get(i) + (column - (i + 1)));
            unavailableRows.add(chosenRowsHead.get(i) - (column - (i + 1)));
        }

        return !unavailableRows.contains(theLastChosenRow);
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.emptyList();
    }

    @Override
    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue) {
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.add(nextValue);
        return nextSolution;
    }

    @Override
    protected boolean isFullSolution(List<Integer> chosenRows) {
        return chosenRows.size() == n;
    }

}
