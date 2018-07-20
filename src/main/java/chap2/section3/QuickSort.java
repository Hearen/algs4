package chap2.section3;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;
import static chap2.section1.SortUtil.shuffle;

import java.util.Arrays;

public class QuickSort {
    static int level = 0;

    public static void sort(Comparable[] arr) {
        shuffle(arr); // to avoid bad case, shuffle it first;
        sort(arr, 0, arr.length - 1);
    }

    /**
     * Warning: the worst case will tumble into StackOverflowError for deep recursive call;
     * @param arr
     * @param l
     * @param r
     */
    private static void sort(Comparable[] arr, int l, int r) {
        if (r - l < 15) {
            for (int i = l + 1; i <= r; ++i) {
                for (int k = i; k > l && less(arr[k], arr[k-1]); --k) {
                    exch(arr, k, k - 1);
                }
            }
            return;
        }
//        System.out.println("recursive level: " + level++);
        int j = partition(arr, l, r);
        sort(arr, l, j - 1); // easy to tumble into StackOverflowError when in bad case - partially sorted;
        sort(arr, j + 1, r);
    }

    private static int partition(Comparable[] arr, int l, int r) {
        int i = l;
        int j = r + 1;
        Comparable v = arr[l];
        while (true) {
            while (less(arr[++i], v)) if (i == r) break;
            while (less(v, arr[--j])) ;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, l, j);
        return j;
    }
}
