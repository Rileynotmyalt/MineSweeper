import java.util.ArrayList;
import java.util.Random;

// reset method?
// init on click
public class Grid {
		Square[][] rawGrid; // an array of Square objects
    ArrayList<Square> bombs = new ArrayList<>();
		

    Grid(int diff){
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}
    }

		//init grid and bombs in one constructor if bool given
		Grid(int diff, boolean initBombs){
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}

				if (initBombs) {initBombs();}
    }

		//init grid bombs and squares in one constructor
		Grid(int diff, boolean initBombs, boolean initBorderingBombs){
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}

				if (initBombs) {initBombs();}
				if (initBorderingBombs) {setBorderingBombs();}
    }

		// first 3 constructors are probably reduntand
		// creates a grid with starting click borderingBombs==0
		Grid(int diff, int xClicked, int yClicked) {
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}
				
				initBombs( xClicked, yClicked);
				setBorderingBombs();
    }

		public Square[] getRow(int row) {
			return rawGrid[row];
		}

		// #bombs is 15% total squares
    public void initBombs() {
				int x;
				int y;
				Square[][] grid = this.rawGrid;
        for (int i = 0; i < grid.length; i++) {
            do {
                x = new Random().nextInt(grid.length);
                y = new Random().nextInt(grid.length);
								grid[y][x].isBomb = true;
								this.bombs.add(grid[y][x]);
            } while (!grid[y][x].isBomb);
        }
    }

		// TODO add xClicked & yClicked
		public void initBombs(int xClicked, int yClicked) {
				int x;
				int y;
				Square[][] grid = this.rawGrid;
        for (int i = 0; i < grid.length; i++) {
            do {
                x = new Random().nextInt(grid.length);
                y = new Random().nextInt(grid.length);
                if (!grid[y][x].isBomb) {
                    grid[y][x].isBomb = true;
                    this.bombs.add(grid[y][x]);
                }
            } while (!bombNearClicked( xClicked, yClicked));
        }
    }


		// this is redundant. need to check before def as bomb
		public boolean bombNearClicked(int xClicked, int yClicked) {
				return (this.rawGrid[yClicked][xClicked].borderingBombs > 0 && this.rawGrid[yClicked][xClicked].isBomb);
		}

    public void setBorderingBombs() {
        int[][] offsets = {
                {-1, -1}, { 0, -1}, { 1, -1},
                {-1,  0}, /*Self*/  { 1,  0},
                {-1,  1}, { 0,  1}, { 1,  1}
        };
        for (Square bomb : this.bombs) {
            for (int[] delta : offsets) {
                int x = delta[0] + bomb.x;
                int y = delta[1] + bomb.y;
                try {
                    this.rawGrid[y][x].borderingBombs += 1;
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }

    // isBomb(x,y)
    // isClicked(x,y)?
}