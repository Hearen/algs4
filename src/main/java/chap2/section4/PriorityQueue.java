package chap2.section4;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int index = 0;

    public PriorityQueue(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public PriorityQueue(Key[] arr) {
        int len = arr.length;
        pq = (Key[]) new Comparable[len + 1];
        for (int i = 0; i < len; ++i) pq[i+1] = arr[i];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public int size() {
        return index;
    }

    public void insert(Key v) {
        pq[++index] = v;
        swim(index);
    }

    public Key delMax() {
        Key max = pq[1];
        pq[1] = pq[index--];
        sink(1);
        return max;
    }

    public void exchE(int i, int j) {
        exch(pq, i, j);
    }

    public void sink(int n) {
        int j = n * 2;
        while (j < index) {
            if (j < index && less(pq[j], pq[j + 1])) j++;
            if (less(pq[n], pq[j])) {
                exch(pq, n, j);
                n = j;
                j *= 2;
            }
            else break;
        }
    }

    public void swim(int n) {
        while (n > 1 && less(pq[n / 2], pq[n])) {
            exch(pq, n, n / 2);
            n /= 2;
        }
    }
}
