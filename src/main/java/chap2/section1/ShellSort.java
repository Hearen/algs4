package chap2.section1;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class ShellSort {
    public static void sort(Comparable[] arr) {
        int len = arr.length;
        int h = 1;
        while (h < len / 3) h = 3 * h + 1;
        while (h > 0) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j-h]) < 0; j -= h) {
                    exch(arr, j, j-h);
                }
            }
            h /= 3;
        }
    }
}
