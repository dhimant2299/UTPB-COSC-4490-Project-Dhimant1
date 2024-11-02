import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;





public class GameWindow extends JPanel implements KeyListener {
    private Ball ball;  
    private List<Spike> spikes;
    private List<Platform> platforms;
    private Goal goal;
    private boolean gameOver = false;
    private boolean gameWon = false;
    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;

    public GameWindow() {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }
    private void initializeGameObjects() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) screenSize.getWidth();
        int windowHeight = (int) screenSize.getHeight();

        /* My plan here is to separate the gameplay code from the level data,
         allowing to create multiple levels without altering main game code.
          By creating different JSON files for each level, I will generate and load many levels,
           opening up the potential for a level-based game structure. */

        ball = new Ball(windowWidth / 2, windowHeight - 100, 25);

        spikes = new ArrayList<>();
        spikes.add(new Spike(60, windowHeight - 2, 20));
        spikes.add(new Spike(200, windowHeight - 2, 20));
        spikes.add(new Spike(350, windowHeight - 200, 20));
        spikes.add(new Spike(500, windowHeight - 300, 20));
        spikes.add(new Spike(700, windowHeight - 400, 20));
        spikes.add(new Spike(950, windowHeight - 500, 20));
        spikes.add(new Spike(1000, windowHeight - 2, 20));
        spikes.add(new Spike(1400, windowHeight - 2, 20));
        //spikes.add(new Spike(1400, windowHeight - 700, 15));
        spikes.add(new Spike(1350, windowHeight - 2, 20));
        spikes.add(new Spike(1400, windowHeight - 2, 20));
        spikes.add(new Spike(1450, windowHeight - 2, 20));
        spikes.add(new Spike(1500, windowHeight - 2, 20));
        
        platforms = new ArrayList<>();
        platforms.add(new Platform(300, windowHeight - 200, 700, 20));
        platforms.add(new Platform(500, windowHeight - 300, 450, 20));
        platforms.add(new Platform(700, windowHeight - 400, 400, 20));
        platforms.add(new Platform(900, windowHeight - 500, 500, 20));
        platforms.add(new Platform(1250, windowHeight - 600, 50, 20));
        platforms.add(new Platform(1350, windowHeight - 700, 800, 20));

        goal = new Goal(1500, windowHeight - 800, 30, 90);

        /* Separate this part right here  */
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("AHHH! SPIKES! ", getWidth() / 2 - 150, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Start a new game?", getWidth() / 2 - 150, getHeight() / 2 + 50);
            return;
        }
        if (gameWon) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("WOW! You Win!", getWidth() / 2 - 100, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Start a new game?", getWidth() / 2 - 150, getHeight() / 2 + 50);
            return;
        }
        if (ball != null) {
            ball.updatePosition();
            ball.bounce();
            ball.draw(g);
        }
        if (spikes != null) {
            for (Spike spike : spikes) {
                spike.draw(g);
                if (spike.intersects(ball)) {
                    gameOver = true;
                    repaint();
                    return;
                }
            }
        }
        if (platforms != null) {
            for (Platform platform : platforms) {
                platform.draw(g);
                if (ball.getY() + ball.getRadius() >= platform.getY() && 
                    ball.getY() < platform.getY() && ball.getVelocityY() > 0 &&
                    ball.getX() + ball.getRadius() > platform.getX() &&
                    ball.getX() - ball.getRadius() < platform.getX() + platform.getWidth()) {                   
                    ball.setVelocityY(-(int)(ball.getVelocityY() * 0.8));
                    ball.setPosition(ball.getX(), platform.getY() - ball.getRadius());
                    ball.onGround = true;
                }
                if (ball.getY() - ball.getRadius() <= platform.getY() + platform.getHeight() && 
                    ball.getY() > platform.getY() + platform.getHeight() && ball.getVelocityY() < 0 &&
                    ball.getX() + ball.getRadius() > platform.getX() &&
                    ball.getX() - ball.getRadius() < platform.getX() + platform.getWidth()) {
                    ball.setVelocityY(-(int)(ball.getVelocityY() * 0.8));
                    ball.setPosition(ball.getX(), platform.getY() + platform.getHeight() + ball.getRadius());
                }
            }
        }
        if (goal != null) {
            goal.draw(g);
            if (goal.isBallInGoal(ball)) {
                gameWon = true;
                repaint();
                return;
            }
        }
        repaint();
    }
    public static int getFloorHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    public static int getWallWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if ((gameOver || gameWon) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            resetGame();
        }
        if (!gameOver && !gameWon) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                ball.setVelocityX(-4);
                isLeftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                ball.setVelocityX(4);
                isRightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE && ball.isOnGround()) {
                ball.setVelocityY(-24);
                ball.onGround = false;
            }
        }
    }
    private void resetGame() {
        gameOver = false;
        gameWon = false;
        ball.setPosition(getWidth() / 2, getHeight() - 100);
        ball.setVelocityX(0);
        ball.setVelocityY(0);
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            isLeftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            isRightPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.initializeGameObjects();  
    }
}
