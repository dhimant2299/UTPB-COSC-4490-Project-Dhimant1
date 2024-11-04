import java.awt.Graphics;
import java.awt.Color;
//import java.awt.Rectangle;

public class Platform {
    private int x, y, width, height;
    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }
    public boolean isBallOnPlatform(Ball ball) {
        int ballBottom = ball.getY() + ball.getRadius();
        int platformTop = this.y;
        return ballBottom >= platformTop && ballBottom <= platformTop + ball.getRadius() &&
               ball.getX() >= this.x && ball.getX() <= this.x + this.width;
    }
}
