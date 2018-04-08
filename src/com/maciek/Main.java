package com.maciek;

import com.maciek.algorithm.Options;
import com.maciek.latin.LatinForwardCheck;

public class Main {

    private static Options options = new Options(false, true, true,
            true, true, true, false);

    public static void main(String[] args) {
        //System.out.println(new LatinBackTrack(6).run());
        System.out.println(new LatinForwardCheck(28, options).run());
        //System.out.println(new LatinBackTrack(5, options).run());
        //compareTimes(13);
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    private static void compareTimes(int n) { //n=14 | remove logs
//        for (int i = 1; i <= n; i++) {
//            System.out.print(i + ",");
//
//            long start = now();
//            int resultBT = new QueenBackTrack(i, options).run();
//            System.out.print(now() - start);
//
//            System.out.print(",");
//
//            start = now();
//            int resultFC = new QueenForwardCheck(i, options).run();
//            System.out.print(now() - start + " | " + resultBT + "," + resultFC);
//            System.out.println("");
//        }
    }

}
