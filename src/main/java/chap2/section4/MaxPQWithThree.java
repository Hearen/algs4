package chap2.section4;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.less;

public class MaxPQWithThree<Key extends Comparable<Key>>{
    Key[] arr;
    int index;
    public MaxPQWithThree(int size) {
        arr = (Key[]) new Comparable[size];
        index = -1;
    }
    public MaxPQWithThree(Key[] theArr) {
        arr = theArr;
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
        int j = k * 3;
        while (j <= index) {
            int j1 = j + 1;
            int j2 = j + 2;
            if(j1 <= index && less(arr[j], arr[j1])) j = j1;
            if(j2 <= index && less(arr[j], arr[j2])) j = j2;
            if (less(arr[k], arr[j])) {
                exch(arr, k, j);
                k = j;
                j *= 3;
            } else break;
        }
    }

    private void swim(int k) {
        while (k > -1 && less(arr[k/3], arr[k])) {
            exch(arr, k/3, k);
            k /= 3;
        }
    }

}
