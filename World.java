import java.awt.*;
import java.util.Queue;

public abstract class World extends Rectangle{
    private Obstacle[] obstacles;
    private Color background;

    public World() {
        this.width = 500;
        this.height = 100;
    }

    // set obstacles
    public void setObstacles(Obstacle[] obstacles) {this.obstacles = obstacles;}

    // get obstacles
    public Obstacle[] getObstacles() {return obstacles;}

    // set color for world
    protected void setBackground(Color background) {
        this.background = background;
    }

    // get color for world
    public Color getBackground() {return background;}

    // move obstacle in the queue
    public void moveObstacle() {}

    // check if player is in the world
    // if no, who cares,
    // if yes, see if player collides with obstacle of world
    public boolean checkCollision(Player p) {
        boolean collision = false;
        if (checkPlayer(p)) {
            for (Obstacle obstacle: this.getObstacles()) {
                collision = p.getBounds2D().intersects(obstacle.getBounds2D());
                break;
            }
        }
        return collision;
    }

    // find if the player is in world
    public boolean checkPlayer(Player p) {
        return this.contains(p);
    }
}
