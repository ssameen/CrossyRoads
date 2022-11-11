import java.awt.*;
import java.util.SplittableRandom;

public class Road extends World {
    private int nCar = 1;
    private int carXD = 1;

    public Road() {
        super();
        this.setBackground(Color.lightGray);
        this.setObstacles(generateCar());
    }

    private Obstacle[] generateCar() {
        Obstacle[] arrayCar = new Obstacle[nCar];
        for (int i = 0; i < nCar; i ++) {
            Obstacle car = new Obstacle(); // willDie = true, canStep = true? or false?
            arrayCar[i] = car;
        }
        return arrayCar;
    }

    private void moveCarR() {
        Obstacle car = this.getObstacles()[0];
        if (car.x >= car.width) {
            car.x = 0;
        }
        car.translate(carXD, 0);
    }

    private void moveCarL() {
        Obstacle car = this.getObstacles()[0];
        carXD *= -1;
        if (car.x <= -car.width) {
            car.x = car.width;
        }
        car.translate(carXD, 0);
    }

    @Override
    public void moveObstacle() {
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {
            moveCarR();
        }
        else if (num==1) {
            moveCarL();
        }
    }

}
