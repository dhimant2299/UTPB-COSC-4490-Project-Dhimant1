import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Ball {
    private double x, y; // Position as double for smoother movement
    private int radius;
    private double velocityX, velocityY; // Velocity as double
    private final int gravity = 900; // Gravity factor
    private final double friction = 0.95; // Friction coefficient for smoother deceleration
    private final int stopThreshold = 5; // Threshold for minimal velocity to stop the ball
    public boolean onGround = true;
    private Image ballImage;

    public Ball(int startX, int startY, int radius) {
        this.x = startX;
        this.y = startY;
        this.radius = radius;
        this.velocityX = 0;
        this.velocityY = 0;
        loadImage();
    }

    private void loadImage() {
        try {
            ballImage = ImageIO.read(new File("src/ball_spritesheet.png")); // Adjust to actual path
        } catch (IOException e) {
            System.err.println("Failed to load ball image.");
            e.printStackTrace();
        }
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public int getRadius() { return radius; }
    public int getVelocityX() { return (int) velocityX; }
    public int getVelocityY() { return (int) velocityY; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void updatePosition(double deltaTime) {
        // Apply gravity
        velocityY += gravity * deltaTime;

        // Apply friction if no key is pressed
        if (!GameWindow.isLeftPressed && !GameWindow.isRightPressed) {
            velocityX *= friction;
            if (Math.abs(velocityX) < stopThreshold) {
                velocityX = 0; // Stop completely if velocity is very small
            }
        }

        // Update ball position
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }

    public void setVelocityX(int velocityX) { 
        this.velocityX = velocityX; 
    }

    public void setVelocityY(int velocityY) { 
        this.velocityY = velocityY; 
    }

    public void bounce() {
        double damping = 0.5; // Damping factor for bounce effect
        if (y + radius >= GameWindow.getFloorHeight()) {
            velocityY = -(int) (velocityY * damping);
            y = GameWindow.getFloorHeight() - radius;
            onGround = true;
        }
        if (y - radius <= 0) {
            velocityY = -(int) (velocityY * damping);
            y = radius;
        }
        if (x - radius <= 0) {
            velocityX = -(int) (velocityX * damping);
            x = radius;
        }
        if (x + radius >= GameWindow.getWallWidth()) {
            velocityX = -(int) (velocityX * damping);
            x = GameWindow.getWallWidth() - radius;
        }
    }

    public boolean intersectsPlatform(Platform platform) {
        // Check horizontal and vertical overlap
        return x + radius > platform.getX() &&
               x - radius < platform.getX() + platform.getWidth() &&
               y + radius > platform.getY() &&
               y - radius < platform.getY() + platform.getHeight();
    }

    public void handlePlatformCollision(Platform platform) {
        // Top collision
        if (y + radius >= platform.getY() &&
            y < platform.getY() &&
            velocityY > 0) {
            y = platform.getY() - radius; // Adjust position to sit on top of the platform
            velocityY = -velocityY * 0.8; // Bounce effect with damping
            onGround = true;
        }
        // Bottom collision
        else if (y - radius <= platform.getY() + platform.getHeight() &&
                 y > platform.getY() + platform.getHeight() &&
                 velocityY < 0) {
            y = platform.getY() + platform.getHeight() + radius; // Adjust position below the platform
            velocityY = -velocityY * 0.8;
        }
        // Side collisions can be added here if needed
    }

    public void draw(Graphics g) {
        if (ballImage != null) {
            g.drawImage(ballImage, (int) x - radius, (int) y - radius, 2 * radius, 2 * radius, null);
        } else {
            // Fallback: Draw a simple circle if the image is not loaded
            g.setColor(java.awt.Color.RED);
            g.fillOval((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
        }
    }
}
