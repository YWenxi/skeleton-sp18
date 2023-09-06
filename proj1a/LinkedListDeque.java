public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        Node() {
            item = null;
            next = null;
            prev = null;
        }

        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /* Constructor */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Add an item to be the first of the queue.
     *
     * @param item to be added
     */
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** Add an item to be the last of the queue.
     *
     * @param item to be added
     */
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque.
     *
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /** Print the Deque, separated by space.
     */
    public void printDeque() {
        Node iterator = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T out = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return out;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T out = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return out;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        while (index != 0) {
            current = current.next;
            index -= 1;
        }
        return current.item;
    }

    private T getRecursive(int index, Node start) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursive(index - 1, start.next);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursive(index, sentinel.next);
    }
}
