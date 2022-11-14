import java.awt.*;
import java.util.SplittableRandom;

public class Railroad extends World {
    private int nTrain = 1;
    private int trainXD = 100;
    private int trainW = 100;
    private int trainDirect;

    public Railroad(double initialHeight) {
        super(initialHeight);
        this.setBackground(Color.orange);
        this.setObstacles(generateTrain());
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {trainDirect = 0;}
        else if (num==1) {trainDirect = 1;}
    }

    // add train to Railroad
    private Obstacle[] generateTrain() {
        Obstacle[] arrayTrain = new Obstacle[1];
        for (int i = 0; i < nTrain; i ++) {
            Obstacle train = new Obstacle(-trainW, this.y, trainW, false, true, "TEMP"); // canStep = false, willDie = true
            arrayTrain[i] = train;
        }
        return arrayTrain;
    }

    // move from left to right
    private void moveTrainR() {
        Obstacle train = this.getObstacles()[0];
        int currentX = train.getX();
        if (currentX >= train.getSize() + this.width) {
            currentX = 0;
        }
        train.setX(currentX += this.trainXD);
    }

    // move train from right to left
    private void moveTrainL() {
        Obstacle train = this.getObstacles()[0];
        int currentX = train.getX();
        if (currentX <= -train.getSize()) {
            currentX = this.width;
        }
        train.setX(currentX -= this.trainXD);
    }

    @Override
    // each Railroad world randomly have train moving left or right
    public void moveObstacle() {
        super.moveObstacle();
        if (this.trainDirect == 0) {
            moveTrainR();
        }
        else if (this.trainDirect == 1) {
            moveTrainL();
        }
    }
}
