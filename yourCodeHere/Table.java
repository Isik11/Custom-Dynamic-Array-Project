// TO DO: add your implementation and JavaDoc

/**
 * A generic two-dimensional table implementation based on row and column headers.
 *
 * @param <RowT>  the type of row header values
 * @param <ColT>  the type of column header values
 * @param <CellT> the type of cell values
 * @param <OpT>   a combiner type that implements {@link Combiner} for combining row/col into a cell
 */
public class Table<RowT, ColT, CellT, OpT extends Combiner<RowT, ColT, CellT>> {

    private DynamicArray<RowT> rowHead; // rowHead as a list of RowT values
    private DynamicArray<ColT> colHead; // colHead as a list of ColT values
    private DynamicGrid<CellT> board; // a 2-D grid of CellT values determined by rowHead, colHead, and op
    private OpT op; // op that defines a function f: f(RowT,ColT)-> CellT

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

    /**
     * Constructs an empty table with no rows or columns.
     *
     * @param op the combiner used to generate cell values from row and column headers
     */
    public Table(OpT op) {
        rowHead = new DynamicArray<>();
        colHead = new DynamicArray<>();
        board = new DynamicGrid<>();
        this.op = op;
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return number of rows
     * @implNote O(1)
     */
    public int getSizeRow() {
        // report the number of rows in board
        // O(1)
        return rowHead.size();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return number of columns
     * @implNote O(1)
     */
    public int getSizeCol() {
        // report the number of columns in board
        // O(1)
        return colHead.size();

    }

    /**
     * Retrieves the row header at the specified index.
     *
     * @param r row index
     * @return the row header
     * @throws IndexOutOfBoundsException if {@code r} is invalid
     * @implNote O(1)
     */
    public RowT getRowHead(int r) {

        if (r >= 0 && r < rowHead.size()) {
            return rowHead.get(r);
        } else {
            throw new IndexOutOfBoundsException("Out of bound!");
        }
        // return the item at index r from rowHead
        // throw IndexOutOfBoundsException for invalid index
        // O(1)

    }

    /**
     * Retrieves the column header at the specified index.
     *
     * @param c column index
     * @return the column header
     * @throws IndexOutOfBoundsException if {@code c} is invalid
     * @implNote O(1)
     */

    public ColT getColHead(int c) {
        if (c >= 0 && c < colHead.size()) {
            return colHead.get(c);
        } else {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        // return the item at index c from colHead
        // throw IndexOutOfBoundsException for invalid index
        // O(1)

    }

    /**
     * Retrieves the cell value at the given row and column indices.
     *
     * @param r row index
     * @param c column index
     * @return the cell value
     * @throws IndexOutOfBoundsException if indices are invalid
     * @implNote O(1)
     */
    public CellT getCell(int r, int c) {

        if (r < 0 || r > rowHead.size() || c < 0 || c >= colHead.size()) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }
        return board.get(r, c);
        // return the cell at row r, column c from board
        // throw IndexOutOfBoundsException for invalid index
        // O(1)
    }

    /**
     * Updates the combiner operation and recalculates all cell values.
     *
     * @param op the new combiner
     * @implNote O(RC)
     */
    public void setOp(OpT op) {

        this.op = op;

        for (int i = 0; i < rowHead.size(); i++) {
            for (int j = 0; j < colHead.size(); j++) {
                CellT value = op.combine(rowHead.get(i), colHead.get(j));
                board.set(i, j, value);
            }
        }
        // change the operation
        // re-calculate and reset the cells of the board
        //
        // O(CR) where C is the number of columns and R is the number of rows of the
        // grid
    }

    /**
     * Adds a new row at the specified index.
     *
     * @param i the row index where the row should be inserted
     * @param v the row header value
     * @return true if successfully added, false otherwise
     * @implNote O(R + C)
     */
    public boolean addRow(int i, RowT v) {

        if (i < 0 || i > rowHead.size()) {
            return false;
        }

        rowHead.add(i, v);
        DynamicArray<CellT> newRow = new DynamicArray<>();
        for (int k = 0; k < colHead.size(); k++) {
            CellT value = op.combine(v, colHead.get(k));
            newRow.add(value);
        }
        board.addRow(i, newRow);
        // insert v to rowHead at index i
        // also insert a new row to the grid at row index i
        // calculate the new row based on v, existing colHead and op
        //
        // i may be equal to the size (indicating that you are appending a row)
        //
        // O(C+R) where R is the number of rows of the grid and
        // C is the number of columns of the grid
        return true;

    }

    /**
     * Adds a new column at the specified index.
     *
     * @param i the column index where the column should be inserted
     * @param v the column header value
     * @return true if successfully added, false otherwise
     * @implNote O(RC)
     */
    public boolean addCol(int i, ColT v) {
        if (i < 0 || i > colHead.size()) {
            return false;
        }
        colHead.add(i, v);
        DynamicArray<CellT> newCol = new DynamicArray<>();
        for (int k = 0; k < rowHead.size(); k++) {
            CellT value = op.combine(rowHead.get(k), v);
            newCol.add(value);
        }

        board.addCol(i, newCol);

        // insert v to colHead at index i
        // also insert a new column to the grid at column index i
        // calculate the new column based on v, existing rowHead and op
        // i may be equal to the size (indicating that you are appending a column)
        //
        // O(CR) where R is the number of rows of the grid and
        // C is the number of columns of the grid
        return true;

    }

    /**
     * Removes and returns the row header at the specified index.
     *
     * @param i the row index
     * @return the removed row header
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(R)
     */
    public RowT removeRow(int i) {

        if (i < 0 || i >= rowHead.size()) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        RowT removeHead = rowHead.remove(i);

        //remove from board
        board.removeRow(i);
        // remove and return value from rowHead at index i
        // also remove row i from grid
        // throw IndexOutOfBoundsException for invalid index
        //
        // O(R) where R is the number of rows of the grid
        return removeHead;

    }

    /**
     * Removes and returns the column header at the specified index.
     *
     * @param i the column index
     * @return the removed column header
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(RC)
     */
    public ColT removeCol(int i) {

        if (i < 0 || i >= colHead.size()) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        ColT removeHead = colHead.remove(i);

        //remmove column
        board.removeCol(i);


        // remove and return value from colHead at index i
        // also remove column i from grid
        // throw IndexOutOfBoundsException for invalid index
        //
        // O(CR) where R is the number of rows and
        // C is the number of columns of the grid
        return removeHead;

    }

    /**
     * Replaces the row header at the specified index and updates its corresponding row.
     *
     * @param i the row index
     * @param v the new row header value
     * @return the old row header value
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(C)
     */
    public RowT setRow(int i, RowT v) {

        if (i < 0 || i >= rowHead.size()) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        RowT oldHead = rowHead.get(i);

        //update head
        rowHead.set(i, v);
        for (int k = 0; k < colHead.size(); k++) {
            CellT value = op.combine(v, colHead.get(k));
            board.set(i, k, value);
        }
        // change value of rowHead at index i to be v
        // also change the ith row of grid using v, the ColTs, and op
        // return old value of rowHead from index i
        // throw IndexOutOfBoundsException for invalid index
        //
        // O(C) where C is the number of columns of the grid
        return oldHead;

    }

    /**
     * Replaces the column header at the specified index and updates its corresponding column.
     *
     * @param i the column index
     * @param v the new column header value
     * @return the old column header value
     * @throws IndexOutOfBoundsException if index is invalid
     * @implNote O(R)
     */
    public ColT setCol(int i, ColT v) {
        if (i < 0 || i >= colHead.size()) {
            throw new IndexOutOfBoundsException("Out of bound!");
        }

        ColT oldHead = colHead.get(i);

        colHead.set(i, v);
        for (int k = 0; k < rowHead.size(); k++) {
            CellT value = op.combine(rowHead.get(k), v);
            board.set(k, i, value);
        }
        // change value of colHead at index i to be v
        // also change the ith column of grid using v, the RowTs, and op
        // return old value of colHead from index i
        // throw IndexOutOfBoundsException for invalid index
        //
        // O(R) where R is the number of rows of the grid
        return oldHead;

    }

    // --------------------------------------------------------
    // PROVIDED for you to help with testing
    // More testing code you can change further down...
    // --------------------------------------------------------

    /**
     * Find the width we should use to print the specified column.
     *
     * @param colIndex column index to specify which column of the grid to check
     *                 width
     * @return an integer to be used to for formatted printing of the column
     */

    private int getColMaxWidth(int colIndex) {
        int ans = -1;
        for (int i = 0; i < this.getSizeRow(); i++) {
            int width = (this.getCell(i, colIndex)).toString().length();
            if (ans < width)
                ans = width;
        }
        return ans + 1;
    }

    /**
     * Find the width we should use to print the rowHead.
     *
     * @return an integer to be used to for formatted printing of the rowHead
     */

    private int getRowHeadMaxWidth() {
        int ans = -1;
        for (int i = 0; i < this.getSizeRow(); i++) {
            int width = (rowHead.get(i)).toString().length();
            if (ans < width)
                ans = width;
        }
        return ans + 1;
    }

    /**
     * Construct a string representation of the table.
     *
     * @return a string representation of the table
     */

    @Override
    public String toString() {

        if (getSizeRow() == 0 && getSizeCol() == 0) {
            return "Empty Table";
        }

        // basic info of op and size
        StringBuilder sb = new StringBuilder("============================\nTable\n");
        sb.append("Operation: " + op.getClass() + "\n");
        sb.append("Size: " + getSizeRow() + " rows, " + getSizeCol() + " cols\n");

        // decide how many chars to use for rowHead
        int rowHeadWidth = getRowHeadMaxWidth();
        int totalWidth = rowHeadWidth;
        DynamicArray<Integer> colWidths = new DynamicArray<>();
        sb.append(String.format(String.format("%%%ds", rowHeadWidth), " "));

        // colHead
        for (int i = 0; i < getSizeCol(); i++) {
            int colWidth = getColMaxWidth(i);
            colWidths.add(colWidth);
            totalWidth += colWidth + 1;
            sb.append(String.format(String.format("|%%%ds", colWidth), colHead.get(i)));
        }

        sb.append("\n" + String.format(String.format("%%%ds", totalWidth), " ").replace(" ", "-") + "\n");

        // row by row
        for (int i = 0; i < getSizeRow(); i++) {
            sb.append(String.format(String.format("%%%ds", rowHeadWidth), rowHead.get(i)));
            for (int j = 0; j < getSizeCol(); j++) {
                int colWidth = colWidths.get(j);
                sb.append(String.format(String.format("|%%%ds", colWidth), board.get(i, j)));
            }
            sb.append("\n");
        }
        sb.append("============================\n");
        return sb.toString();

    }

    // --------------------------------------------------------
    // example testing code... edit this as much as you want!
    // --------------------------------------------------------

    public static void main(String[] args) {
        StringAdder sa = new StringAdder();
        Table<String, String, String, StringAdder> stable = new Table<>(sa);
        stable.addRow(0, "red");
        stable.addRow(1, "yellow");
        stable.addCol(0, "apple");

        if (stable.getSizeRow() == 2 && stable.getSizeCol() == 1 &&
                stable.getCell(0, 0).equals("red apple") && stable.getCell(1, 0).equals("yellow apple")) {
            System.out.println("Yay 1");
        }
        // System.out.println(stable.toString());

        stable.addCol(1, "banana");
        stable.addCol(1, "kiwi");
        stable.addRow(2, "green");
        if (stable.getSizeRow() == 3 && stable.getSizeCol() == 3 && stable.getRowHead(2).equals("green")
                && stable.getColHead(2).equals("banana") && stable.getCell(2, 1).equals("green kiwi")) {
            System.out.println("Yay 2");
        }
        // System.out.println(stable.toString());

        stable.removeRow(0);
        stable.setCol(2, "orange");
        if (stable.getSizeRow() == 2 && stable.getSizeCol() == 3 && stable.getRowHead(0).equals("yellow")
                && stable.getColHead(2).equals("orange") && stable.getCell(0, 2).equals("yellow orange")) {
            System.out.println("Yay 3");
        }
        // System.out.println(stable.toString());

        Table<Integer, Integer, Integer, IntegerComb> itable = new Table<>(new IntegerAdder());
        for (int i = 0; i < 5; i++) {
            itable.addRow(itable.getSizeRow(), i + 1);
            itable.addCol(0, (i + 1) * 10);
        }
        if (itable.getSizeRow() == 5 && itable.getSizeCol() == 5 && itable.getCell(0, 0) == 51
                && itable.getCell(4, 0) == 55 && itable.getCell(3, 4) == 14) {
            System.out.println("Yay 4");
        }
        // System.out.println(itable.toString());

        itable.setOp(new IntegerTimer());
        if (itable.getSizeRow() == 5 && itable.getSizeCol() == 5 && itable.getCell(0, 0) == 50
                && itable.getCell(4, 0) == 250 && itable.getCell(3, 4) == 40) {
            System.out.println("Yay 5");
        }
        // System.out.println(itable.toString());



        Table<Integer, Integer,Integer,IntegerTimer> multTable = new Table<>(new IntegerTimer());


        multTable.addRow(0,2);
        multTable.addRow(1,3);
        multTable.addRow(7,8);
        multTable.addRow(1,2);

        multTable.addCol(2,1);
        multTable.addCol(0,2);
        multTable.addCol(1,5);
        System.out.println(multTable.toString());

    }

}