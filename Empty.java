import java.awt.*;
import java.util.SplittableRandom;

public class Empty extends World{

    public Empty(double initialHeight) {

            super(initialHeight);
            Obstacle[] o = new Obstacle[0];
            this.setObstacles(o);
            this.setDanger(false);
            this.setBackground(Color.green);


    }
}
