package chap2.section3;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

import java.util.Stack;

import lib.StdRandom;

public class QuickSort {
    private final static int INSERTION_THRESHOLD = 15;
    static int level = 0;

    public static void sort(Comparable[] arr) {
//        shuffle(arr); // to avoid bad case, shuffle it first;
//        sortBasicRecursion(arr, 0, arr.length - 1);
        sortIteration(arr, 0, arr.length - 1, PartitionType.PARTITION_1);
    }

    public static void sort(Comparable[] arr, SortType sortType) {
        sort(arr, sortType, PartitionType.PARTITION_2);
    }

    public static void sort(Comparable[] arr, SortType sortType, PartitionType partitionType) {
        int r = arr.length - 1;
        switch (sortType) {
            case ITERATION:
                sortIteration(arr, 0, r, partitionType);
                break;
            case RECURSIVE:
                sortBasicRecursion(arr, 0, r, partitionType);
                break;
            case SORT_3_WAY_RECURSION:
                sort3WayRecursion(arr, 0, r);
                break;
            case SORT_3_WAY_INTERATION:
                sort3WayIteration(arr, 0, r);
                break;
        }
    }

    /**
     * Warning: the worst case will tumble into StackOverflowError for deep recursive call;
     *
     * @param arr
     * @param l
     * @param r
     */
    private static void sortBasicRecursion(Comparable[] arr, int l, int r, PartitionType partitionType) {
        if (r - l < INSERTION_THRESHOLD) {
            for (int i = l + 1; i <= r; ++i) {
                for (int k = i; k > l && less(arr[k], arr[k - 1]); --k) {
                    exch(arr, k, k - 1);
                }
            }
            return;
        }
//        System.out.println("recursive level: " + level++);
        int j = getPartition(PartitionType.PARTITION_1).apply(arr, l, r);
        sortBasicRecursion(arr, l, j - 1, partitionType); // easy to tumble into StackOverflowError when in bad case - partially sorted;
        sortBasicRecursion(arr, j + 1, r, partitionType);
    }

    private static TripleFunction getPartition(PartitionType partitionType) {
        switch (partitionType) {
            case PARTITION_1:
                return QuickSort::partition;
            case PARTITION_2:
                return QuickSort::partition2;
        }
        return QuickSort::partition;
    }

    private static void sortIteration(Comparable[] arr, int l, int r, PartitionType partitionType) {
        if (r - l < INSERTION_THRESHOLD) {
            insertionSort(arr, l, r);
            return;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(r);
        stack.push(l);
        while (!stack.isEmpty()) {
            int lo = stack.pop();
            int hi = stack.pop();
            int j = getPartition(partitionType).apply(arr, lo, hi);
            if (j - 1 > lo) {
                stack.push(j - 1);
                stack.push(lo);
            }
            if (hi > j + 1) {
                stack.push(hi);
                stack.push(j + 1);
            }
        }
    }

    private static void insertionSort(Comparable[] arr, int l, int r) {
        for (int i = l + 1; i <= r; ++i) {
            for (int k = i; k > l && less(arr[k], arr[k - 1]); --k) {
                exch(arr, k, k - 1);
            }
        }
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

    private static int partition2(Comparable[] arr, int l, int r) {
        int i = l;
        int j = r + 1;
        exch(arr, l, StdRandom.uniform(i, j));
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

    private static void sort3WayRecursion(Comparable[] arr, int l, int r) {
        // when there are few duplicates, this method is rather easy to tumble into StackOverflowError;
        if (r - l < INSERTION_THRESHOLD) {
            insertionSort(arr, l, r);
            return;
        }
        int lo = l;
        int i = l + 1;
        int hi = r;
        Comparable v = arr[lo];
        while (i <= hi) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) exch(arr, i++, lo++);
            else if (cmp > 0) exch(arr, i, hi--);
            else i++;
        }
        sort3WayRecursion(arr, l, lo - 1);
        sort3WayRecursion(arr, hi + 1, r);
    }

    private static void sort3WayIteration(Comparable[] arr, int l, int r) {
        Stack<Integer> stack = new Stack<>();
        stack.push(r);
        stack.push(l);
        while (!stack.isEmpty()) {
            level = Math.max(stack.size(), level);
            int lo = stack.pop();
            int low = lo;
            int hi = stack.pop();
            int high = hi;
            int i = lo + 1;
            Comparable v = arr[lo];
            while (i <= hi) {
                int cmp = arr[i].compareTo(v);
                if (cmp < 0) exch(arr, i++, lo++);
                else if (cmp > 0) exch(arr, i, hi--);
                else i++;
            }
            if (lo - low - 1 < INSERTION_THRESHOLD) {
                insertionSort(arr, low, lo - 1);
            } else {
                stack.push(lo - 1);
                stack.push(low);
            }
            if (high - hi - 1 < INSERTION_THRESHOLD) {
                insertionSort(arr, hi + 1, high);
            } else {
                stack.push(high);
                stack.push(hi + 1);
            }
        }
        System.out.println("max level: " + level);
    }

    public enum PartitionType {
        PARTITION_1,
        PARTITION_2,
    }

    public enum SortType {
        RECURSIVE,
        ITERATION,
        SORT_3_WAY_RECURSION,
        SORT_3_WAY_INTERATION;
    }

    @FunctionalInterface
    interface TripleFunction {
        int apply(Comparable[] arr, int l, int r);
    }
}
