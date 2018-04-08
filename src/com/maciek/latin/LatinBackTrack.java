package com.maciek.latin;

import com.maciek.algorithm.BackTrack;
import com.maciek.algorithm.Options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LatinBackTrack extends BackTrack {

    public LatinBackTrack(int n, Options options) {// n max=5
        super(n, options);
    }

    @Override
    protected boolean isValidSubSolution(List<Integer> subSolution) {
        if (equalsInitialSolution(subSolution)) {
            return true;
        }

        boolean isValid = true;

        for (int i = 0; i < n && isValid; i++) {
            List<Integer> row = subSolution.subList(i * n, n * (i + 1));
            if (!isValidValuesList(row)) {
                isValid = false;
            }

            List<Integer> column = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                column.add(subSolution.get(i + n * j));
            }

            if (!isValidValuesList(column)) {
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isValidValuesList(List<Integer> list) {
        List<Integer> listWithoutZeros = new ArrayList<>(list);
        listWithoutZeros.removeIf(val -> val == 0);
        return new HashSet<>(listWithoutZeros).size() == listWithoutZeros.size();
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.nCopies(n * n, 0);
    }

    private boolean equalsInitialSolution(List<Integer> solution) {
        HashSet<Integer> values = new HashSet<>(solution);
        return values.size() == 1 && values.contains(0);
    }

    @Override
    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue, int index) {
        int indexOfFirstZero = previousSolution.indexOf(0);
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.set(indexOfFirstZero, nextValue);
        return nextSolution;
    }

    @Override
    protected boolean isFullSolution(List<Integer> solution) {
        return !solution.contains(0);
    }

}
