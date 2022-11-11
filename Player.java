import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Square {
    private int x, y, size;
    String imagePath;

    public Player(int x, int y, int size, String imagePath) {
        super(x, y, size);
        this.imagePath = imagePath;
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

    public void move(JFrame frame, World currentWorld, NodeQueue enviro, Player player) {
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
                    case KeyEvent.VK_UP:
                        //need to figure out moving up
                        if (enviro.getAboveWorld().canStepUp()){
                            y += 50;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (world.canStepRight(player)) {
                            x += 50;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (world.canStepLeft(player)){
                            x -= 50;
                        }
                        break;
                }
            }
        });
    }
}
