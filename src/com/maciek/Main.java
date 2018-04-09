package com.maciek;

import com.maciek.algorithm.Options;
import com.maciek.latin.LatinBackTrack;
import com.maciek.latin.LatinForwardCheck;
import com.maciek.queen.QueenBackTrack;
import com.maciek.queen.QueenForwardCheck;

public class Main {

    private static Options options = new Options(true, true, true,
            false, true, true, false);

    public static void main(String[] args) {
        //System.out.println(new LatinBackTrack(6).run());
        //System.out.println(new LatinForwardCheck(8, options).run());
        //System.out.println(new LatinBackTrack(5, options).run());
        //System.out.println(new QueenBackTrack(14, options).run());
        //System.out.println(new QueenForwardCheck(15, options).run());
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
