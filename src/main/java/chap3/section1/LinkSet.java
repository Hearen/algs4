package chap3.section1;

import java.util.Iterator;

public class LinkSet<Key extends Comparable<Key>, Value> {
    Node head; // sentinel;

    public LinkSet() {
        head = new Node();
    }

    // null key is not allowed;

    /**
     * null key is not allowed;
     * null value is removing the key but if the key doesn't exist, will throw IllegalArgumentException;
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("no null key allowed!");
        if (head.next == null) { // no element then if the value is null, it's not allowed;
            if (value == null) throw new IllegalArgumentException("No null value allowed!");
            head.next = new Node(key, value, head.next);
            return;
        }
        Node p = head; // pointing to the previous node for deletion;
        while (p.next != null) {
            if (key.compareTo(p.next.key) < 0) {
                p.next = new Node(key, value, p.next);
                return;
            } else if (key.compareTo(p.next.key) > 0) {
                p = p.next;
            } else {
                if (value == null) { // remove the node if the value is null;
                    p.next = p.next.next;
                } else {
                    p.next.val = value;
                    return;
                }
            }
        }
    }

    public Value get(Key key) {
        Node p = head.next;
        while (p != null) {
            if (p.key.compareTo(key) == 0) return p.val;
            p = p.next;
        }
        return null;
    }

    public void delete(Key key) {
        put(key, null);
    }

    // null value is not allowed;
    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public int size() {
        int count = 0;
        Node p = head.next;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    public Key min() {
        if (head.next == null) throw new IllegalStateException("No element in the set");
        return head.next.key;
    }

    public Key max() {
        if (head.next == null) throw new IllegalStateException("No element in the set");
        Node p = head.next;
        while (p.next != null) {
            p = p.next;
        }
        return p.key;
    }

    public Key floor(Key key) {
        Key theKey = null;
        Node p = head.next;
        while (p != null) {
            if (p.key.compareTo(key) <= 0) theKey = p.key;
            else break;
        }
        return theKey;
    }

    public Key ceiling(Key key) {
        Node p = head.next;
        while (p != null) {
            if (p.key.compareTo(key) >= 0) return key;
        }
        return null;
    }

    public int rank(Key key) {
        int count = 0;
        Node p = head.next;
        while (p != null) {
            if (p.key.compareTo(key) < 0) count++;
            else break;
        }
        return count;
    }

    /**
     * related to rank(Key key) starts from 0;
     *
     * @param k
     * @return
     */
    public Key select(int k) {
        if (k < 0) throw new IllegalArgumentException("Negative rank is not allowed");
        Node p = head.next;
        for (int i = 0; i < k; ++i) {
            if (p == null) throw new IllegalArgumentException("rank is too high");
            p = p.next;
        }
        return p.key;
    }

    public void delMin() {
        if (head.next == null) throw new IllegalArgumentException("No element in the set");
        head.next = head.next.next;
    }

    public void delMax() {
        if (head.next == null) throw new IllegalArgumentException("No element in the set");
        Node p = head;
        while (p.next.next != null) {
            p = p.next;
        }
        p.next = p.next.next;
    }

    /**
     * [lo, hi]
     *
     * @param lo
     * @param hi
     * @return
     */
    public int size(Key lo, Key hi) {
        if (head.next == null) throw new IllegalArgumentException("No element in the set");
        int count = 0;
        boolean inTheZone = false;
        Node p = head.next;
        while (p != null) {
            if (p.key.compareTo(lo) >= 0) inTheZone = true;
            if (inTheZone) {
                if (p.key.compareTo(hi) <= 0) count++;
                else break;
            }
            p = p.next;
        }
        return count;
    }

    /**
     * [lo...hi]
     *
     * @param lo
     * @param hi
     * @return
     */
    public Iterator<Key> keys(Key lo, Key hi) {
        Node newHead = new Node();
        Node tail = new Node();
        Node p = head.next;
        boolean inTheZone = false; // using inTheZone flag to avoid key comparison;
        while (p != null) {
            if (!inTheZone && p.key.compareTo(lo) >= 0) inTheZone = true;
            if (inTheZone) {
                if (p.key.compareTo(hi) <= 0) {
                    if (newHead.next == null) {
                        newHead.next = new Node(p.key, p.val, null);
                        tail = newHead.next;
                    } else {
                        tail.next = new Node(p.key, p.val, null);
                        tail = tail.next;
                    }
                } else break;
            }
            p = p.next;
        }
        return new KeyIterator(newHead.next);
    }

    public Iterator<Key> keys() {
        return new KeyIterator(head.next);
    }

    class KeyIterator implements Iterator<Key> {
        Node p;

        public KeyIterator(Node head) {
            this.p = head;
        }

        @Override
        public boolean hasNext() {
            return p != null && p.next != null;
        }

        @Override
        public Key next() {
            Key key = p.key;
            p = p.next;
            return key;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class Node {
        Key key;
        Value val;
        Node next;

        public Node() {

        }

        public Node(Key theKey, Value theValue, Node theNode) {
            this.key = theKey;
            this.val = theValue;
            this.next = theNode;
        }
    }
}
