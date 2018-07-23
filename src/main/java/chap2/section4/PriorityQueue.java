package chap2.section4;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public PriorityQueue(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        pq[1] = pq[N--];
        sink(1);
        return max;
    }

    private void sink(int n) {
        while (n * 2 < N) {
            if (2 * n < N - 1 && less(pq[n * 2], pq[n * 2 + 1])) exch(pq, n * 2, n * 2 + 1);
            if (less(pq[n], pq[n * 2])) exch(pq, n, n * 2);
            else break;
        }
    }

    private void swim(int n) {
        while (n > 1 && less(pq[n / 2], pq[n])) n /= 2;
    }
}
