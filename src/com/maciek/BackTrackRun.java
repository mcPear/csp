package com.maciek;

import java.util.*;

public class BackTrackRun {

    private final int n;
    private final List<List<Integer>> solutions;

    public BackTrackRun(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
    }

    public int run() {
        backTrack(Collections.emptyList());
        return solutions.size();
    }

    private void backTrack(List<Integer> chosenRows) {
        QueenUtil.logProgress(n, chosenRows);

        if (isValidSubSolution(chosenRows)) {
            if (isFullSolution(chosenRows)) {
                saveSolution(chosenRows);
            } else {
                triggerBackTracksForFreeRows(chosenRows);
            }
        }

    }

    private void triggerBackTracksForFreeRows(List<Integer> chosenRows) {
        List<Integer> freeRows = getFreeRows(chosenRows);
        freeRows.forEach(freeRow -> {
            List<Integer> nextChosenRows = new ArrayList<>(chosenRows);
            nextChosenRows.add(freeRow);
            backTrack(nextChosenRows);
        });
    }

    private boolean isValidSubSolution(List<Integer> chosenRows) {
        if (chosenRows.isEmpty()) {
            return true;
        }
        List<Integer> chosenRowsHead = new ArrayList<>(chosenRows).subList(0, chosenRows.size() - 1);
        Integer theLastChosenRow = chosenRows.get(chosenRows.size() - 1);
        int column = chosenRows.size();

        Set<Integer> unavailableRows = new HashSet<>(chosenRowsHead);
        for (int i = 0; i < chosenRowsHead.size(); i++) {
            unavailableRows.add(chosenRowsHead.get(i) + (column - (i + 1)));
            unavailableRows.add(chosenRowsHead.get(i) - (column - (i + 1)));
        }

        return !unavailableRows.contains(theLastChosenRow);
    }

    private List<Integer> getFreeRows(List<Integer> chosenRows) {
        List<Integer> result = QueenUtil.getAllAvailableRows(n);
        result.removeAll(chosenRows);
        return result;
    }

    private void saveSolution(List<Integer> chosenRows) {
        solutions.add(chosenRows);
    }

    private boolean isFullSolution(List<Integer> chosenRows) {
        return chosenRows.size() == n;
    }

}
