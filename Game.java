import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Game extends JComponent implements KeyListener {
    Random rand = new Random();
    static Boolean death = false;
    Player chicken = new Player(400,550, 75, "test");
    static int score = 0;
    static int position = 0;
    NodeQueue enviro = new NodeQueue();

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(3));
        paintWorlds(g2);
        scoreText(g2);
        paintObstacles(g2);
        playerPaint(g2);
    }
    public void playerPaint(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(chicken.getX(), enviro.getSize()*100-(position*100)+125, 50, 50);
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
        for(int i =0; i < enviro.getSize(); i++){
            World now = enviro.check(i);

                Obstacle[] obs = now.getObstacles();
                for (Obstacle o : obs) {
                    if(!(o==null)) {
                        g2.setColor(Color.darkGray);
                        g2.fillRect(o.getX(), enviro.getSize() * now.height - (i * 100) + 125, 50, 50);
                    }
                    }

        }
    }

    public Game(){
        this.addKeyListener(this);
        setFocusable(true);
        requestFocus();
        for(int i =0; i<6;i++){
            World next = newWorld();
            enviro.enqueue(next);
        }

    }

    void run() throws InterruptedException {
        score = 0;
        while(!death){
            TimeUnit.SECONDS.sleep(3);
            tick();

        }
    }

    void tick(){


        enviro.dequeue();
        World next = newWorld();
        enviro.enqueue(next);
        position--;
        moveObjects();
        this.repaint();
    }

    void moveObjects(){
        for(int i =0; i < enviro.getSize(); i++){
            World now = enviro.check(i);

            now.moveObstacle();
        }
    }

    World newWorld(){
        World next;
        if(rand.nextInt(4)==0){
            next = new Road();
        }else if(rand.nextInt(4) == 1){
            next = new Forest();
        }else if(rand.nextInt(4)==2){
            next = new Railroad();
        }else{
            next = new River();
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


            position++;
            score++;
            this.repaint();

    }

    /*private boolean obsAbove(){
        World above = enviro.check(position +1);
        Obstacle[] obs = above.getObstacles();
        for(Obstacle o: obs){
            int[] values = new int[2];
            values[0] = o.getBottomLeft[0];
            values[1] = o.getTopRight[0];
            if(chicken.getTopRight()[0] <= values[1]&&chicken.getTopRight()[0]>=values[0]){
                return true;
            }else if(chicken.getBottomLeft()[0] <= values[1]&&chicken.getBottomLeft()[0]>=values[0]){
                return true;
            }
        }
        return false;
    }*/
    @Override
    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar() == 'w' && position < 7){
            playerUp();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
