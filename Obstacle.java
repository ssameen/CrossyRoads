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

    public boolean getCanStep() {
        return canStep;
    }

    public boolean getWillDie() {
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
        if (playerSquare.getTopLeft()[0] == obstacleSquare.getTopLeft()[0]) return true;
        else return false;
    }

    public boolean checkDeath(Player player){
        return willDie && checkCollision(player);
    }
}
