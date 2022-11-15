import java.awt.*;
import java.util.SplittableRandom;

public class Road extends World {
    private int nCar = 1;
    private int carXD = 100;
    private int carW = 100;
    private int carDirect;

    public Road(double initialHeight) {
        super(initialHeight);
        this.setDanger(false);
        this.setBackground("Icons/road.png");
        this.setObstacles(generateCar());
        SplittableRandom rand = new SplittableRandom();
        int num = rand.nextInt(2);
        if (num == 0) {carDirect = 0;}
        else if (num == 1) {carDirect = 1;}
    }

    // add a car to road
    private Obstacle[] generateCar() {
        Obstacle[] arrayCar = new Obstacle[nCar];
        for (int i = 0; i < nCar; i ++) {
            Obstacle car = new Obstacle(-carW, this.y, carW, false, true, "Icons/car.png"); // willDie = true, canStep = true? or false?
            arrayCar[i] = car;
        }
        return arrayCar;
    }

    // move from left to right
    private void moveCarR() {
        Obstacle car = this.getObstacles()[0];
        int currentX = car.getX();
        if (currentX >= car.getSize() + this.width) {
            currentX = 0;
        }
        car.setX(currentX += this.carXD);
    }

    // move from right to left
    private void moveCarL() {
        Obstacle car = this.getObstacles()[0];
        int currentX = car.getX();
        if (currentX <= -car.getSize()) {
            currentX = this.width;
        }
        car.setX(currentX -= this.carXD);
    }

    @Override
    // each Road world randomly have car moving left or right
    public void moveObstacle() {
        super.moveObstacle();
        if (this.carDirect == 0) {
            moveCarR();
        }
        else if (this.carDirect == 1) {
            moveCarL();
        }

    }

}
