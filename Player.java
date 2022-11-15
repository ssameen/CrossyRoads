public class Player extends Square {
    private int x, y, size;
    String imagePath;

    public Player(int x, int y, int size, String imagePath) {
        super(x, y, size);
        this.x = x;
        this.y = y;
        this.imagePath = imagePath;
    }

    public void changeX(int x){
        this.x += x;
    }

    public void changeY(int y){
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Square getSquare(){
        return new Square(x, y, size);
    }

    public String getImagePath() {
        return "Icons/chicken.png";
    }
}
