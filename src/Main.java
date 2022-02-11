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

				printGrid(new Grid(diff,true,true).rawGrid);
				System.out.println();
				printGrid(new Grid(diff,clickPos[0],clickPos[1]).rawGrid);
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

		int[][] offsets = {
                {-1, -1}, { 0, -1}, { 1, -1},
                {-1,  0}, /*Self*/  { 1,  0},
                {-1,  1}, { 0,  1}, { 1,  1}
        };

		// TODO: clearEmpty() self iterates
		public void clearEmpty() {
				for (int[] seed : this.seeds) {
					for ()
				}
		}

		// TODO: first click function
		public void onClick(int x, int y) {
				Grid grid = new Grid(this.diff, x, y);
				// itirate out from click pos use seeds
				ArrayList<Integer[]> seeds = new ArrayList<>();
				seeds.add({x,y});
				//clear empty function that iterates on its self
		}

		// TODO: on click function
		public void onClick(int x, int y) {
				
		}
}
