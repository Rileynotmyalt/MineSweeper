public class Square {
    boolean isBomb;
    int borderingBombs;
    int x;
    int y;

    public Square(boolean isBomb,int x, int y) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
    }

}
