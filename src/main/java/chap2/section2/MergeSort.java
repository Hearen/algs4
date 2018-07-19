package chap2.section2;

import java.util.Arrays;

import chap2.section1.SortUtil;

public class MergeSort {
    public static void sort(Comparable[] arr) {
//        Comparable[] aux = Arrays.copyOf(arr, arr.length);
//        topDownSort(arr, aux, 0, arr.length - 1);
        downUpSort(arr);
    }

    private static void topDownSort(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (l >= r) return ;
        int m = l + (r - l) / 2;
        topDownSort(aux, arr, l, m);
        topDownSort(aux, arr,m + 1, r);
        merge(aux, arr, l, m, r);
    }

    private static void downUpSort(Comparable[] arr) {
        int len = arr.length;
        Comparable[] aux = Arrays.copyOf(arr, len);
        for (int size = 1; size < len; size *= 2) {
            for (int l = 0; l < len - size; l += 2 * size) {
                merge(arr, aux, l, l + size - 1, Math.min(l + 2 * size - 1, len - 1));
            }
            Comparable[] t = arr;
            arr = aux;
            aux = t;
        }
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int k = l;
        while (k <= r) {
            if (j > r || SortUtil.less(arr[i], arr[j])) aux[k++] = arr[i++];
            else aux[k++] = arr[j++];
        }
    }
}
