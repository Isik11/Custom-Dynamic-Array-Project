// TO DO: add your implementation and JavaDoc

/**
 * A generic two-dimensional grid structure implemented using
 * a {@link DynamicArray} of {@link DynamicArray}s.
 *
 * The grid supports operations for adding, inserting, removing,
 * accessing, and modifying rows and columns, while dynamically
 * resizing as needed.
 *
 * @param <T> the type of elements stored in this grid
 * @author Ismoil Aknazarov
 */
public class DynamicGrid<T> {
    private DynamicArray<DynamicArray<T>> storage;    // underlying storage
    // HINT: Read the big-O requirements of the methods below to determine
    // how the columns/rows should be stored in storage.


    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

    /**
     * Constructs an empty DynamicGrid with 0 rows and 0 columns.
     */
    public DynamicGrid() {
        // constructor
        // create an empty table of 0 rows and 0 cols
        storage = new DynamicArray<>();
    }

    /**
     * Returns the number of rows in the grid.
     *
     * @return the number of rows
     * @implNote O(1)
     */
    public int getNumRow() {
        // return number of rows in the grid
        // O(1)
        return storage.size();
    }

    /**
     * Returns the number of columns in the grid.
     *
     * @return the number of columns (0 if grid is empty)
     * @implNote O(1)
     */
    public int getNumCol() {
        // return number of columns in the grid
        // O(1)

        if (storage.size() == 0) {
            return 0;
        } else
            return storage.get(0).size();
    }

    /**
     * Retrieves the element at the specified row and column.
     *
     * @param indexRow the row index
     * @param indexCol the column index
     * @return the element at the given location
     * @throws IndexOutOfBoundsException if indices are invalid
     * @implNote O(1)
     */
    public T get(int indexRow, int indexCol) {

        // return the value at the specified row and column
        if (indexRow >= storage.size() || indexRow < 0) {
            throw new IndexOutOfBoundsException("Row of bound!");
        }
        if (indexCol < 0 || storage.get(indexRow).size() <= indexCol) {
            throw new IndexOutOfBoundsException("Column out of bound!");
        }
        // throw IndexOutOfBoundsException for invalid index
        // O(1)
        return storage.get(indexRow).get(indexCol);
    }

    /**
     * Replaces the value at the specified row and column.
     *
     * @param indexRow the row index
     * @param indexCol the column index
     * @param value    the new value to set
     * @return the old value at the given location
     * @throws IndexOutOfBoundsException if indices are invalid
     * @implNote O(1)
     */
    public T set(int indexRow, int indexCol, T value) {
        // change value at the specified row and column to be value
        // return the old value
        // throw IndexOutOfBoundsException for invalid index
        if (indexRow >= storage.size() || indexRow < 0) {
            throw new IndexOutOfBoundsException("Row of bound!");
        }
        if (indexCol < 0 || storage.get(indexRow).size() <= indexCol) {
            throw new IndexOutOfBoundsException("Column out of bound!");
        }
        //Get the row of the 2d array
        DynamicArray<T> rowArr = storage.get(indexRow);
        T oldValue = rowArr.set(indexCol, value);

        // O(1)
        // Note: this can not be used to add new items, only replace
        // existing items.
        return oldValue;
    }

    /**
     * Adds a new row at the specified index.
     * <p>
     * The new row must match the existing column size (unless the grid is empty).
     *
     * @param index  the row index where the new row should be inserted
     * @param newRow the row to insert
     * @return true if the row was successfully added, false otherwise
     * @implNote O(C) where C is the number of columns
     */

    public boolean addRow(int index, DynamicArray<T> newRow) {
        // copy values from newRow to add a row at the row index specified
        // cannot add if the length of newRow does not match existing rows

        if (index < 0 || index > storage.size()) {
            return false;
        }
        //Check if the grid is empty
        if (storage.size() == 0) {
            DynamicArray<T> rowArr = new DynamicArray<>();

            for (int i = 0; i < newRow.size(); i++) {
                rowArr.add(newRow.get(i));
            }
            storage.add(0, rowArr);
            return true;
        }
        //insert in between
        else if (newRow.size() == storage.get(0).size()) {

            DynamicArray<T> rowArr = new DynamicArray<>();
            for (int i = 0; i < newRow.size(); i++) {
                rowArr.add(newRow.get(i));
            }
            storage.add(index, rowArr);
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param index  the column index where the newCol should be inserted in DynamicArray
     * @param newCol col to insert
     * @return true if the column was added succesfully, false otherwise
     */
    public boolean addCol(int index, DynamicArray<T> newCol) {


        //Checking if adding to empty grid
        if (storage.size() == 0) {
            if (index == 0 && newCol.size() == 0) {
                return true;
            } else
                return false;
        }
        //Index of row validity
        if (newCol.size() == storage.size()) {
            //index of column validity
            if (index >= 0 && index <= getNumCol()) {

                for (int i = 0; i < storage.size(); i++) {
                    DynamicArray<T> row = storage.get(i);
                    row.add(index, newCol.get(i));
                }
                return true;
            }
        }

        // copy values from newCol to add a column at the column index specified
        // cannot add if the length of newCol does not match existing columns
        // return true if newCol can be added correctly
        // return false otherwise
        //
        // O(RC) where R is the number of rows and C is the number of columns of the grid
        // Hint: Remember the big-O of the underlying DynamicArray of DynamicArrays

        // Note: this can be used to append columns as well as insert columns
        return false;
    }

    /**
     * Removes and returns the row at the specified index.
     *
     * @param index the row index
     * @return the removed row
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(R) where R is the number of rows
     */
    public DynamicArray<T> removeRow(int index) {

        if (index >= 0 && index < storage.size()) {
            DynamicArray<T> row = storage.get(index);
            storage.remove(index);
            return row;
        } else {
            throw new IndexOutOfBoundsException("Out of bound!");
        }


        // remove and return a row at index x
        // shift rows to remove the gap
        // throw IndexOutOfBoundsException for invalid index
        // Hint: Use the underlying storage to do 90% of this...
        //
        // O(R) where R is the number of rows of the grid
    }


    /**
     * Removes and returns the column at the specified index.
     *
     * @param index the column index
     * @return the removed column
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(RC) where R is rows, C is columns
     */
    public DynamicArray<T> removeCol(int index) {

        if (index >= 0 && index < getNumCol()) {

            DynamicArray<T> removedCol = new DynamicArray<>();
            for (int i = 0; i < storage.size(); i++) {

                T removedElement = storage.get(i).remove(index);
                removedCol.add(removedElement);
            }
            return removedCol;
        } else {
            throw new IndexOutOfBoundsException("Out of bound!");
        }
        // remove and return a col at index x
        // shift columns to remove the gap
        // throw IndexOutOfBoundsException for invalid index
        // Hint: Use the underlying storage to do 90% of this...
        //
        // O(RC) where R is the number of rows and C is the number of columns

    }


    // --------------------------------------------------------
    // example testing code... edit this as much as you want!
    // --------------------------------------------------------


    // Not required, update for your own testing

    /**
     * Returns a string representation of this grid.
     *
     * @return a simple string description
     */
    @Override
    public String toString() {
        return "dynamic grid";
    }


    public static void main(String[] args) {
        // make some simple grids
        DynamicGrid<String> sgrid = new DynamicGrid<>();
        DynamicArray<String> srow = new DynamicArray<>();
        srow.add("English");
        srow.add("Spanish");
        srow.add("German");
        if (sgrid.getNumRow() == 0 && sgrid.getNumCol() == 0 && sgrid.addRow(0, srow)
                && sgrid.getNumRow() == 1 && sgrid.getNumCol() == 3) {
            System.out.println("Yay 1");
        }

        if (sgrid.get(0, 0).equals("English") && sgrid.set(0, 1, "Espanol").equals("Spanish")
                && sgrid.get(0, 1).equals("Espanol")) {
            System.out.println("Yay 2");
        }

        // more complicated grids
        DynamicGrid<Integer> igrid = new DynamicGrid<Integer>();
        boolean ok = true;

        for (int i = 0; i < 3; i++) {
            DynamicArray<Integer> irow = new DynamicArray<>();
            irow.add((i + 1) * 10);
            ok = ok && igrid.addRow(igrid.getNumRow(), irow);
        }
        if (ok && igrid.getNumRow() == 3 && igrid.getNumCol() == 1 && igrid.get(2, 0) == 30) {
            System.out.println("Yay 3");
        }

        DynamicArray<Integer> icol = new DynamicArray<>();
        icol.add(-10);
        icol.add(-20);
        ok = igrid.addCol(1, icol);
        icol.add(-30);
        if (!ok && igrid.addCol(1, icol) && igrid.getNumRow() == 3 && igrid.getNumCol() == 2) {
            System.out.println("Yay 4");
        }

        DynamicArray<Integer> irow = new DynamicArray<>();
        irow.add(5);
        irow.add(10);
        if (!igrid.addRow(5, irow) && igrid.addRow(0, irow) && igrid.getNumRow() == 4 &&
                igrid.get(0, 0) == 5 && igrid.get(3, 1) == -30) {
            System.out.println("Yay 5");
        }
        //System.out.println(igrid.toString());

        irow = igrid.removeRow(2);
        if (igrid.getNumRow() == 3 && igrid.getNumCol() == 2 && irow.get(0) == 20 &&
                igrid.get(0, 1) == 10 && igrid.get(2, 0) == 30) {
            System.out.println("Yay 6");
        }

    }

}