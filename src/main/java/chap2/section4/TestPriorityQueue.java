package chap2.section4;

public class TestPriorityQueue {
    public static void main(String... args) {
        int N = 10;
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(N);
        PriorityQueueStartWithZero<Integer> maxPQ = new PriorityQueueStartWithZero<>(N);
        for (int i = 0; i < N; ++i) {
//            maxPQ.insert(StdRandom.uniform(100));
            maxPQ.insert(arr[i]);
        }
        for (int i = 0; i < N; ++i) {
            System.out.println(maxPQ.delMax());
        }
    }
}
