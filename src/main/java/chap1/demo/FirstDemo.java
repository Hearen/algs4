package chap1.demo;

import java.util.Arrays;

import lib.StdDraw;
import lib.StdIn;
import lib.StdOut;

public class FirstDemo {
    public static void main(String... args) {
        System.out.println("Hello world");
        drawSomeGraphs();
//        testStdIn();
        testMatrixMuplication(2);
        testSqrtUsingNewtonMethod(3000);
    }


    private static void drawSomeGraphs() {
        int N = 100;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N * N);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < 100; ++i) {
            StdDraw.point(i, i);
            StdDraw.point(i, i * i);
            StdDraw.point(i, i * Math.log(i));
        }
    }

    private static void testStdIn() {
        double sum = 0.0;
        int count = 0;
        while(!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            count++;
        }
        StdOut.printf("Average is %.5f\n", sum / count);
    }

    private static void testSqrtUsingNewtonMethod(double d) {
        double err = 1E-15;
        double t = d;
        while (Math.abs(t - d/t) > t * err) {
            t = (t + d/t) / 2.0;
        }
        System.out.println("Result: " + t + "\nMath.sqrt: " + Math.sqrt(d));
    }

    private static void testMatrixMuplication(int N) {
        int[][] a = new int[N][N];
        int[][] b = new int[N][N];
        int[][] c = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                a[i][j] = b[i][j] = 1;
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int k = 0; k < N; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        System.out.println(Arrays.deepToString(c));
    }
}
