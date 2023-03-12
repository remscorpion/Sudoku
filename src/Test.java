import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    public static void main(String[] args) {
        String diagram =    "897312456\n" +
                "6135..728\n" +
                "425678913\n" +
                "941867532\n" +
                "538921647\n" +
                "276435891\n" +
                "359186274\n" +
                "764253189\n" +
                "182749365\n";
        Square[][] grid = Sudoku.createSquares(diagram);
        Sudoku.solve(grid);
        String correct =    "897312456\n" +
                "613594728\n" +
                "425678913\n" +
                "941867532\n" +
                "538921647\n" +
                "276435891\n" +
                "359186274\n" +
                "764253189\n" +
                "182749365\n";

        System.out.printf("%s \n %s", correct, Sudoku.toString(grid));
    }
}
