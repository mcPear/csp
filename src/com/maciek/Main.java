package com.maciek;

import com.maciek.algorithm.Options;
import com.maciek.algorithm.Result;
import com.maciek.latin.LatinBackTrack;
import com.maciek.latin.LatinForwardCheck;
import com.maciek.queen.QueenBackTrack;
import com.maciek.queen.QueenForwardCheck;

public class Main {

    private static Options options = new Options(false, true, true,
            false, true,
            false, false,
            false, false);

    public static void main(String[] args) {
        //System.out.println(new LatinBackTrack(6).run());
        //System.out.println(new LatinForwardCheck(8, options).run());
        //System.out.println(new LatinBackTrack(50, options).run());
        //System.out.println(new QueenBackTrack(14, options).run());
        //System.out.println(new QueenForwardCheck(30, options).run());
        compareTimes(30000);
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    private static void compareTimes(int n) {
//        System.out.println("0,0,0");
        for (int i = 1; i <= n; i++) {
//            System.out.print(i + ",");

            Result result = new LatinForwardCheck(i, options).run();
//            System.out.print(result.recursiveCallsCount+","+result.returnsCount+","+result.executionTimeMillis);
            System.out.print(result.toString());

//            System.out.print(",");
//
//            Result resultFC2 = new LatinForwardCheck(i, options2).run();
//            System.out.print(resultFC2.recursiveCallsCount+","+resultFC2.returnsCount+","+resultFC2.executionTimeMillis);
//
//            System.out.print(",");
//
//            Result resultFC3 = new LatinForwardCheck(i, options3).run();
//            System.out.print(resultFC3.recursiveCallsCount+","+resultFC3.returnsCount+","+resultFC3.executionTimeMillis);
            System.out.println("");
        }
    }

}
