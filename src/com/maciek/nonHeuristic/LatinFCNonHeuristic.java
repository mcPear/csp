package com.maciek.nonHeuristic;

import com.maciek.algorithm.Options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LatinFCNonHeuristic extends ForwardCheckNonHeuristic {

    public LatinFCNonHeuristic(int n, Options options) {
        super(n, options);
    }

    @Override
    protected List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains) {
        if (equalsInitialSolution(subSolution)) {
            return domains;
        }
        int theLastValueIndex = subSolution.indexOf(0) - 1;
        Integer theLastValue = subSolution.get(theLastValueIndex);
        List<List<Integer>> updatedDomains = copyList2D(domains);
        updatedDomains.remove(0);

        //update row
        int zerosInTheLastColumnCount = n - theLastValueIndex % n - 1;
        List<List<Integer>> zerosInTheLastColumnDomains = updatedDomains.subList(0, zerosInTheLastColumnCount);
        zerosInTheLastColumnDomains.forEach(domain -> domain.remove(theLastValue));

        //update column
        List<List<Integer>> fullZeroColumnsDomains = updatedDomains.subList(zerosInTheLastColumnCount, updatedDomains.size());
        int columnValueIndex = theLastValueIndex % n;
        while (columnValueIndex < fullZeroColumnsDomains.size()) {
            fullZeroColumnsDomains.get(columnValueIndex).remove(theLastValue);
            columnValueIndex += n;
        }

        return updatedDomains;
    }

    private boolean equalsInitialSolution(List<Integer> solution) {
        HashSet<Integer> values = new HashSet<>(solution);
        return values.size() == 1 && values.contains(0);
    }

    @Override
    protected boolean isFullSolution(List<Integer> solution) {
        return !solution.contains(0);
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.nCopies(n * n, 0);
    }

    @Override
    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue) {
        int indexOfFirstZero = previousSolution.indexOf(0);
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.set(indexOfFirstZero, nextValue);
        return nextSolution;
    }

    @Override
    protected List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n * n; i++) {
            domains.add(allKnownValues);
        }
        return domains;
    }
}