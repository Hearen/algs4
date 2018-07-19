package chap2.section1;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

import java.awt.*;

public class InsertionSort {
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int j = i;
            for (; j > 0 && less(arr[j], arr[j-1]); --j) {
                exch(arr, j, j-1);
            }
//            ArrayViewer.drawRealtimeArray(arr, 50_000, j, i, Color.RED);
        }
    }
}
