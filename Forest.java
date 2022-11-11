import java.awt.*;
import java.util.Random;

public class Forest extends World {

    public Forest() {
        super();
        this.setBackground(Color.green);
        this.setObstacles(generateTrees());
    }

    // randomly generate tree
    private Obstacle[] generateTrees() {
        // make sure there is space for player to move forward, assuming the width of obstacle is 50
        Obstacle[] ObstacleArray =  new Obstacle[this.width/50 - 1];
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(1,this.width/50); i++) {
            Obstacle tree = new Obstacle(); // canStep = false, willDie = false
            ObstacleArray[i] = tree;
        }
        return ObstacleArray;
    }

}
