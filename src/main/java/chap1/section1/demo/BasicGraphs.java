package chap1.section1.demo;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

import lib.StdDraw;
import lib.StdRandom;

public class BasicGraphs {
    public static void main(String... args) {
//        testArrayGraph(false);
        testArrayGraph(true);
//        testVisualAccumulator();
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

    public class VisualAccumulator {
        private final static double THIN_PEN = 0.001;
        private final static double NORMAL_PEN = 0.003;
        private final static double MARK_PEN = 0.01;
        double total;
        double max = Double.MIN_VALUE;
        int count;
        public VisualAccumulator(int X, double Y) {
            StdDraw.setXscale(0, X);
            StdDraw.setYscale(0, Y);
            StdDraw.setPenRadius(0.003);
        }

        public void add(double val) {
            max = Math.max(val, max);
            total += val;
            count++;
            System.out.println("val: " + val + " max: " + max + " total: " + total);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(count, val);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(count, mean());
        }

        public double mean() { return total / (1.0 * count); }

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
