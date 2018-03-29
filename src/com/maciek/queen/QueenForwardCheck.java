package com.maciek.queen;

import com.maciek.algorithm.ForwardCheck;

import java.util.Collections;
import java.util.List;

public class QueenForwardCheck extends ForwardCheck {

    public QueenForwardCheck(int n) {
        super(n);
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
    protected List<Integer> getInitialSolution(){
        return Collections.emptyList();
    }

    @Override
    protected boolean isFullSolution(List<Integer> chosenRows) {
        return chosenRows.size() == n;
    }

}
