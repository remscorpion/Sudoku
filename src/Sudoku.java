/**
 * The Sudoku solver.
 *
 * @author PUT THE FIRST NAME HERE
 * @author PUT THE SECOND NAME HERE
 */
public class Sudoku {

    /** Prints the solution to the puzzle in the specified directory. */
    public static void main(String[] args) {
        String puzzle = new In("sudoku1.txt").readAll();
        Square[][] grid = createSquares(puzzle);
        solve(grid);
        StdOut.println(toString(grid));
    }

    /** Returns a new Location obj
     * ect with the specified row and column. */
    static Location createLocation(int r, int c) {
        // TODO You have to write this
        Location temp = new Location();
        temp.row = r;
        temp.column = c;

        return temp;
    }

    /** Returns an array of the eight Locations in the same row as here. */
    static Location[] findRow(Location here) {
        // TODO You have to write this

        return null;
    }

    /** Returns an array of the eight Locations in the same column as here. */
    static Location[] findColumn(Location here) {
        // TODO You have to write this
        return null;
    }

    /** Returns an array of the eight Locations in the same 3x3 block as here. */
    static Location[] findBlock(Location here) {
        // TODO You have to write this
        return null;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block.
     */
    static Square[][] createSquares() {
        // TODO You have to write this
        return null;
    }

    /** Returns a String representing grid, showing the numbers (or . for squares with value 0). */
    static String toString(Square[][] grid) {
        // TODO You have to write this
        return null;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block. Any numbers in diagram are filled in to the grid; empty squares should be given as
     * periods.
     */
    static Square[][] createSquares(String diagram) {
        // TODO You have to write this
        return null;
    }

    /**
     * Returns a boolean array of length 10. For each digit, the corresponding entry in the array is true
     * if that number does not appear elsewhere in s's row, column, or block.
     */
    static boolean[] findValidNumbers(Square s) {
        // TODO You have to write this
        return null;
    }

    /**
     * Returns true if grid can be solved. If so, grid is modified to fill in that solution.
     */
    static boolean solve(Square[][] grid) {
        // TODO You have to write this
        // Here's an outline of the algorithm:
        // for each square
        //     if its value is 0
        //         for each valid number that could be filled in
        //             if you can solve the rest of the grid
        //                 return true
        //         nothing worked: set value back to 0 and return false
        // no squares left to fill in: return true
        return true;
    }

}
