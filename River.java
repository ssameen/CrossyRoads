import java.awt.*;
import java.util.SplittableRandom;

public class River extends World {
    private int nObjects = 1;
    private int logXD = 100;
    private int logW= 100;
    private int logDirect;

    public River(double initialHeight) {
        super(initialHeight);
        this.setDanger(true);
        this.setBackground("Icons/river.png");
        this.setObstacles(generateLog());
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {logDirect = 0;}
        else if (num==1) {logDirect = 1;}
    }

    public int rMove(){return logDirect;}

    // add log to river
    private Obstacle[] generateLog() {
        Obstacle[] arrayOb = new Obstacle[nObjects];
        for ( int i = 0; i < nObjects; i++) {
            Obstacle log = new Obstacle(300, this.y, logW, true, false, "Icons/log.png"); //  canStep = true, willDie = false
            arrayOb[i] = log;
        }
        return arrayOb;
    }

    // move from left to right
    private void moveLogR() {
        Obstacle log = this.getObstacles()[0];
        int currentX = log.getX();
        currentX += this.logXD;
        if (currentX >=599) {
            currentX = 0;
        }
        log.setX(currentX);

    }

    // move from right to left
    private void moveLogL() {
        Obstacle log = this.getObstacles()[0];
        int currentX = log.getX();
        currentX -= this.logXD;
        if (currentX < 0) {
            currentX = this.width-100;
        }
        log.setX(currentX);

    }

    @Override
    // each River world randomly have log moving left or right
    public void moveObstacle() {
        super.moveObstacle();
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (this.logDirect == 0) {
            moveLogR();
        }
        else if (this.logDirect == 1) {
            moveLogL();
        }
    }
}
