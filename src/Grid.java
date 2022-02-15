import java.util.ArrayList;
import java.util.Random;

// reset method?
// init on click
// TODO: move init and on click into Grid
public class Grid {
		Square[][] rawGrid; // an array of Square objects
    ArrayList<Square> bombs = new ArrayList<>();
		ArrayList<int[]> seeds = new ArrayList<>();
		int diff;

		int[][] offsets = {
                {-1, -1}, { 0, -1}, { 1, -1},
                {-1,  0}, /*Self*/  { 1,  0},
                {-1,  1}, { 0,  1}, { 1,  1}
        };
		

    Grid(int diff){
				this.diff = diff;
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}
    }

		//init grid and bombs in one constructor if bool given
		Grid(int diff, boolean initBombs){
				this.diff = diff;
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
				this.diff = diff;
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
				this.diff = diff;
				this.rawGrid = new Square[diff][diff];

				for (int height = 0; height < diff; height++) {
						for (int width = 0; width < diff; width++) {
								rawGrid[height][width] = new Square(false, width, height);
						}
				}
				
				initBombs( xClicked, yClicked);
				setBorderingBombs();

				onClick(xClicked, yClicked);
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
				for (int[] delta : this.offsets) {
						if (xClicked + delta[0] == xCheck && yClicked + delta[1] == yCheck) {return true;} 
				}
				return nearClicked;
		}

    public void setBorderingBombs() {
        for (Square bomb : this.bombs) {
            for (int[] delta : this.offsets) {
                int x = delta[0] + bomb.x;
                int y = delta[1] + bomb.y;
                try {
                    this.rawGrid[y][x].borderingBombs += 1;
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }
		
		// TODO: clearEmpty() self iterates
		public void clearEmpty() {
				// make a list to add stuff to
				ArrayList<int[]> tempSeeds = new ArrayList<>(seeds);

				tempSeeds.forEach((seed) -> { // from seed
						for (int[] delta : this.offsets){ // check around
								try{
									int pointToClearX = seed[0] + delta[0];
									int pointToClearY = seed[1] + delta[1];
									// if bordering a 0 and that 0 is in seeds
									for (int[] newDelta : this.offsets){
											int borderingPointX = pointToClearX + newDelta[0];
											int borderingPointY = pointToClearY + newDelta[1];

											if (this.rawGrid[borderingPointY][borderingPointX].borderingBombs < 1) {
													this.rawGrid[pointToClearY][pointToClearX].isClear = true;

													// instead add to a list that will add to seeds later
													int[] addCoord = {pointToClearX,pointToClearY};
													seeds.add(addCoord);
											}
									}
								} catch (ArrayIndexOutOfBoundsException ignored) {}
						}
				});
				System.out.println(seeds + "\n");
				
		}

		// TODO: on click function
		public void onClick(int x, int y) {
				if (!this.rawGrid[y][x].isBomb){
						this.seeds.add(new int[]{x,y});
						this.rawGrid[y][x].isClear = true;
						clearEmpty();
				} else {System.out.println("Game Over");}
		}

    // isBomb(x,y)
    // isClicked(x,y)?
}