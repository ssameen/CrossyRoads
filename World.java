import java.awt.*;

public abstract class World extends Rectangle{
    private Obstacle[] obstacles;
    private Color background;

    public World() {
        // assuming frame is 600x600, so height is 630 to include the bar on top
        // world height = 100
        // start each world at the bottom of screen, so 630 -100
        this.y = 630 - 100;
        this.width = 600;
        this.height = 100;
    }

    // set obstacles
    protected void setObstacles(Obstacle[] obstacles) {this.obstacles = obstacles;}

    // get obstacles
    public Obstacle[] getObstacles() {return obstacles;}

    // set color for world
    protected void setBackground(Color background) {
        this.background = background;
    }

    // get color for world
    public Color getBackground() {return background;}

    // move obstacles along with world, and override to move obstacle horizontally in subclasses if needed
    public void moveObstacle() {
        for (Obstacle o : this.getObstacles()) {
            if (o != null) {
                o.setY(this.y + 15);
            }
        }
    }

    // use Obstacle's check collision, I haven't checked this at all, let me know if it doesn't work
    public boolean checkCollision(Player p) {
        boolean collision = false;
        for (Obstacle o : this.getObstacles()) {
            if (o != null && o.checkDeath(p)) {
                collision = true;
            }
        }
        return collision;
    }

}
