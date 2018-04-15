package com.maciek.queen;

import com.maciek.algorithm.CSPAlgorithm;
import com.maciek.algorithm.Options;
import com.maciek.algorithm.Result;

import java.util.*;

//count all domains
public class QueenForwardCheck extends CSPAlgorithm {

    public QueenForwardCheck(int n, Options options) {
        super(n, options);
    }

    public Result run() {
        long start = System.currentTimeMillis();
        forwardCheck(getInitialSolution(), getAllFullDomains(), -1);
        if (options.countExecutionTime) {
            executionTimeMillis = System.currentTimeMillis() - start;
        }
        return getResult();
    }

    private void forwardCheck(List<Integer> subSolution, List<List<Integer>> domains, int index) {
        if (options.logProgress) {
            logProgress(subSolution);
        }
        if (options.countRecursiveCalls) recursiveCallsCount++;
        if (options.stopAtFirstSolution && !foundSolutions.isEmpty()) {
            return;
        }

        if (isFullSolution(subSolution)) {
            saveSolution(subSolution);
        } else {
            List<List<Integer>> updatedDomains = getUpdatedDomains(subSolution, domains, index);
            if (!containsEmptyDomainOnEmptyVariable(subSolution, updatedDomains)) {
                triggerForwardCheckForNextDomainValues(subSolution, updatedDomains);
            } else if (options.countReturns) {
                returnsCount++;
            }
        }

    }

    private void triggerForwardCheckForNextDomainValues(List<Integer> subSolution, List<List<Integer>> updatedDomains) {
        int nextVariableDomainIndex = getNextVariableDomainIndex(subSolution, updatedDomains);
        List<Integer> domainValues = updatedDomains.get(nextVariableDomainIndex);
        if (options.useRandomVariableValueHeuristic) {
            Collections.shuffle(domainValues);
        }
        domainValues.forEach(domainValue -> {
            List<Integer> nextSolution = getNextSolution(subSolution, domainValue, nextVariableDomainIndex);
            forwardCheck(nextSolution, updatedDomains, nextVariableDomainIndex);
        });
    }

    private void sortDescendingFrequentDomainValues(List<Integer> values, List<Integer> subSolution) {
        Comparator descendingFrequencyComparator = Collections.reverseOrder(Comparator.comparing(val -> Collections.frequency(subSolution, val)));
        values.sort(descendingFrequencyComparator);
    }

    private void sortAscendingFrequentDomainValues(List<Integer> values, List<Integer> subSolution) {
        Comparator ascendingFrequencyComparator = Comparator.comparing(val -> Collections.frequency(subSolution, val));
        values.sort(ascendingFrequencyComparator);
    }

    private int getNextVariableDomainIndex(List<Integer> subSolution, List<List<Integer>> updatedDomains) {
        if (options.useMinimumDomainHeuristic) {
            int minimumDomainIndex = -1;
            List<Integer> minimumDomain = null;
            for (int i = 0; i < updatedDomains.size(); i++) {
                if (subSolution.get(i) == 0 && (minimumDomain == null || updatedDomains.get(i).size() < minimumDomain.size())) {
                    minimumDomainIndex = i;
                    minimumDomain = updatedDomains.get(i);
                }
            }
            return minimumDomainIndex;
        } else if (options.useMaximumDomainHeuristic) {
            int maximumDomainIndex = -1;
            List<Integer> maximumDomain = null;
            for (int i = 0; i < updatedDomains.size(); i++) {
                if (subSolution.get(i) == 0 && (maximumDomain == null || updatedDomains.get(i).size() > maximumDomain.size())) {
                    maximumDomainIndex = i;
                    maximumDomain = updatedDomains.get(i);
                }
            }
            return maximumDomainIndex;
        } else {
            return subSolution.indexOf(0);
        }
    }

    private boolean equalsInitialSolution(List<Integer> solution) {
        HashSet<Integer> values = new HashSet<>(solution);
        return values.size() == 1 && values.contains(0);
    }

    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer value, int index) {
        //int indexOfFirstZero = previousSolution.indexOf(0);
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.set(index, value);
        return nextSolution;
    }

    protected List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains, int index) { //FIXME update all domains
        if (equalsInitialSolution(subSolution)) {
            return domains;
        }

        int theLastValueIndex = index;
        Integer theLastValue = subSolution.get(theLastValueIndex);
        List<List<Integer>> updatedDomains = copyList2D(domains);

        //update cell
        updatedDomains.get(theLastValueIndex).clear();

        //update columns at right
        int nextIndex = theLastValueIndex + 1;
        for (int i = theLastValueIndex + 1; i < updatedDomains.size(); i++) {
            List<Integer> domain = updatedDomains.get(i);
            domain.remove(theLastValue);
            domain.remove((Integer) (theLastValue + (i - nextIndex + 1)));
            domain.remove((Integer) (theLastValue - (i - nextIndex + 1)));
        }

        //update columns at left
        int previousIndex = theLastValueIndex - 1;
        for (int i = theLastValueIndex - 1; i >= 0; i--) {
            List<Integer> domain = updatedDomains.get(i);
            domain.remove(theLastValue);
            domain.remove((Integer) (theLastValue + (i - (previousIndex) + 1)));
            domain.remove((Integer) (theLastValue - (i - (previousIndex) + 1)));
        }
        return updatedDomains;

        /*
                int theLastValueIndex = index;
        Integer theLastValue = subSolution.get(theLastValueIndex);
        List<List<Integer>> updatedDomains = copyList2D(domains);

        //update cell
        updatedDomains.get(theLastValueIndex).clear();

        //update row
        int firstIndexOfRow = theLastValueIndex - theLastValueIndex % n;
        int firstIndexOfNextRow = n - theLastValueIndex % n + theLastValueIndex;
        List<List<Integer>> domainsRow = updatedDomains.subList(firstIndexOfRow, firstIndexOfNextRow);
        domainsRow.forEach(domain -> domain.remove(theLastValue));

        //update column
        for (int i = 0; i < n; i++) {
            int columnVariableIndex = theLastValueIndex % n + i * n;
            updatedDomains.get(columnVariableIndex).remove(theLastValue);
        }

        return updatedDomains;
         */
    }

    @Override
    protected boolean isFullSolution(List<Integer> solution) {
        return !solution.contains(0);
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.nCopies(n, 0);
    }

    protected List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            domains.add(allKnownValues);
        }
        return domains;
    }

    private boolean containsEmptyDomainOnEmptyVariable(List<Integer> subSolution, List<List<Integer>> domains) {
        for (int i = 0; i < domains.size(); i++) {
            if (subSolution.get(i) == 0 && domains.get(i).size() == 0) {
                return true;
            }
        }
        return false;
    }

    private List<List<Integer>> copyList2D(List<List<Integer>> list2D) {
        List<List<Integer>> result = new ArrayList<>();
        list2D.forEach(list -> result.add(new ArrayList<>(list)));
        return result;
    }

}
