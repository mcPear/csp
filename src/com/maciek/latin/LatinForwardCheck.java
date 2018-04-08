package com.maciek.latin;

import com.maciek.algorithm.CSPAlgorithm;
import com.maciek.algorithm.Options;
import com.maciek.algorithm.Result;

import java.util.*;
import java.util.stream.Collectors;

public class LatinForwardCheck extends CSPAlgorithm {

    public LatinForwardCheck(int n, Options options) {
        super(n, options);
    }

    List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains, int index) {
        if (equalsInitialSolution(subSolution)) {
            return domains;
        }
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
        if (options.useVariableValueHeuristic) {
            sortDomainValues(domainValues, subSolution);
        }
        domainValues.forEach(domainValue -> {
            List<Integer> nextSolution = getNextSolution(subSolution, domainValue, nextVariableDomainIndex);
            forwardCheck(nextSolution, updatedDomains, nextVariableDomainIndex);
        });
    }

    private void sortDomainValues(List<Integer> values, List<Integer> subSolution) {
        Comparator descendingFrequencyComparator = Collections.reverseOrder(Comparator.comparing(val -> Collections.frequency(subSolution, val)));
        values.sort(descendingFrequencyComparator);
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
        } else {
            return subSolution.indexOf(0);
        }
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

    protected List<Integer> getNextSolution(List<Integer> previousSolution, Integer nextValue, int index) {
        //int indexOfFirstZero = previousSolution.indexOf(0);
        List<Integer> nextSolution = new ArrayList<>(previousSolution);
        nextSolution.set(index, nextValue);
        return nextSolution;
    }

    List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n * n; i++) {
            domains.add(allKnownValues);
        }
        return domains;
    }

    public Result run() {
        long start = System.currentTimeMillis();
        forwardCheck(getInitialSolution(), getAllFullDomains(), -1);
        if (options.countExecutionTime) {
            executionTimeMillis = System.currentTimeMillis() - start;
        }
        return getResult();
    }

    List<List<Integer>> copyList2D(List<List<Integer>> list2D) {
        List<List<Integer>> result = new ArrayList<>();
        list2D.forEach(list -> result.add(new ArrayList<>(list)));
        return result;
    }

    private boolean containsEmptyDomainOnEmptyVariable(List<Integer> subSolution, List<List<Integer>> domains) {
        for (int i = 0; i < domains.size(); i++) {
            if (subSolution.get(i) == 0 && domains.get(i).size() == 0) {
                return true;
            }
        }
        return false;
    }

}
