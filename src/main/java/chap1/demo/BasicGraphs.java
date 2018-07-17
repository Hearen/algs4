package chap1.demo;

import java.util.Arrays;

import lib.StdDraw;
import lib.StdRandom;

public class BasicGraphs {
    public static void main(String... args) {
//        testArrayGraph(false);
//        testArrayGraph(true);
        testVisualAccumulator();
    }

    private static void testVisualAccumulator() {
        final int N = 10_000;
        final double max = 1.0;
        VisualAccumulator a = new BasicGraphs().new VisualAccumulator(N, max);
        for (int i = 0; i < N; ++i) {
            a.add(StdRandom.uniform(0, max));
        }
        System.out.println(a);
    }

    class VisualAccumulator {
        double total;
        int count;
        public VisualAccumulator(int theCount, double max) {
            StdDraw.setXscale(0, theCount);
            StdDraw.setYscale(0, max);
            StdDraw.setPenRadius(max / 200);
        }

        public void add(double val) {
            total += val;
            count++;
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(count, val);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(count, mean());
        }

        public double mean() { return total / count; }

        @Override
        public String toString() {
            return String.format("Mean (%d values): %7.5f", count, mean());
        }
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
