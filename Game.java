import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game extends JComponent implements KeyListener {
    Random rand = new Random();
    boolean death = false;
    Player chicken = new Player(400,550, 100, "test");
    static int score = 0;
    static int lastWorld = 0;
    static int playerPosition = 0;
    NodeQueue enviro = new NodeQueue();

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(3));
        if(!death) {
            paintWorlds(g2);
            scoreText(g2);
            paintObstacles(g2);
            playerPaint(g2);
        }else{
            paintDeath(g2);
        }
    }

    private void paintDeath(Graphics2D g2) {
        g2.setColor(Color.black);
        Font f = new Font(Font.SERIF, Font.BOLD, 120);
        g2.setFont(f);

        g2.drawString("You Scored", 0, 300);
        f = new Font(Font.SERIF, Font.BOLD, 200);
        g2.setFont(f);
        g2.drawString(""+score, 250, 550);
    }

    public void playerPaint(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(chicken.getX(), enviro.getSize()*100-(playerPosition*100)+100, 100, 100);
    }

    public void scoreText(Graphics2D g2){
        Font f = new Font(Font.SERIF, Font.BOLD, 30);
        g2.setFont(f);
        g2.setColor(Color.black);
        g2.drawString(""+score, 300, 75);
    }
    public void paintWorlds(Graphics2D g2){
        for(int i =0; i < enviro.getSize(); i++){
            World now = enviro.check(i);
            g2.setColor(now.getBackground());
            g2.fillRect(now.x, enviro.getSize()*now.height-(i*100)+100, now.width, now.height);
        }
    }

    void paintObstacles(Graphics2D g2){
        for(int i = 0; i < enviro.getSize(); i++){
            World now = enviro.check(i);

            Obstacle[] obs = now.getObstacles();
            for (Obstacle o : obs) {
                if(!(o==null)) {
                    g2.setColor(Color.darkGray);
                    g2.fillRect(o.getX(), enviro.getSize() * now.height - (i * 100) + 100, 100, 100);
                }
            }
        }
    }

    public Game(){
        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();
        for(int i =0; i<3;i++){
            World next = new Empty(5.5-i);
            enviro.enqueue(next);
        }
        for(int i =0; i<3;i++){
            World next = newWorld(lastWorld, 5.5 - i+3);
            enviro.enqueue(next);
        }
    }

    void run() throws InterruptedException {
        score = 0;
        while(!death){
            TimeUnit.SECONDS.sleep(3);
            if(death){
                break;
            }
            tick();

        }
    }

    void tick(){
        enviro.dequeue();
        World next = newWorld(lastWorld, 6);
        enviro.enqueue(next);
        playerPosition--;
        chicken.changeY(100);
        if(enviro.check(playerPosition).isDanger()&&touch()){
            if(enviro.check(playerPosition).rMove()==0) {
                chicken.changeX(100);
            }else{
                chicken.changeX(-100);
            }
        }
        moveObjects();
        setDeath();

        this.repaint();
        if(playerPosition <0){
            death= true;
        }
        if(chicken.getX() <-1 || chicken.getX()>599){
            death= true;
        }

    }

    public boolean touch(){
        for (Obstacle obstacle: enviro.check(playerPosition).getObstacles()) {
            return obstacle != null && obstacle.checkCollision(chicken);
        }
        return false;
    }

    public void setDeath() {
        if(!enviro.check(playerPosition).isDanger()) {
            for (Obstacle obstacle : enviro.check(playerPosition).getObstacles()) {
                if (obstacle != null && obstacle.checkDeath(chicken)) {
                    death = true;
                    break;
                } else death = false;
            }
        }
    }

    void moveObjects(){
        for(int i = 0; i < enviro.getSize(); i++){
            World now = enviro.check(i);
            now.moveObstacle();
        }
    }

    World newWorld(int i, double initialHeight){
        World next;
        lastWorld = rand.nextInt(4);
        if(lastWorld == 0){
            next = new Road(initialHeight);
        }else if(lastWorld == 1){
            next = new Forest(initialHeight);
        }else if(lastWorld == 2){
            next = new Railroad(initialHeight);
        }else{
            next = new River(initialHeight);
        }
        if(lastWorld == i){
            next = newWorld(i, initialHeight);
        }
        return next;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(600, 850);
        frame.setLocation(0, 0);
        Game g = new Game();

        frame.add(g);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        g.run();
    }

    private void playerUp() {
        if(playerPosition<5) {
            if (!obsAbove()) {
                playerPosition++;
                score++;
                chicken.changeY(-100);
                this.repaint();
            }else {
                System.out.println("no");
            }
        }
    }

    private void playerRight(){
        if(!obsRight()) {
            chicken.changeX(100);
            this.repaint();
        }else {
            System.out.println("no");
        }
    }

    private void playerLeft(){
        if(!obsLeft()) {
            chicken.changeX(-100);
            this.repaint();
        }else {
            System.out.println("no");
        }
    }

    private boolean obsRight(){
        boolean check = false;
        World here = enviro.check(playerPosition);
        Obstacle[] obs = here.getObstacles();
        for (Obstacle o: obs){
            if(!(o==null)&&!o.getCanStep()){
                if(chicken.getX() + 100 >= o.getX() + 100 || o.getX() >= chicken.getX() + 200) check = false;
                else {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    private boolean obsLeft(){
        boolean check = false;
        World here = enviro.check(playerPosition);
        Obstacle[] obs = here.getObstacles();
        for (Obstacle o: obs){
            if(!(o==null)&&!o.getCanStep()){
                if(chicken.getX() - 100 >= o.getX() + 100 || o.getX() >= chicken.getX()) check = false;
                else {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    // if chicken bottom >= obs top || obs bottom >= chicken top
    private boolean obsAbove(){
        boolean check = false;
        World above = enviro.check(playerPosition + 1);
        Obstacle[] obs = above.getObstacles();
        for(Obstacle o: obs){
            if(!(o==null)&&!o.getCanStep()) {
                if(chicken.getX() >= o.getX() + 100 || o.getX() >= chicken.getX() + 100) check = false;
                else {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'w' && playerPosition < 7){
            playerUp();
        } else if (e.getKeyChar() == 'a'&& chicken.getX()>0) {
            playerLeft();
        } else if (e.getKeyChar() == 'd'&&chicken.getX()<500) {
            playerRight();
        }
        if(enviro.check(playerPosition).isDanger()&&!touch()){
            death=true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
