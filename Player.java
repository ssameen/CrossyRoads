import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Square {
    private int x, y, size;
    String imagePath;

    public Player(int x, int y, int size, String imagePath) {
        super(x, y, size);
        this.x = x;
        this.imagePath = imagePath;
    }
    public void changeX(int d){
        this.x += d;
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

    public void move(JFrame frame) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //not using
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //not using
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP -> ++y;
                    case KeyEvent.VK_RIGHT -> ++x;
                    case KeyEvent.VK_LEFT -> --x;
                }
            }
        });
    }
}
