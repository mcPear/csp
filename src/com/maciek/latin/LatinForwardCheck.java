package com.maciek.latin;

import com.maciek.algorithm.ForwardCheck;
import com.maciek.algorithm.Options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//count all domains
public class LatinForwardCheck extends ForwardCheck {

    public LatinForwardCheck(int n, Options options) {
        super(n, options);
    }

    @Override
    protected List<List<Integer>> getUpdatedDomains(List<Integer> subSolution, List<List<Integer>> domains, int index) {
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


    @Override
    protected boolean equalsInitialSolution(List<Integer> solution) {
        return isAllZeros(solution);
    }

    @Override
    protected List<Integer> getInitialSolution() {
        return Collections.nCopies(n * n, 0);
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
