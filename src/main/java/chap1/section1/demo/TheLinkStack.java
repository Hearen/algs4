package chap1.section1.demo;

import lib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TheLinkStack {
    public static void main(String...args) {
        final int MAX = 30;
        StackUsingLinkNode<Integer> stackUsingLink = new TheLinkStack().new StackUsingLinkNode<>();
        int[] arr = new int[MAX];
        for (int i = 0; i < MAX; ++i) {
            int a = StdRandom.uniform(1000);
            stackUsingLink.push(a);
            arr[i] = a;
        }
        int i = MAX - 1;
        for (Integer integer : stackUsingLink) {
            System.out.println(integer + " : " + arr[i--]);
        }
        while (!stackUsingLink.isEmpty()) {
            System.out.println(stackUsingLink.pop());
        }
        System.out.println(stackUsingLink.size());
        stackUsingLink.pop();
    }

    public class StackUsingLinkNode<T> implements Iterable<T> {
        Node head;
        int count;
        public StackUsingLinkNode() {
            head = new Node(null, null);
            count = 0;
        }

        public boolean isEmpty() { return head.next == null; }

        public int size() { return count; }
        public void push(T t) {
            head.next = new Node(t, head.next);
            count++;
        }

        public T pop() {
            if (head.next == null) {
                throw new NoSuchElementException("No element in the stack");
            }
            T t = head.next.value;
            if (head.next != null) {
                head.next = head.next.next;
            }
            count--;
            return t;
        }

        @Override
        public Iterator<T> iterator() {
            return new StackIterator();
        }

        class StackIterator implements Iterator {
            Node theHead = head;
            @Override
            public boolean hasNext() {
                return theHead.next != null;
            }

            @Override
            public Object next() {
                theHead = theHead.next;
                return theHead.value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not implemented yet");
            }
        }

        class Node {
            T value;
            Node next;
            public Node(T theT, Node theNext)  {
                this.value = theT;
                this.next = theNext;
            }
        }
    }
}
