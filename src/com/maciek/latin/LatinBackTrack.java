package com.maciek.latin;

import com.maciek.algorithm.BackTrack;
import com.maciek.algorithm.Options;

import java.util.*;

//count domains of unassigned variables, but remember all
public class LatinBackTrack extends BackTrack {

    private List<Integer> subsolutionColumn = new ArrayList<>();
    private Set<Integer> findDuplicatesHelper = new HashSet<>();

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

            subsolutionColumn.clear();
            for (int j = 0; j < n; j++) {
                subsolutionColumn.add(subSolution.get(i + n * j));
            }

            if (!isValidValuesList(subsolutionColumn)) {
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isValidValuesList(List<Integer> list) {
        return !hasDuplicateIgnoringZeros(list);
    }

    private boolean hasDuplicateIgnoringZeros(Iterable<Integer> all) {
        findDuplicatesHelper.clear();
        for (Integer each: all) if (each!=0 && !findDuplicatesHelper.add(each)) return true;
        return false;
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.nCopies(n * n, 0);
    }

    private boolean equalsInitialSolution(List<Integer> solution) {
        return isAllZeros(solution);
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
