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
    private LevelManager levelManager;
    private long levelStartTime;

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

        levelManager = new LevelManager();  // Initialize LevelManager
        loadCurrentLevel();                 // Load initial level
    }

    private void loadCurrentLevel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) screenSize.getWidth();
        int windowHeight = (int) screenSize.getHeight();

        ball = new Ball(windowWidth / 2, windowHeight - 100, 25);
        LevelManager.LevelData levelData = levelManager.loadLevel();

        if (levelData != null) {
            platforms = levelData.platforms;
            spikes = levelData.spikes;
            goal = levelData.goal;
        }
        levelStartTime = System.currentTimeMillis();  // Set start time for level
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("AHHH! SPIKES!", getWidth() / 2 - 150, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'Enter' to start a new game", getWidth() / 2 - 150, getHeight() / 2 + 50);
            return;
        }

        if (gameWon) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("WOW! You Win!", getWidth() / 2 - 100, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press 'Enter' to start a new game", getWidth() / 2 - 150, getHeight() / 2 + 50);
            return;
        }

        // Show level number for 2 seconds at the start of each level
        if (System.currentTimeMillis() - levelStartTime < 2000) {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Level " + levelManager.currentLevel, getWidth() / 2 - 50, 100);
        }

        ball.updatePosition();
        ball.bounce();
        ball.draw(g);

        for (Spike spike : spikes) {
            spike.draw(g);
            if (spike.intersects(ball)) {
                gameOver = true;
                repaint();
                return;
            }
        }

        for (Platform platform : platforms) {
            platform.draw(g);

            // Top collision
            if (ball.getY() + ball.getRadius() >= platform.getY() &&
                ball.getY() < platform.getY() && ball.getVelocityY() > 0 &&
                ball.getX() + ball.getRadius() > platform.getX() &&
                ball.getX() - ball.getRadius() < platform.getX() + platform.getWidth()) {

                ball.setVelocityY(-(int)(ball.getVelocityY() * 0.8));
                ball.setPosition(ball.getX(), platform.getY() - ball.getRadius());
                ball.onGround = true;
            }

            // Bottom collision
            if (ball.getY() - ball.getRadius() <= platform.getY() + platform.getHeight() &&
                ball.getY() > platform.getY() + platform.getHeight() && ball.getVelocityY() < 0 &&
                ball.getX() + ball.getRadius() > platform.getX() &&
                ball.getX() - ball.getRadius() < platform.getX() + platform.getWidth()) {

                ball.setVelocityY(-(int)(ball.getVelocityY() * 0.8));
                ball.setPosition(ball.getX(), platform.getY() + platform.getHeight() + ball.getRadius());
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
        loadCurrentLevel();  // Reload the current level
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
        new GameWindow();  // Constructor initializes and loads the first level
    }
}
