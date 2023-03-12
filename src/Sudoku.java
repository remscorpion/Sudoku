import java.util.Arrays;

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
        Location[] temp = new Location[8];
        int i = 0;
        for (int j = 0; j < 9; j++) {
            if (j != here.column) {
                temp[i] = createLocation(here.row, j);
                i++;
            }
        }

        return temp;
    }

    /** Returns an array of the eight Locations in the same column as here. */
    static Location[] findColumn(Location here) {
        // TODO You have to write this
        Location[] temp = new Location[8];
        int i = 0;
        for (int j = 0; j < 9; j++) {
            if (j != here.row) {
                temp[i] = createLocation(j, here.column);
                i++;
            }
        }

        return temp;
    }

    /** Returns an array of the eight Locations in the same 3x3 block as here. */
    static Location[] findBlock(Location here) {
        // TODO You have to write this
        Location[] temp = new Location[8];
        int index = 0;

        int r = here.row - here.row % 3;
        int c = here.column - here.column % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (i == here.row && j == here.column) {
                    continue;
                } else {
                    Location t = new Location();
                    t.row = i;
                    t.column = j;
                    temp[index++] = t;
                }
            }
        }
        return temp;
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block.
     */
    static Square[][] createSquares() {
        // TODO You have to write this
        Square[][] grid = new Square[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Square n = new Square();
                n.value = 0;
                grid[i][j] = n;
            }
        }
        resetSquares(grid);
        return grid;
    }

    /** Returns a String representing grid, showing the numbers (or . for squares with value 0). */
    static String toString(Square[][] grid) {
        // TODO You have to write this
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].value == 0) {
                    row.append(".");
                } else {
                    row.append(grid[i][j].value);
                }
            }
            row.append("\n");
            temp.append(row);
        }

        return temp.toString();
    }

    /**
     * Returns a 9x9 array of Squares, each of which has value 0 and knows about the other squares in its row,
     * column, and block. Any numbers in diagram are filled in to the grid; empty squares should be given as
     * periods.
     */
    static Square[][] createSquares(String diagram) {
        // TODO You have to write this
        Square[][] grid = new Square[9][9];
        int i = 0, j = 0, index = 0;
        while (index < diagram.length()) {
            if (diagram.charAt(index) == '\n') {
                j = 0;
                i++;
            } else {
                Square n = new Square();
                if (diagram.charAt(index) == '.') {
                    n.value = 0;
                } else {
                    n.value = Character.getNumericValue(diagram.charAt(index));
                }
                grid[i][j++] = n;
            }
            index++;
        }

        resetSquares(grid);
        return grid;
    }

    /**
     * Returns a boolean array of length 10. For each digit, the corresponding entry in the array is true
     * if that number does not appear elsewhere in s's row, column, or block.
     */
    static boolean[] findValidNumbers(Square s) {
        // TODO You have to write this
        boolean[] re = new boolean[10];
        Arrays.fill(re, true);

        for (int i = 0; i < re.length; i++) {
            // TODO In row
            for (Square t : s.row) {
                if (i == t.value) {
                    re[i] = false;
                    break;
                }
            }

            // TODO In Column
            for (Square t : s.column) {
                if (i == t.value) {
                    re[i] = false;
                    break;
                }
            }
            // TODO In block
            for (Square t : s.block) {
                if (i == t.value) {
                    re[i] = false;
                    break;
                }
            }
        }

        return re;
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

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].value == 0) {
                    boolean[] vail = findValidNumbers(grid[i][j]);
                    for (int k = 1; k < vail.length; k++) {
                        grid[i][j].value = k;
                        resetSquares(grid);

                        if (vail[k] && solve(grid)){
                            return true;
                        } else {
                            grid[i][j].value = 0;
                            resetSquares(grid);
                        }
                    }

                    return false;
                } // end if
            } // end for j
        } // end for i

        return true;
    }

    /***
     * set(reset) row column block of each Square in the board
     * @param grid the board
     */
    static void resetSquares(Square[][] grid) {
        int i = 0, j = 0, index = 0;
        // Add row, column, block info into each square
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                Location here = new Location();
                here.row = i;
                here.column = j;

                // TODO Find row
                Location[] row_locations = findRow(here);
                Square[] row = new Square[8];
                index = 0;
                for (Location loc: row_locations) row[index++] = grid[loc.row][loc.column];
                grid[i][j].row = row;

                // TODO Find column
                Location[] column_locations = findColumn(here);
                Square[] column = new Square[8];
                index = 0;
                for (Location loc: column_locations) column[index++] = grid[loc.row][loc.column];
                grid[i][j].column = column;

                // TODO Find block
                Location[] block_locations = findBlock(here);
                Square[] block = new Square[8];
                index = 0;
                for (Location loc: block_locations) block[index++] = grid[loc.row][loc.column];
                grid[i][j].block = block;
            }
        }
    }


}
