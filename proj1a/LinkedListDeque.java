public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    public class Node {
        public T item;
        public Node next;
        public Node prev;

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
    LinkedListDeque() {
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
        Node NewNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = NewNode;
        sentinel.next = NewNode;
        size += 1;
    }

    /** Add an item to be the last of the queue.
     *
     * @param item to be added
     */
    public void addLast(T item) {
        Node NewNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = NewNode;
        sentinel.prev = NewNode;
    }

    /** Returns true if deque is empty, false otherwise.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        if(size == 0) {return true;}
        else {return false;}
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
            System.out.print(iterator.item);
            iterator = iterator.next;
        }
        System.out.print("\n");
    }



}