package chap1.section4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import lib.StdRandom;

public class TheLinkQueue {
    public static void main(String... args) {
        final int MAX = 30;
        QueueUsingLinkNode<Integer> queueUsingLink = new TheLinkQueue().new QueueUsingLinkNode<>();
        int[] arr = new int[MAX];
        for (int i = 0; i < MAX; ++i) {
            int a = StdRandom.uniform(1000);
            queueUsingLink.enque(a);
            arr[i] = a;
        }
        int i = 0;
        for (Integer integer : queueUsingLink) {
            assert integer == arr[i];
            System.out.println(integer + " : " + arr[i++]);
        }
        i = 0;
        while (!queueUsingLink.isEmpty()) {
            int a = queueUsingLink.deque();
            assert a == arr[i];
            System.out.println("Dequing " + a + " : " + arr[i++]);
        }
        System.out.println(queueUsingLink.size());
//        queueUsingLink.deque();
        queueUsingLink.enque(1);
        queueUsingLink.enque(2);
        for (Integer aInt : queueUsingLink) {
            queueUsingLink.enque(3);
            System.out.println(aInt);
        }
    }

    class QueueUsingLinkNode<T> implements Iterable<T> {
        Node head;
        Node tail;
        int modCount; // used for fast-fail concurrent modification;
        int count;

        public QueueUsingLinkNode() {
            head = new Node(null, null);
            tail = new Node(null, null);
            count = 0;
            modCount = 0;
        }

        public int size() {
            return count;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public void enque(T t) {
            tail.next = new Node(t, null);
            tail = tail.next;
            if (head.next == null) head.next = tail;
            count++;
            modCount++;
        }

        public T deque() {
            if (head.next == null) {
                throw new NoSuchElementException("No element in the queue");
            }
            T t = head.next.value;
            head.next = head.next.next;
            count--;
            modCount++;
            return t;
        }

        @Override
        public Iterator<T> iterator() {
            // ToDo: one step late - if there is no more elements it won't throw concurrent modification exception;
            modCount = 0;
            return new QueueIterator();
        }

        class QueueIterator implements Iterator {
            Node theHead = head.next;

            @Override
            public boolean hasNext() {
                return theHead != null;
            }

            @Override
            public Object next() {
                if (modCount > 0) {
                    throw new ConcurrentModificationException("No modification can be made during traversal");
                }
                T t = theHead.value;
                theHead = theHead.next;
                return t;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not implemented yet");
            }
        }

        private class Node {
            T value;
            Node next;

            public Node(T theValue, Node theNext) {
                this.value = theValue;
                this.next = theNext;
            }
        }
    }
}
