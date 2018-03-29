package com.maciek.queen;

import com.maciek.algorithm.ForwardCheck;
import com.maciek.algorithm.Options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueenForwardCheck extends ForwardCheck {

    public QueenForwardCheck(int n, Options options) {
        super(n, options);
    }

    @Override
    protected List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains) {
        if (subSolution.isEmpty()) {
            return domains;
        }
        Integer theLastChosenRow = subSolution.get(subSolution.size() - 1);
        List<List<Integer>> updatedDomains = copyList2D(domains);
        updatedDomains.remove(0);
        for (int i = 0; i < updatedDomains.size(); i++) {
            List<Integer> domain = updatedDomains.get(i);
            domain.remove(theLastChosenRow);
            domain.remove((Integer) (theLastChosenRow + (i + 1)));
            domain.remove((Integer) (theLastChosenRow - (i + 1)));
        }
        return updatedDomains;
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.emptyList();
    }

    @Override
    protected boolean isFullSolution(List<Integer> chosenRows) {
        return chosenRows.size() == n;
    }

    @Override
    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue) {
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.add(nextValue);
        return nextSolution;
    }

    @Override
    protected List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            domains.add(allKnownValues);
        }
        return domains;
    }

}
