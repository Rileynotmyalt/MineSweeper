import java.util.ArrayList;
import java.util.Random;

// Replit sucks, so I have to do this in the console and then convert it later
public class Main {
    public static void main(String[] args) {
        // Difficulties
        int[] diffs = { 8, 10, 15 };
        int chosenDiff = 0;
        int diff = diffs[chosenDiff];


        Square[][] grid = getGrid(diff);

        ArrayList<Square> bombs = getBombs(grid);

        getBorderingBombs(grid, bombs);
        printGrid(grid);
    }

    // console stuff
    public static void printGrid(Square[][] squares) {
        for (Square[] squareRow : squares) {
            for (Square square : squareRow) {
                if (square.isBomb) {
                    System.out.print("!");
                } else {
                    System.out.print(square.borderingBombs);
                }
            }
            System.out.println();
        }
    }

    public static Square[][] getGrid(int diff) {
        Square[][] squares = new Square[diff][diff];
        for (int height = 0; height < diff; height++) {
            for (int width = 0; width < diff; width++) {
                squares[height][width] = new Square(false, width, height);
            }
        }
        return squares;
    }

    // #bombs is 15% total squares
    public static ArrayList<Square> getBombs(Square[][] grid) {
        ArrayList<Square> bombs = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            while (true){
                int x = new Random().nextInt(grid.length);
                int y = new Random().nextInt(grid.length);
                if (!grid[y][x].isBomb) {
                    grid[y][x].isBomb = true;
                    bombs.add(grid[y][x]);
                    break;
                }
            }
        }
        return bombs;
    }


    public static void getBorderingBombs(Square[][] grid, ArrayList<Square> bombs) {
        int[][] offsets = {
                {-1, -1}, { 0, -1}, { 1, -1},
                {-1,  0}, /*Self*/  { 1,  0},
                {-1,  1}, { 0,  1}, { 1,  1}
        };
        for (Square bomb : bombs) {
            for (int[] delta : offsets) {
                int x = delta[0] + bomb.x;
                int y = delta[1] + bomb.y;
                try {
                    grid[y][x].borderingBombs += 1;
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }
}
