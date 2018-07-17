package chap1.demo;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TheResizableQueue {

    class ResizableQueue<T> implements Iterable<T> {
        T[] arr;
        int start;
        int end;
        public ResizableQueue(int size) {
            arr = (T[]) new Object[size];
            start = 0;
            end = -1;
        }
        public void enque(T t) {
            arr[++end] = t;
            if (end == arr.length - 1) {
                resize(arr.length * 2);
            }
        }

        public T deque() {
            if (end - start <= 0) {
                throw new NoSuchElementException("No element in the queue");
            }
            T t = arr[start++];
            arr[start-1] = null;
            if (end - start == arr.length / 2) {
                resize(arr.length / 2);
            }
            return t;
        }
        public boolean isEmpty() { return end - start > -1; }
        private void resize(int newSize) {
            T[] newArr = (T[]) new Object[newSize];
            for (int i = start; i <= end; ++i) {
                newArr[i] = arr[i];
            }
            arr = newArr;
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
