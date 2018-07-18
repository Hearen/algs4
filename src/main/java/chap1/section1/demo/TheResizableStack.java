package chap1.section1.demo;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lib.StdRandom;

public class TheResizableStack {
    public static void main(String... args) {
        final int N = 1_000;
        final int MAX = 3_000;
        StackUsingArray<Integer> stackUsingArray = new TheResizableStack().new StackUsingArray<Integer>(N);
        for (int i = 0; i < MAX; ++i) {
            stackUsingArray.push(StdRandom.uniform(1000));
        }
        for (Integer integer : stackUsingArray) {
            System.out.println(integer);
        }
        while (!stackUsingArray.isEmpty()) {
            stackUsingArray.pop();
//            System.out.println(stackUsingArray.pop());
        }


    }

    class StackUsingArray<T> implements Iterable<T> {
        T[] arr;
        int index;

        public StackUsingArray(int theSize) {
            arr = (T[]) new Object[theSize]; // no generics array allowed;
            index = -1;
        }

        @Override
        public Iterator<T> iterator() {
            return new StackIterator();
        }

        private void resize(int newSize) {
            System.out.println("Resizing from " + arr.length + " new size: " + newSize);
            T[] newArr = (T[]) new Object[newSize];
            for (int i = 0; i <= index; ++i) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }

        public T pop() {
            if (index < 0) {
                throw new NoSuchElementException("No element in the stack");
            }
            T ret = arr[index--];
            arr[index + 1] = null; // avoid loitering;
            if (index == arr.length / 8) {
                resize(arr.length / 2);
            }
            return ret;
        }

        public void push(T t) {
            arr[++index] = t;
            if (index == arr.length - 1) {
                resize(2 * arr.length);
            }
        }

        public boolean isEmpty() {
            return index == -1;
        }

        class StackIterator implements Iterator<T> {
            int i = index;

            @Override
            public boolean hasNext() {
                return i > -1;
            }

            @Override
            public T next() {
                return arr[i--];
            }

            @Override
            public void remove() {
            }
        }
    }
}
