import java.util.ArrayList;
import java.util.Random;

// Replit sucks, so I have to do this in the console and then convert it later
public class Main {
    public static void main(String[] args) {
        // Difficulties
        int[] diffs = { 8, 10, 15 };
        int chosenDiff = 0;
        int diff = diffs[chosenDiff];

				printGrid(new Grid(diff,true,true).rawGrid);
				System.out.println();
				printGrid(new Grid(diff,2,2).rawGrid);
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

}
