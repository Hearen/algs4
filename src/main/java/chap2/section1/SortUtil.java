package chap2.section1;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class SortUtil {
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isAscended(int[] arr) {
        return isAscended(Arrays.stream(arr).boxed().toArray(Integer[]::new));
    }

    public static boolean isAscended(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            if (less(arr[i], arr[i-1])) {
                System.out.println("Wrong Point: " + i + " => " + arr[i - 1] + " - " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void shuffle(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; ++i) {
            exch(arr, i, new Random().nextInt(len - i) + i);
        }
    }
}
