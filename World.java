import java.awt.*;

public abstract class World extends Rectangle{
    private Obstacle[] obstacles;
    private Color background;
    int y;

    public World(double initialHeight) {
        this.y = (int) initialHeight * 100;
        this.width = 600;
        this.height = 100;
    }

    public void changeY(int y) {
        this.y += y;
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

     //move obstacles along with world, and override to move obstacle horizontally in subclasses if needed
    public void moveObstacle() {
        for (Obstacle o : this.getObstacles()) {
            if (o != null) {
                o.setY(this.y + 100);
            }
        }
    }
}
