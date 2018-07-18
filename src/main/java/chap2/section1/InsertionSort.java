package chap2.section1;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class InsertionSort {
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i; j > 0 && less(arr[j], arr[j-1]) < 0; --j) {
                exch(arr, j, j-1);
            }
        }
    }
}
