import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Ball {
    private int x, y;  
    private int radius;
    private int velocityX, velocityY;
    private int gravity = 1;
    private double friction = 0.5;
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
            ballImage = ImageIO.read(getClass().getResource("./ball_spritesheet.png"));  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadius() { return radius; }
    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void updatePosition() {
        velocityY += gravity;

        if (!GameWindow.isLeftPressed && !GameWindow.isRightPressed) {
            velocityX *= friction;
            if (Math.abs(velocityX) < 0.5) {
                velocityX = 0;
            }
        }
        x += velocityX;
        y += velocityY;
    }

    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(int velocityY) { this.velocityY = velocityY; }

    public void bounce() {
        double damping = 0.8;
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

    public void draw(Graphics g) {
        if (ballImage != null) {
            g.drawImage(ballImage, x - radius, y - radius, 2 * radius, 2 * radius, null);
        }
    }
}
