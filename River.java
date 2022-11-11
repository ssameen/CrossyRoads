import java.awt.*;
import java.util.SplittableRandom;

public class River extends World {
    private int nObjects = 1;
    private int logXD = 1;

    public River() {
        super();
        this.setBackground(Color.white);
        this.setObstacles(generateLog());
    }

    private Obstacle[] generateLog() {
        Obstacle[] arrayOb = new Obstacle[nObjects];
        for ( int i = 0; i < nObjects; i++) {
            Obstacle log = new Obstacle(); // log, willDie = false, canStep = true
            arrayOb[i] = log;
        }
        return arrayOb;
    }

    //might need to override check-collision

    private void moveLogR() {
        Obstacle log = this.getObstacles()[1];
        if (log.x >= log.width) {
            log.x = 0;
        }
        log.translate(logXD, 0);
    }

    private void moveLogL() {
        Obstacle log = this.getObstacles()[0];
        logXD *= -1;
        if (log.x <= -log.width) {
            log.x = log.width;
        }
        log.translate(logXD, 0);
    }

    @Override
    public void moveObstacle() {
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {
            moveLogR();
        }
        else if (num==1) {
            moveLogL();
        }
    }
}
