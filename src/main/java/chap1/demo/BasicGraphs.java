package chap1.demo;

import java.util.Arrays;

import lib.StdDraw;
import lib.StdRandom;

public class BasicGraphs {
    public static void main(String... args) {
//        testArrayGraph(false);
        testArrayGraph(true);
    }

    private static void testArrayGraph(boolean isSorted) {
        int N = 50;
        Double[] arr = new Double[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = StdRandom.uniform();
        }
        if (isSorted) {
            Arrays.sort(arr);
        }
        drawArray(arr);
    }

    private static <T extends Number> void drawArray(T[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            double x = i * 1.0 / arr.length;
            double y = arr[i].doubleValue() / 2.0;
            double rw = 0.4 / arr.length;
            double rh = arr[i].doubleValue() / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
