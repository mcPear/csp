package com.maciek.algorithm;

public class Options {

    public final boolean logProgress;
    public final boolean countRecursiveCalls;
    public final boolean countReturns;
    public final boolean stopAtFirstSolution;
    public final boolean countExecutionTime;
    public final boolean useMinimumDomainHeuristic;
    public final boolean useVariableValueHeuristic;

    public Options(boolean logProgress, boolean countRecursiveCalls, boolean countReturns, boolean stopAtFirstSolution,
                   boolean countExecutionTime, boolean useMinimumDomainHeuristic, boolean useVariableValueHeuristic) {
        this.logProgress = logProgress;
        this.countRecursiveCalls = countRecursiveCalls;
        this.countReturns = countReturns;
        this.stopAtFirstSolution = stopAtFirstSolution;
        this.countExecutionTime = countExecutionTime;
        this.useMinimumDomainHeuristic = useMinimumDomainHeuristic;
        this.useVariableValueHeuristic = useVariableValueHeuristic;
    }
}
