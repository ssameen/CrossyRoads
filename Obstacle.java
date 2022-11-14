public class Obstacle{
    private boolean canStep, willDie;
    private String imagePath;
    private int x, y, size;

    public Obstacle(int x, int y, int size, boolean canStep, boolean willDie, String imagePath){
        this.canStep = canStep;
        this.willDie = willDie;
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public boolean isCanStep() {
        return canStep;
    }

    public boolean isWillDie() {
        return willDie;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private Square getSquare(){
        return new Square(x, y, size);
    }

    private boolean checkCollision(Player player){
        Square playerSquare = player.getSquare();
        Square obstacleSquare = this.getSquare();
        int x1 = playerSquare.getBottomLeft()[0], y1 = playerSquare.getBottomLeft()[1];
        int x2 = playerSquare.getTopRight()[0], y2 = playerSquare.getTopRight()[1];
        int x3 = obstacleSquare.getBottomLeft()[0], y3 = obstacleSquare.getBottomLeft()[1];
        int x4 = obstacleSquare.getTopRight()[0], y4 = obstacleSquare.getTopRight()[1];
        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    public boolean checkDeath(Player player){
        return willDie && checkCollision(player);
    }
}
