package chap2.section1;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class SelectionSort {
    public static void sort(Comparable[] arr) {
        int min = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            min = i;
            for (int j = i + 1; j < arr.length; ++j) {
                if (less(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            exch(arr, i, min);
        }
    }
}
