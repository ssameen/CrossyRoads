import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Forest extends World {
    private int treeSize = 100;

    public Forest(double initialHeight) {
        super(initialHeight);
        this.setDanger(false);
        this.setBackground("Icons/forest.png");
        this.setObstacles(generateTrees());
    }

    // randomly generate tree
    private Obstacle[] generateTrees() {
        // make sure there is space for player to move forward, assuming the width of obstacle is 50
        Obstacle[] ObstacleArray =  new Obstacle[this.width/100 - 1];
        // generate possible unique positions on the rectangles for trees
        Integer[] positionX = new Integer[this.width/100];
        for (int i = 0; i < positionX.length; i++) {
            positionX[i] = i;
        }
        // shuffle the array to get random position for each tree
        Collections.shuffle(Arrays.asList(positionX));
        // generate random number of trees and position each tree using positionX random array
        Random rand = new Random();
        //make between 3 and 5 trees
        for (int i = 0; i < rand.nextInt(3,this.width/100); i++) {
            Obstacle tree = new Obstacle(positionX[i] * 100, this.y, treeSize, false, false, "Icons/tree.png"); // canStep = false, willDie = false
            ObstacleArray[i] = tree;
        }
        return ObstacleArray;
    }
}
