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
            while (true) {
                x = new Random().nextInt(grid.length);
                y = new Random().nextInt(grid.length);
								if (!grid[y][x].isBomb){
									grid[y][x].isBomb = true;
									this.bombs.add(grid[y][x]);
									break;
								}
            } 
        }
    }

		// TODO add xClicked & yClicked
		public void initBombs(int xClicked, int yClicked) {
				int x;
				int y;
				Square[][] grid = this.rawGrid;
        for (int i = 0; i < grid.length; i++) {
            while (true) {
                x = new Random().nextInt(grid.length);
                y = new Random().nextInt(grid.length);
								// & if bomb not in radius of clicked
                if (!grid[y][x].isBomb && !bombNearClicked(xClicked,yClicked,x,y)) {
                    grid[y][x].isBomb = true;
                    this.bombs.add(grid[y][x]);
										break;
                }
            } // while (!bombNearClicked( xClicked, yClicked));
        }
    }

		// maybe change later, rather than no bombs, limit #
		// possibly make a certian area that would open?
		public boolean bombNearClicked(int xClicked, int yClicked, int xCheck, int yCheck) {
				boolean nearClicked = false;
				int[][] offsets = {
                {-1, -1}, { 0, -1}, { 1, -1},
                {-1,  0}, /*Self*/  { 1,  0},
                {-1,  1}, { 0,  1}, { 1,  1}
        };
				for (int[] delta : offsets) {
						if (xClicked + delta[0] == xCheck && yClicked + delta[1] == yCheck) {return true;} 
				}
				return nearClicked;
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