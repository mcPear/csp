package com.maciek;

import java.util.ArrayList;
import java.util.List;

public class QueenUtil {

    public static List<Integer> getAllAvailableRows(int n) {
        List<Integer> allAvailableRows = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            allAvailableRows.add(i);
        }
        return allAvailableRows;
    }

    public static void logProgress(int n, List<Integer> chosenRows) {
        if (chosenRows.size() == 1) {
            System.out.println("" + (chosenRows.get(0) * 100 / n) + "%");
        }
    }

}
