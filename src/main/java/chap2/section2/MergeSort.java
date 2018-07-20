package chap2.section2;

import static chap2.section1.SortUtil.less;

import chap2.section1.SortUtil;

public class MergeSort {
    public static void sort(Comparable[] arr) {
//        Comparable[] aux = Arrays.copyOf(arr, arr.length);
//        topDownSort(arr, aux, 0, arr.length - 1);
        downUpSort(arr);
    }

    private static void topDownSort(Comparable[] arr, Comparable[] aux, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        topDownSort(aux, arr, l, m);
        topDownSort(aux, arr, m + 1, r);
        merge(aux, arr, l, m, r);
    }

    private static void downUpSort(Comparable[] arr) {
        int len = arr.length;
        Comparable[] aux = new Comparable[len];
        Comparable[] origin = arr;
        for (int size = 1; size < len; size *= 2) {
            for (int l = 0; l < len; l += 2 * size) {
                if (l < len - size) {
                    merge(arr, aux, l, l + size - 1, Math.min(l + 2 * size - 1, len - 1));
                } else { // make sure aux is completely sorted even the leftover is ignored in merge stage;
                    while (l < len) aux[l] = arr[l++];
                }
            }
            Comparable[] t = arr;
            arr = aux;
            aux = t;
        }
        if (origin != arr) { // restore back to the original array if it's changed to the locally temp array;
            for (int i = 0; i < len; ++i) {
                origin[i] = arr[i];
            }
        }
    }

    /**
     * Merge the arr to the aux making the aux is completely sorted;
     *
     * @param arr arr is an array while [l, m] is sorted and (m, r] is also sorted;
     * @param aux
     * @param l
     * @param m
     * @param r
     */
    private static void merge(Comparable[] arr, Comparable[] aux, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int k = l;
        while (k <= r) {
            if (i > m) aux[k++] = arr[j++];
            else if (j > r) aux[k++] = arr[i++];
            else if (less(arr[j], arr[i])) aux[k++] = arr[j++]; // maintain the original order if equal;
            else aux[k++] = arr[i++];
//            if ((i <= m) && (j > r || less(arr[i], arr[j]))) aux[k++] = arr[i++];
//            else aux[k++] = arr[j++];
        }
    }
}
