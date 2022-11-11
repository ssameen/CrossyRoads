import java.awt.Graphics;

public class Square {
    private int x;
    private int y;
    private int size;
    public Square(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void paintSquare(Graphics g) {
        g.drawRect(x, y, size, size);
    }

    public int[] getTopRight() {
        return new int[]{x+size, y};
    }

    public int[] getBottomLeft() {
        return new int[]{x, y+size};
    }
}
