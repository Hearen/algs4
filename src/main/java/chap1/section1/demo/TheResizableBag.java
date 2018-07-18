package chap1.section1.demo;

import lib.StdRandom;

import java.util.Iterator;

public class TheResizableBag {

    public static void main(String... args) {
        final int N = 10;
        final int MAX = 30;
        BasicGraphs.VisualAccumulator visualAccumulator = new BasicGraphs().new VisualAccumulator(N, MAX);
        ResizableBag<Integer> bagUsingArray = new TheResizableBag().new ResizableBag<>(N);
        for (int i = 0; i < MAX; ++i) {
            bagUsingArray.add(StdRandom.uniform(1000));
        }
        for (Integer integer : bagUsingArray) {
            System.out.println(integer);
        }
        System.out.println(bagUsingArray.size());
    }
    class ResizableBag<T> implements Iterable<T> {
        int index;
        T[] arr;
        public ResizableBag(int defaultSize) {
            arr = (T[]) new Object[defaultSize];
            index = -1;
        }
        public int size() { return index + 1; }
        public void add(T t) {
            arr[++index] = t;
            if (index == arr.length - 1) {
                resize(arr.length * 2);
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new BagIterator();
        }

        private void resize(int newSize) {
            System.out.println("Resizing from " + arr.length + " to " + newSize);
            T[] newArr = (T[]) new Object[newSize];
            for (int i = 0; i <= index; ++i) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }

        class BagIterator implements Iterator {
            int i = index;
            @Override
            public boolean hasNext() {
                return i > -1;
            }

            @Override
            public Object next() {
                return arr[i--];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not implemented yet");
            }
        }
    }
}
