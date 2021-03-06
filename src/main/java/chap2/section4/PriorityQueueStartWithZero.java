package chap2.section4;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class PriorityQueueStartWithZero<Key extends Comparable<Key>> {
    Key[] arr;
    int index;

    public PriorityQueueStartWithZero(int size) {
        arr = (Key[]) new Comparable[size];
        index = -1;
    }

    public void insert(Key val) {
        arr[++index] = val;
        swim(index);
    }

    public Key delMax() {
        Key max = arr[0];
        exch(arr, 0, index--);
        arr[index+1] = null;
        sink(0);
        return max;
    }

    private void sink(int k) {
        int j = 2 * k + 1;
        while (j <= index) {
            if (j < index && less(arr[j], arr[j + 1])) j++;
            if (less(arr[k], arr[j])) {
                exch(arr, k, j);
                k = j;
                j = 2 * j + 1;
            } else break;
        }
    }

    private void swim(int k) {
        while (k > 0 && less(arr[(k - 1) / 2], arr[k])) {
            exch(arr, (k - 1) / 2, k);
            k = (k - 1) / 2;
        }
    }
}
