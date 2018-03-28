package com.maciek;

import java.util.ArrayList;
import java.util.List;

public abstract class CSPAlgorithmRun {

    protected final int n;
    protected final List<List<Integer>> solutions;

    protected CSPAlgorithmRun(int n) {
        this.n = n;
        this.solutions = new ArrayList<>();
    }

    public List<Integer> getAllAvailableRows() {
        List<Integer> allAvailableRows = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            allAvailableRows.add(i);
        }
        return allAvailableRows;
    }

    public void logProgress(List<Integer> chosenRows) {
        if (chosenRows.size() == 1) {
            System.out.println("" + (chosenRows.get(0) * 100 / n) + "%");
        }
    }

    public boolean isFullSolution(List<Integer> chosenRows) {
        return chosenRows.size() == n;
    }

    public void saveSolution(List<Integer> chosenRows) {
        solutions.add(chosenRows);
    }

}
