import java.util.ArrayList;
import java.util.Random;

// Replit sucks, so I have to do this in the console and then convert it later
public class Main {
    public static void main(String[] args) {
        // Difficulties
				int[] diffs = { 8, 10, 15 };
        int chosenDiff = 0;
        int diff = diffs[chosenDiff];

				// click position
				int[] clickPos = {2,2};

				Grid grid = new Grid(diff,clickPos[0],clickPos[1]);
				printGrid(grid.rawGrid);
				printPicked(grid.rawGrid);
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
		
		public static void printPicked(Square[][] squares) {
        for (Square[] squareRow : squares) {
            for (Square square : squareRow) {
                if (square.isClear) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }
}
