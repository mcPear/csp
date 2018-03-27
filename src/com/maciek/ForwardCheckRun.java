package com.maciek;

import java.util.*;

public class ForwardCheckRun {

//    private static final int FIRST_COLUMN = 1;
//    private final int n;
//    private final List<List<Integer>> solutions;
//    private final List<Integer> allAvailableRows;
//    private final List<List<Integer>> domains;
//
//    public ForwardCheckRun(int n) {
//        this.n = n;
//        this.solutions = new ArrayList<>();
//        this.allAvailableRows = QueenUtil.getAllAvailableRows(n);
//        this.domains = getAllFullDomains();
//    }
//
//    public int run() {
//        forwardCheck(allAvailableRows, Collections.emptyList(), FIRST_COLUMN);
//        return solutions.size();
//    }
//
//    private void forwardCheck(List<Integer> domains, List<Integer> chosenRows, int column) {
//        QueenUtil.logProgress(n, chosenRows);
//
//
//
//        if (availableRows.isEmpty()) {
//            return;
//        }
//
//        if (column == n) {
//            saveSolution(chosenRows, availableRows);
//            return;
//        }
//
//        for (Integer currentRow : availableRows) {
//            List<Integer> nextAvailableRows = findNextAvailableRows(chosenRows, currentRow, column + 1);
//            if (nextAvailableRows.size() > 0) {
//                List<Integer> nextChosenRows = copyListAndAppendElement(chosenRows, currentRow);
//                forwardCheck(nextAvailableRows, nextChosenRows, column + 1);
//            }
//        }
//
//    }
//
//    private List<Integer> findNextAvailableRows(List<Integer> chosenRows, Integer nextRow, int nextColumn) {
//        Set<Integer> unavailableRows = new HashSet<>(chosenRows);
//        for (int i = 0; i < chosenRows.size(); i++) {
//            unavailableRows.add(chosenRows.get(i) + (nextColumn - (i + 1)));
//            unavailableRows.add(chosenRows.get(i) - (nextColumn - (i + 1)));
//        }
//        unavailableRows.add(nextRow);
//        unavailableRows.add(nextRow + 1);
//        unavailableRows.add(nextRow - 1);
//        List<Integer> nextAvailableRows = new ArrayList<>(allAvailableRows);
//        nextAvailableRows.removeAll(unavailableRows);
//        return nextAvailableRows;
//    }
//
//    private void saveSolution(List<Integer> chosenRows, List<Integer> availableRows) {
//        if (availableRows.size() != 1) {
//            throw new IllegalArgumentException(
//                    "Can not allow solution. The number of available rows is "
//                            + availableRows.size()
//                            + " which is not 1 meaning the last available row");
//        }
//        solutions.add(copyListAndAppendElement(chosenRows, availableRows.get(0)));
//    }
//
//    private <T> List<T> copyListAndAppendElement(List<T> list, T element) {
//        List<T> copy = new ArrayList<>(list);
//        copy.add(element);
//        return copy;
//    }
//
//    private List<List<Integer>> getAllFullDomains() {
//        List<List<Integer>> domains = new ArrayList<>();
//        for (int i = 1; i <= n; i++) {
//            domains.add(QueenUtil.getAllAvailableRows(n));
//        }
//        return domains;
//    }

}
