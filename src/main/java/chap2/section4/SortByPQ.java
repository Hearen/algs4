package chap2.section4;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class SortByPQ {
    public static void sort(Comparable[] arr) {
        sortByPQRule(arr);
//        sortByPQ(arr);
    }

    public static void sort(Comparable[][] arrs) {
        int arrCount = arrs.length;
        int[] indexes = new int[arrCount];
        for (int i = 0; i < arrCount; ++i) indexes[i] = -1;
        Pair<Comparable, Comparable[]>[] pq = new Pair[arrCount];
        for (int i = 0; i < arrCount; ++i) {
            pq[i] = new Pair(arrs[i][++indexes[i]], arrs[i]);
        }
        for (int i = arrCount/2; i > -1; --i) sink(pq, i, arrCount-1);
        List<Comparable> list = new ArrayList<>();

    }

    private static void sink(Pair<Comparable, Comparable[]>[] pq, int i, int lastIndex) {
        int j = 2 * i + 1;
        while (j <= lastIndex) {
            if (j < lastIndex && less(pq[j].getKey(), pq[j+1].getKey())) j++;
            if (pq[i].getKey() == null || less(pq[i].getKey(), pq[j].getKey())) {
                exch(pq, i, j);
                i = j;
                j = 2 * j + 1;
            } else break;
        }
    }

    private static void exch(Pair[] pq, int i, int j) {
        Pair t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private static void sortByPQRule(Comparable[] arr) {
        int len = arr.length;
        int lastIndex = len - 1;
        for (int i = len / 2; i > -1; --i) sink(arr, i, lastIndex);
        int i = len - 1;
        while (i > 0) {
            exch(arr, 0, i--);
            sink(arr, 0, i);
        }
    }

    // index starts from zero;
    private static void sink(Comparable[] arr, int i, int lastIndex) {
        int j = 2*i + 1;
        while (j <= lastIndex) {
            if (j < lastIndex && less(arr[j], arr[j+1])) j++;
            if (less(arr[i], arr[j])) {
                exch(arr, i, j);
                i = j;
                j = 2*j + 1;
            } else break;
        }
    }

    private static void sortByPQ(Comparable[] arr) {
        int len = arr.length;
        PriorityQueue priorityQueue = new PriorityQueue<>(arr);
        for (int i = len / 2; i > 0; --i) {
            priorityQueue.sink(i);
        }
        int i = len; // FIXME: with last element lost;
        while (i > 1) {
            priorityQueue.exchE(1, i--);
            priorityQueue.sink(1);
        }
        for (i = 0; i < len/2; ++i) {
            exch(arr, i, len - i - 1);
        }
    }
}
