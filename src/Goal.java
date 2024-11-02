import java.awt.Graphics;
import java.awt.Color;

public class Goal {
    private int x, y, width, height;
    public Goal(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x, y, width, height);
    }
    public boolean isBallInGoal(Ball ball) {
        int ballCenterX = ball.getX();
        int ballCenterY = ball.getY();
        return (ballCenterX > x && ballCenterX < x + width) &&
               (ballCenterY > y && ballCenterY < y + height);
    }
}
