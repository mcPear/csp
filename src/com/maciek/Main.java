package com.maciek;

import com.maciek.latin.LatinBackTrack;
import com.maciek.latin.LatinForwardCheck;
import com.maciek.queen.QueenBackTrack;
import com.maciek.queen.QueenForwardCheck;

public class Main {

    public static void main(String[] args) {
        //System.out.println(new LatinBackTrack(6).run());
        System.out.println(new LatinForwardCheck(5).run());
        //compareTimes(13);
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    private static void compareTimes(int n) { //n=14 | remove logs
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ",");

            long start = now();
            int resultBT = new QueenBackTrack(i).run();
            System.out.print(now() - start);

            System.out.print(",");

            start = now();
            int resultFC = new QueenForwardCheck(i).run();
            System.out.print(now() - start + " | "+resultBT+","+resultFC);
            System.out.println("");
        }
    }

}
