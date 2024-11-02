import java.awt.Graphics;
import java.awt.Color;

public class Spike {
    public int x, y; 
    public int size;  
    public Spike(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
    public void draw(Graphics g) {
        int[] xPoints = { x, x + size / 2, x + size };  
        int[] yPoints = { y, y - size, y };              
        g.setColor(Color.RED);
        g.fillPolygon(xPoints, yPoints, 3);
    }
    public boolean intersects(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballRadius = ball.getRadius();
        return ballX + ballRadius > x && ballX - ballRadius < x + size &&
               ballY + ballRadius > y - size && ballY - ballRadius < y;
    }
}
