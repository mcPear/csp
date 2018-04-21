package com.maciek;

import com.maciek.algorithm.Options;
import com.maciek.algorithm.Result;
import com.maciek.latin.LatinForwardCheck;
import com.maciek.queen.QueenForwardCheck;

import java.util.*;

public class Main {

    private static Set<Integer> findDuplicatesHelper = new HashSet<>();


    private static Options options = new Options(false, true, true,
            false, true,
            false, false,
            false, false, true);
    //none 12,641975,244668,1792,14200
    //min 12,416829,101882,1457,14200
    //max 12,2784851,1549562,4338,14200
    //random 12,641975,244668,2326,14200
    //e2m 12,641975,244668,2003,14200
    private static Options options2 = new Options(false, true, true,
            false, true,
            false, false,
            false, false, false);

    public static void main(String[] args) {
        //System.out.println(new LatinBackTrack(6).run());
        //System.out.println(new LatinForwardCheck(8, options).run());
        //System.out.println(new LatinBackTrack(50, options).run());
        //System.out.println(new QueenBackTrack(14, options).run());
//        Result result = new QueenForwardCheck(7, options).run();
//        Result resultGood = new QueenForwardCheck(7, options2).run();
//        List<List<Integer>> badResults = new ArrayList<>(result.foundSolutions);
//        badResults.removeAll(resultGood.foundSolutions);
        //System.out.println(new QueenForwardCheck(7, options).run());
        compareTimes(30000);
//        List<Integer> lista = new ArrayList<>(Arrays.asList());
//        QueenForwardCheck.orderFromMedianToEdge(lista);
//        lista.forEach(el -> System.out.print(el+" "));
        return;
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    private static void compareTimes(int n) {
//        System.out.println("0,0,0");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ",");

            Result result = new QueenForwardCheck(i, options).run();
            System.out.print(result.recursiveCallsCount + "," + result.returnsCount + "," + result.executionTimeMillis+","+result.foundSolutionsCount);
//            result.foundSolutions.get(0).forEach(s -> System.out.print(s + ","));

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

    private static boolean isValidValuesList(List<Integer> list) {
        return !hasDuplicateIgnoringZeros(list);
    }

    private static boolean hasDuplicateIgnoringZeros(Iterable<Integer> all) {
        findDuplicatesHelper.clear();
        for (Integer each: all) if (each!=0 && !findDuplicatesHelper.add(each)) return true;
        return false;
    }


}
