import java.awt.*;
import java.util.SplittableRandom;

public class Railroad extends World {
    private int nTrain = 1;
    private int trainXD = 2;

    public Railroad() {
        super();
        this.setBackground(Color.black);
        this.setObstacles(generateTrain());
    }

    // create train object
    private Obstacle[] generateTrain() {
        Obstacle[] arrayTrain = new Obstacle[1];
        for (int i = 0; i < nTrain; i ++) {
            Obstacle train = new Obstacle(); // canStep = false, willDie = true
            arrayTrain[i] = train;
        }
        return arrayTrain;
    }

    // move from left to right
    private void moveTrainR() {
        Obstacle train = this.getObstacles()[0];
        if (train.x >= train.width ) {
            train.x = 0;
        }
        train.translate(trainXD, 0);
    }

    // move train from right to left
    private void moveTrainL() {
        Obstacle train = this.getObstacles()[0];
        trainXD *= -1;
        if (train.x <= -train.width) {
            train.x = train.width;
        }
        train.translate(trainXD, 0);
    }

    @Override
    public void moveObstacle() {
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {
            moveTrainR();
        }
        else if (num==1) {
            moveTrainL();
        }
    }
}
