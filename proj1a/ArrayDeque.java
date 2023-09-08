public class ArrayDeque<T> {
    private T[] array;
    private int size;

    /** size of the array */
    private int length;
    /** index of the first item */
    private int first;
    /** next index of the last item */
    private int next;

    public ArrayDeque() {
        size = 0;
        length = 8;
        array = (T[]) new Object[length];
        first = 4;
        next = 4;
    }

    /** move the index to the front one
     * @param index index to be moved
     * @return the front index
     */
    private int indexMinus(int index, int module) {
        if (index == 0) {
            return module - 1;
        }
        return index - 1;
    }

    /** move the index to the next one
     *
     * @param index index to be moved
     * @param module length of array
     * @return the next index
     */
    private int indexPlus(int index, int module) {
        return (index + 1) % module;
    }

    /** move the index to the
     *
     * @param index index to be moved
     * @param module length of the array
     * @param step steps to move forward
     * @return the next index
     */
    private int indexPlus(int index, int module, int step) {
        return (index + step) % module;
    }

    /** resize the array by factor 2 */
    private void resize() {
        /* create a new array */
        T[] newArray = (T[]) new Object[length * 2];

        // iterator to read elements
        int readPtr = first;
        // iterator to write elements
        int writePtr = length;

        // write the newArray
        for (int i = 0; i < size; i += 1) {
            newArray[writePtr] = array[readPtr];
            readPtr = indexPlus(readPtr, length);
            writePtr = indexPlus(writePtr, length * 2);
        }

        // update attributes
        array = newArray;
        first = length;
        length = 2 * length;
        next = writePtr;
    }

    /** Shrink the array by the factor 2 */
    private void shrink() {
        // check size is less than half of length
        if (size > length / 2 || length <= 8) {
            return;
        }

        // iterators/ptr to read and write
        int readPtr = first;
        int writePtr = length / 4;

        // newArray with half-length
        T[] newArray = (T[]) new Object[length / 2];

        // write the new array;
        while (readPtr != next) {
            newArray[writePtr] = array[readPtr];
            readPtr = indexPlus(readPtr, length);
            writePtr = indexPlus(writePtr, length / 2);
        }

        // update attributes
        array = newArray;
        length = length / 2;
        first = length / 2;
        next = writePtr;
    }

    /** Check if the Deque is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** returns the size of deque */
    public int size() {
        return size;
    }

    /** Add an item to the front of the list
     * @param item: item to add
     */
    public void addFirst(T item) {
        // resize if the slots are full
        if (size == length) {
            resize();
        }

        // add the item and move the first pointer
        first = indexMinus(first, length);
        array[first] = item;
        size += 1;
    }

    /** Add an item to the last of the list
     * @param item: item to add
     */
    public void addLast(T item) {
        // resize if the slots are full
        if (size == length) {
            resize();
        }

        // add, move, and update
        array[next] = item;
        next = indexPlus(next, length);
        size += 1;
    }

    /** Get the item at the index
     *
     * @param index
     * @return the item; if no such index, returns null
     */
    public T get(int index) {
        // check if index is valid, otherwise return null
        if (index < 0 || index >= size) {
            return null;
        }

        // get the item directly
        int readPtr = indexPlus(first, length, index);
        return array[readPtr];
    }

    /** Print the deque */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.print("\n");
    }

    /** Pop out the first item. */
    public T removeFirst() {
        // if no such item, return null
        if (size == 0) {
            return null;
        }

        // pop out the first item
        T out = array[first];
        array[first] = null;

        // update the attributes
        first = indexPlus(first, length);
        size -= 1;

        // shrink if necessary
        shrink();

        return out;
    }

    /** Pop out the last item */
    public T removeLast() {
        // if no such item, return null;
        if (size == 0) {
            return null;
        }

        // move ptr, pop out the last
        next = indexMinus(next, length);
        T out = array[next];
        array[next] = null;

        // update
        size -= 1;

        // shrink if necessary
        shrink();

        return out;
    }
}
