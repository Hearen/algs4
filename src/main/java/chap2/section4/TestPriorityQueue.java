package chap2.section4;

import static chap2.section1.SortUtil.isAscended;

import lib.StdRandom;

public class TestPriorityQueue {
    public static void main(String... args) {
        int N = 1000;
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(N);
//        Integer[] arr = new Integer[N];
        PriorityQueueStartWithZero<Integer> maxPQ = new PriorityQueueStartWithZero<>(N);
        MaxPQWithThree<Integer> maxPQWithThree = new MaxPQWithThree<>(N);
        SortByPQ.sort(arr);
//        for (int i = 0; i < N; ++i) {
////            maxPQ.insert(StdRandom.uniform(100));
//            maxPQWithThree.insert(StdRandom.uniform(N));
////            maxPQ.insert(arr[i]);
//        }
//        for (int i = 0; i < N; ++i) {
////            arr[i] = maxPQ.delMax();
//            arr[i] = maxPQWithThree.delMax();
//            System.out.println(arr[i]);
//        }
        assert isAscended(arr) : "PriorityQueue Error!";
    }


    private static void testSortMultiArrays(Comparable[][] arrs) {

    }
}
