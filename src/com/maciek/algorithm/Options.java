package com.maciek.algorithm;

public class Options {

    public final boolean logProgress;
    public final boolean countRecursiveCalls;
    public final boolean countReturns;
    public final boolean stopAtFirstSolution;
    public final boolean countExecutionTime;
    public final boolean useMinimumDomainHeuristic;
    public final boolean useMaximumDomainHeuristic;
    public final boolean useFrequentVariableValueHeuristic;
    public final boolean useRandomVariableValueHeuristic;

    public Options(boolean logProgress, boolean countRecursiveCalls, boolean countReturns, boolean stopAtFirstSolution,
                   boolean countExecutionTime, boolean useMinimumDomainHeuristic, boolean useMaximumDomainHeuristic,
                   boolean useFrequentVariableValueHeuristic, boolean useRandomVariableValueHeuristic) {
        this.logProgress = logProgress;
        this.countRecursiveCalls = countRecursiveCalls;
        this.countReturns = countReturns;
        this.stopAtFirstSolution = stopAtFirstSolution;
        this.countExecutionTime = countExecutionTime;
        this.useMinimumDomainHeuristic = useMinimumDomainHeuristic;
        this.useMaximumDomainHeuristic = useMaximumDomainHeuristic;
        this.useFrequentVariableValueHeuristic = useFrequentVariableValueHeuristic;
        this.useRandomVariableValueHeuristic = useRandomVariableValueHeuristic;
    }
}
