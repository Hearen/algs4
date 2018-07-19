package chap2.section3;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class QuickSort {
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {
        if (l >= r) return ;
        int j = partition(arr, l, r);
        sort(arr, l, j - 1); // easy to tumble into StackOverflowError;
        sort(arr, j + 1, r);
    }

    private static int partition(Comparable[] arr, int l, int r) {
        int i = l;
        int j = r + 1;
        Comparable v = arr[l];
        while (true) {
            while (i < r && !less(v, arr[++i])) ;
            while (j > l && !less(arr[--j], v)) ;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, l, j);
        return j;
    }
}
