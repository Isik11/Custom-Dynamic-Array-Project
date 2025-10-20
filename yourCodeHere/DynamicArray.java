// TO DO: add your implementation and JavaDocs

/***
 * Generic class of Dynamic Array.
 * @param <T> the type of elements stored in this DynamicArray.
 * @author Ismoil Aknazarov
 */
public class DynamicArray<T> {

    private static final int INITCAP = 2;    // default initial capacity / minimum capacity
    private T[] array;    // underlying array
    private int size;

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!


    @SuppressWarnings("unchecked")

    /**
     * Constructor for a new DynamicArray with Initial capacity of 2.
     *
     */
    public DynamicArray() {
        array = (T[]) new Object[INITCAP];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    /**
     * Constructs a new DynamicArray with the specified initial capacity.
     *
     * @param initCapacity the initial capacity of the array.
     * @throws IllegalArgumentException if {@code initCapacity < 1}
     */
    public DynamicArray(int initCapacity) {
        // constructor
        // set the initial capacity of the array as initCapacity
        array = (T[]) new Object[initCapacity];
        // throw IllegalArgumentException if initCapacity < 1
        if (initCapacity < 1) {
            throw new IllegalArgumentException("Capacity cannot be less than one!");
        }

    }

    /**
     * Returns the number of elements currently stored in the array.
     *
     * @return the number of elements in this DynamicArray.
     */
    public int size() {
        //report the number of elements in the list

        // O(1)
        return this.size;
    }

    /**
     * Returns the current capacity (maximum number of elements
     * before the array expands).
     *
     * @return the capacity of the underlying array
     */
    public int capacity() {
        //report the max number of elements before the next expansion

        // O(1)
        return array.length;
    }

    /**
     * Replaces the element at the given index with the specified value.
     *
     * @param index the index of the element to replace
     * @param value the new element to store
     * @return the old element previously stored at {@code index}
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size}
     */

    public T set(int index, T value) {
        // change item x at index i to be value
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        // return old item at index
        T oldValue = array[index];
        array[index] = value;
        // throw IndexOutOfBoundsException for invalid index
        // O(1)

        // Note: you cannot add new items with this method
        return oldValue;
    }

    /**
     * Returns the element at the given index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the given {@code index}
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size}
     */
    public T get(int index) {
        // throw IndexOutOfBoundsException for invalid index
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        // O(1)

        return array[index];
    }

    /**
     * Appends the given value to the end of the array.
     * Expands the capacity if full.
     *
     * @param value the element to add
     * @return {@code true} once the value is successfully added
     */
    @SuppressWarnings("unchecked")
    public boolean add(T value) {

        // add value to the end of the list (append)
        // return true
        // double the capacity if no space is available
        if (this.size == array.length) {
            T[] oldArray = array;
            array = (T[]) new Object[array.length * 2];
            System.arraycopy(oldArray, 0, array, 0, size);
        }

        array[this.size] = value;
        this.size++;
        // amortized O(1)

        // Note: Remember... code reuse is awesome...
        return true;
    }


    /**
     * Inserts a value at the specified index, shifting subsequent
     * elements to the right if necessary. Expands the capacity if full.
     *
     * @param index the position to insert the element at
     * @param value the element to insert
     * @throws IndexOutOfBoundsException if {@code index < 0 || index > size}
     */
    @SuppressWarnings("unchecked")
    public void add(int index, T value) {
        // insert value at index, shift elements if needed
        // throw IndexOutOfBoundsException for invalid index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }
        // double the capacity if no space is available
        if (this.size == array.length) {
            T[] oldArray = array;
            array = (T[]) new Object[array.length * 2];
            System.arraycopy(oldArray, 0, array, 0, size);
        }

        if (this.size == index) {
            array[index] = value;
            this.size++;
        } else if (this.size > index) {
            for (int i = this.size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = value;
            this.size++;
        }
        // O(N) where N is the number of elements in the list
        // Note: this method may be used to append items as
        // well as insert items
    }


    /**
     * Removes and returns the element at the specified index.
     * Shifts elements left to fill the gap.
     * Contracts capacity if size drops below 1/3 of capacity.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size}
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        // remove and return element at position index

        // shift elements to remove any gap in the list
        // throw IndexOutOfBoundsException for invalid index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        //element to remove
        T removedElm = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        //remove previous last element and decrement size
        array[size - 1] = null;
        this.size--;

        // halve capacity if the number of elements falls below 1/3 of the capacity
        if (size < array.length / 3) {
            if (array.length / 2 < 2) {
                T[] oldArray = array;
                array = (T[]) new Object[INITCAP];
                System.arraycopy(oldArray, 0, array, 0, size);
            } else {
                T[] oldArray = array;
                array = (T[]) new Object[array.length / 2];
                System.arraycopy(oldArray, 0, array, 0, size);
            }

        }
        // capacity should NOT go below INITCAP

        // O(N) where N is the number of elements in the list
        return removedElm;
    }


    // --------------------------------------------------------
    // example testing code... edit this as much as you want!
    // --------------------------------------------------------


    // Not required, update for your own testing

    /**
     * Returns a string representation of this DynamicArray.
     *
     * @return a string containing the size and capacity
     */
    @Override
    public String toString() {
        // return string representation of DynamicArray
        // update if you want to include more information
        return "DynamicArray with size " + size() + ", capacity " + capacity();

    }

    public static void main(String args[]) {
        // new list?
        DynamicArray<Integer> ida = new DynamicArray<>();
        if ((ida.size() == 0) && (ida.capacity() == 2)) {
            System.out.println("Yay 1");
        }

        // adding to the list?
        boolean ok = true;
        for (int i = 0; i < 3; i++)
            ok = ok && ida.add(i * 5);

        if (ok && ida.size() == 3 && ida.get(2) == 10 && ida.capacity() == 4) {
            System.out.println("Yay 2");
        }

        ida.add(1, -10);
        ida.add(4, 100);
        if (ida.set(1, -20) == -10 && ida.get(2) == 5 && ida.size() == 5
                && ida.capacity() == 8) {
            System.out.println("Yay 3");
        }

        // removing from the list?
        if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100
                && ida.size() == 2 && ida.capacity() == 4) {
            System.out.println("Yay 4");
        }

        // Checking bounds
        DynamicArray<String> names = new DynamicArray<>();

        names.add("Ismoil");
        names.add("Shawn");
        names.add("Firya");

        System.out.println("Size = " + names.size);
        System.out.println(names.get(1));
    }

}