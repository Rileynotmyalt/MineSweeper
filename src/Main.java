import java.util.ArrayList;
import java.util.Random;

// Replit sucks so i have to do this in the console and then convert it later
public class Main {
    public static void main(String[] args) {
        // Difficulties
        int[] diffs = { 8, 10, 15 };
        int chosenDiff = 0;
        int diff = diffs[chosenDiff];

        // #bombs is 15% total squares
        int numBombs = Math.round((int)(Math.pow(diff,2)*0.15));

        // create square array
        Square[][] squares = new Square[diff][diff];


        for (int height = 0; height < diff; height++) {
            for (int width = 0; width < diff; width++) {
                squares[height][width] = new Square(false, width, height);
            }
        }
        // add bombs and create bomb list
        ArrayList<Square> bombs = new ArrayList<>();
        for (int i = 0; i < numBombs; i++) {
            while (true){
                int x = new Random().nextInt(diff);
                int y = new Random().nextInt(diff);
                if (!squares[y][x].isBomb) {
                    squares[y][x].isBomb = true;
                    bombs.add(squares[y][x]);
                    break;
                }
            }
        }

        getBombs(squares,bombs);
        printGrid(squares);
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

    public static void getBombs(Square[][] squares, ArrayList<Square> bombs) {
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
                    squares[y][x].borderingBombs += 1;
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }
}
