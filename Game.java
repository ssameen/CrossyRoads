import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class Game extends JComponent {

    static Boolean death = false;
    Player chicken = new Player();
    static int score = 0;
    NodeQueue enviro = new NodeQueue();

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(3));
        paintWorlds(g2);
        scoreText(g2);

    }
    public void playerPaint(Graphics2D g2){

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
            g2.setColor(now.c);
            g2.fillRect(now.x+100, enviro.getSize()*now.height-(i*50)+100, now.width, now.height);

        }
    }

    public Game(){
        for(int i =0; i<6;i++){
            World next = new World();
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
        score++;
        enviro.dequeue();
        World next = new World();
        enviro.enqueue(next);
        this.repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(630, 600);
        frame.setLocation(0, 0);
        Game g = new Game();
        frame.add(g);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        g.run();
    }
}
