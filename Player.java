public class Player extends Square {
    int x, y, size;

    public Player(int x, int y, int size) {
        super(x, y, size);
    }

    public Square getSquare(){
        return new Square(x, y, size);
    }
}
