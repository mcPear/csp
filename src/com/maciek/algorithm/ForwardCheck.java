package com.maciek.algorithm;

import java.util.ArrayList;
import java.util.List;

public abstract class ForwardCheck extends CSPAlgorithm {

    public ForwardCheck(int n, Options options) {
        super(n, options);
    }

    public Result run() {
        long start = System.currentTimeMillis();
        forwardCheck(getInitialSolution(), getAllFullDomains());
        if (options.countExecutionTime) {
            executionTimeMillis = System.currentTimeMillis() - start;
        }
        return getResult();
    }

    protected abstract List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains);

    private void forwardCheck(List<Integer> subSolution, List<List<Integer>> domains) {
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
            List<List<Integer>> updatedDomains = getUpdatedDomains(subSolution, domains);
            if (!containsEmptyDomain(updatedDomains)) {
                triggerForwardCheckForNextDomainValues(subSolution, updatedDomains);
            } else if (options.countReturns) {
                returnsCount++;
            }
        }

    }

    protected List<List<Integer>> copyList2D(List<List<Integer>> list2D) {
        List<List<Integer>> result = new ArrayList<>();
        list2D.forEach(list -> result.add(new ArrayList<>(list)));
        return result;
    }

    private void triggerForwardCheckForNextDomainValues(List<Integer> subSolution, List<List<Integer>> updatedDomains) {
        List<Integer> domainValues = updatedDomains.get(0);
        domainValues.forEach(domainValue -> {
            List<Integer> nextSolution = getNextSolution(subSolution, domainValue);
            forwardCheck(nextSolution, updatedDomains);
        });
    }

    private boolean containsEmptyDomain(List<List<Integer>> domains) {
        return domains.stream().anyMatch(List::isEmpty);
    }

    protected abstract List<List<Integer>> getAllFullDomains();

}
