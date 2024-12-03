
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

public class GameWindow extends JPanel implements KeyListener {
    private Ball ball;  
    private List<Spike> spikes;
    private List<Platform> platforms;
    private Goal goal;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private LevelManager levelManager;
    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    private long levelStartTime;
    private Timer gameTimer; // Timer for the game loop

    private Image backgroundImage;
    private boolean playedBounceSound = false;

    private long lastUpdateTime = System.nanoTime(); // To track delta time

    public void playBounceSound() {
        if (!playedBounceSound) {
            playSound("src/soundEffects/bouncy.wav");
            playedBounceSound = true;
        }
    }
    
    public void resetBounceSound() {
        playedBounceSound = false;
    }
    
    public void playWinSound() {
        playSound("src/soundEffects/levelSucceeded.wav");
    }

    public void playLoseSound() {
        playSound("src/soundEffects/lost.wav");
    }

    private void playSound(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public GameWindow() {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.add(this);
        frame.addKeyListener(this);

        levelManager = new LevelManager();
        initializeGameObjects();

        frame.setVisible(true);
        //backgroundImage = new ImageIcon("src/bgm.jpg").getImage();

        // Setup a game loop using a Timer (16ms per frame = ~60 FPS)
        gameTimer = new Timer(16, e -> gameLoop());
        gameTimer.start();
    }

    private void initializeGameObjects() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = (int) screenSize.getWidth();
        int windowHeight = (int) screenSize.getHeight();

        ball = new Ball(windowWidth / 2, windowHeight - 100, 30);

        LevelManager.LevelData levelData = levelManager.loadLevel();
        if (levelData != null) {
            platforms = levelData.platforms;
            spikes = levelData.spikes;
            goal = levelData.goal;
        }
        levelStartTime = System.currentTimeMillis();
    }

    private void gameLoop() {
        long currentTime = System.nanoTime();
        double deltaTime = (currentTime - lastUpdateTime) / 1e9; // Convert to seconds
        lastUpdateTime = currentTime;

        if (!gameOver && !gameWon) {
            // Update game objects with deltaTime
            ball.updatePosition(deltaTime);
            ball.bounce();

            // Spike Collision
            if (spikes != null) {
                for (Spike spike : spikes) {
                    if (spike.intersects(ball)) {
                        gameOver = true;
                        playLoseSound();
                        return;
                    }
                }
            }
            
            // Platform Collision
            if (platforms != null) {
                for (Platform platform : platforms) {
                    if (ball.intersectsPlatform(platform)) {
                        ball.handlePlatformCollision(platform);
                        playBounceSound();
                    }
                }
            }

            // Goal Collision
            if (goal != null && goal.isBallInGoal(ball)) {
                gameWon = true;
                playWinSound();
            }
        }

        repaint(); // Trigger rendering
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ball == null) return;

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        if (gameOver) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Verdana", Font.BOLD, 52));
            g.drawString("AHHH! SPIKES!!", getWidth() / 2 - 152, getHeight() / 2 + 2);
            g.setColor(Color.RED);
            g.drawString("AHHH! SPIKES!!", getWidth() / 2 - 150, getHeight() / 2);
            g.setFont(new Font("Verdana", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawString("Press ENTER to play again", getWidth() / 2 - 100, getHeight() / 2 + 80);
            return;
        }
        
        if (gameWon) {
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Verdana", Font.BOLD, 56));
            g.drawString("Level Complete!", getWidth() / 2 - 142, getHeight() / 2 + 2);
            g.setColor(new Color(0, 180, 0)); // Vibrant green
            g.drawString("Level Complete!", getWidth() / 2 - 140, getHeight() / 2);
            return;
        }

        ball.draw(g);

        if (spikes != null) {
            for (Spike spike : spikes) {
                spike.draw(g);
            }
        }

        if (platforms != null) {
            for (Platform platform : platforms) {
                platform.draw(g);
            }
        }

        if (goal != null) {
            goal.draw(g);
        }

        if (System.currentTimeMillis() - levelStartTime < 2000) {
            g.setColor(Color.black);
            g.setFont(new Font("Verdana", Font.BOLD, 36));
            g.drawString("Level " + levelManager.currentLevel, getWidth() / 2 - 50, 100);
        }
    }

    private void resetGame() {
        if (gameWon) {
            levelManager.advanceLevel();
        }
        gameOver = false;
        gameWon = false;

        initializeGameObjects();
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
                ball.setVelocityX(-200);
                isLeftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                ball.setVelocityX(200);
                isRightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE && ball.isOnGround()) {
                ball.setVelocityY(-700);
                ball.onGround = false;
            }
        }
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
        new GameWindow();
    }
}
