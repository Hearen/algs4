package chap1.demo;

import lib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TheResizableQueue {
    public static void main(String... args) {
        final int N = 1_000;
        final int MAX = 3_000;
        ResizableQueue<Integer> queueUsingArray = new TheResizableQueue().new ResizableQueue<Integer>(N);
        for (int i = 0; i < MAX; ++i) {
            queueUsingArray.enque(StdRandom.uniform(1000));
        }
//        for (Integer integer : queueUsingArray) {
//            System.out.println(integer);
//        }
        while (!queueUsingArray.isEmpty()) {
            queueUsingArray.deque();
//            System.out.println(queueUsingArray.pop());
        }
    }

    class ResizableQueue<T> implements Iterable<T> {
        T[] arr;
        int start;
        int end;
        public ResizableQueue(int size) {
            arr = (T[]) new Object[size];
            start = -1;
            end = -1;
        }

        public int size() { return end - start + 1;}

        public void enque(T t) {
            if (start == -1) start = 0;
            arr[++end] = t;
            if (end == arr.length - 1) {
                resize();
            }
        }

        public T deque() {
            if (size() <= 0) {
                throw new NoSuchElementException("No element in the queue");
            }
            T t = arr[start++];
            arr[start-1] = null;
            if (start == arr.length / 2) {
                resize();
            }
            return t;
        }
        public boolean isEmpty() { return size() <= 0; }
        private void resize() {
            if (start > arr.length / 4) {
                System.out.println("Moving to the leftmost: " + start);
                for (int i = start; i <= end; ++i) {
                    arr[i - start] = arr[i];
                }
                end -= start;
                start = 0;
            }
            if (size() == arr.length) {
                System.out.println("Resizing from " + arr.length + " to " + arr.length * 2);
                T[] newArr = (T[]) new Object[arr.length * 2];
                for (int i = start; i <= end; ++i) {
                    newArr[i] = arr[i];
                }
                arr = newArr;
                end -= start;
                start = 0;
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new QueueIterator();
        }

        class QueueIterator implements Iterator {
            int l = start;
            int r = end;
            @Override
            public boolean hasNext() {
                return r - l > -1;
            }

            @Override
            public Object next() {
                return arr[l++];
            }
        }
    }
}
