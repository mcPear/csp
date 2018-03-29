package com.maciek.algorithm;

public class Options {

    public final boolean logProgress;
    public final boolean countRecursiveCalls;
    public final boolean countReturns;
    public final boolean stopAtFirstSolution;
    public final boolean countExecutionTime;

    public Options(boolean logProgress, boolean countRecursiveCalls, boolean countReturns, boolean stopAtFirstSolution, boolean countExecutionTime) {
        this.logProgress = logProgress;
        this.countRecursiveCalls = countRecursiveCalls;
        this.countReturns = countReturns;
        this.stopAtFirstSolution = stopAtFirstSolution;
        this.countExecutionTime = countExecutionTime;
    }
}
