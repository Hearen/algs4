package chap1.demo;

import java.util.Arrays;

import lib.In;
import lib.StdIn;
import lib.StdRandom;
import util.IoUtils;

public class BinarySearch {
    public static int rank(int key, int[] arr) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("array has to be in order");
        }
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid]) hi = mid - 1;
            else if (key > arr[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    private static boolean isSorted(int[] arr) {
        boolean isAscending = true;
        boolean isDescending = true;
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i-1] > arr[i]) {
                isAscending = false;
            }
            if (arr[i-1] < arr[i]) {
                isDescending = false;
            }
        }
        return isAscending || isDescending;
    }

    private static void smallInputTest() {
        int N = 20;
        int max = 100;
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = StdRandom.uniform(max);
        }
        Arrays.sort(arr);
        while(!StdIn.isEmpty()) {
            int a = StdIn.readInt();
            if (rank(a, arr) > -1) {
                System.out.println(a + " in " + Arrays.toString(arr));
            } else {
                System.out.println(a + " not found");
            }
        }
    }

    public static void main(String... args) {
//        smallInputTest();
        int[] whiteList = In.readInts(IoUtils.getResourcePath("largeW.txt"));
        Arrays.sort(whiteList);
        int[] testList = In.readInts(IoUtils.getResourcePath("largeT.txt"));
        for (int a : testList) {
            if (rank(a, whiteList) < 0) {
                System.out.println(a);
            }
        }
    }
}
