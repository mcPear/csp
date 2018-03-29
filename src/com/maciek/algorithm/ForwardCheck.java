package com.maciek.algorithm;

import java.util.ArrayList;
import java.util.List;

public abstract class ForwardCheck extends CSPAlgorithm {

    public ForwardCheck(int n) {
        super(n);
    }

    public int run() {
        forwardCheck(getInitialSolution(), getAllFullDomains());
        return foundSolutions.size();
    }

    protected abstract List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains);

    private void forwardCheck(List<Integer> subSolution, List<List<Integer>> domains) {
        //logProgress(subSolution);

        if (isFullSolution(subSolution)) {
            saveSolution(subSolution);
        } else {
            List<List<Integer>> updatedDomains = getUpdatedDomains(subSolution, domains);
            if (!containsEmptyDomain(updatedDomains)) {
                triggerForwardCheckForNextDomainValues(subSolution, updatedDomains);
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
            List<Integer> nextSubSolution = new ArrayList<>(subSolution);
            nextSubSolution.add(domainValue);
            forwardCheck(nextSubSolution, updatedDomains);
        });
    }

    private boolean containsEmptyDomain(List<List<Integer>> domains) {
        return domains.stream().anyMatch(List::isEmpty);
    }

    private List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            domains.add(allKnownValues);
        }
        return domains;
    }

}
