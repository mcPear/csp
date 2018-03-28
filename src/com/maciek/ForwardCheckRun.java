package com.maciek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForwardCheckRun extends CSPAlgorithmRun {

    public ForwardCheckRun(int n) {
        super(n);
    }

    public int run() {
        forwardCheck(Collections.emptyList(), getAllFullDomains());
        return solutions.size();
    }

    private void forwardCheck(List<Integer> chosenRows, List<List<Integer>> domains) {
        logProgress(chosenRows);

        if (isFullSolution(chosenRows)) {
            saveSolution(chosenRows);
        } else {
            List<List<Integer>> updatedDomains = getUpdatedDomains(chosenRows, domains);
            if (!containsEmptyDomain(updatedDomains)) {
                triggerForwardCheckForNextDomainRows(chosenRows, updatedDomains);
            }
        }

    }

    private List<List<Integer>> getUpdatedDomains(List<Integer> chosenRows, List<List<Integer>> domains) {
        if (chosenRows.isEmpty()) {
            return domains;
        }
        Integer theLastChosenRow = chosenRows.get(chosenRows.size() - 1);
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

    private List<List<Integer>> copyList2D(List<List<Integer>> list2D) {
        List<List<Integer>> result = new ArrayList<>();
        list2D.forEach(list -> result.add(new ArrayList<>(list)));
        return result;
    }

    private void triggerForwardCheckForNextDomainRows(List<Integer> chosenRows, List<List<Integer>> updatedDomains) {
        List<Integer> domainRows = updatedDomains.get(0);
        domainRows.forEach(domainRow -> {
            List<Integer> nextChosenRows = new ArrayList<>(chosenRows);
            nextChosenRows.add(domainRow);
            forwardCheck(nextChosenRows, updatedDomains);
        });
    }

    private boolean containsEmptyDomain(List<List<Integer>> domains) {
        return domains.stream().anyMatch(List::isEmpty);
    }

    private List<List<Integer>> getAllFullDomains() {
        List<List<Integer>> domains = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            domains.add(getAllAvailableRows());
        }
        return domains;
    }

}
