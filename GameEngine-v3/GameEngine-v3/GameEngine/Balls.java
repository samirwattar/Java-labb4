package GameEngine;
import java.util.ArrayList;
import java.awt.*;

public class Balls extends Sprite {
    private int dx = Constants.BALLSPEED;
    private int dy = -Constants.BALLSPEED;
    private boolean powerMode = false;

    public Balls(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setPowerMode(boolean enabled) {
        this.powerMode = enabled;
    }

    public boolean isPowerMode() {
        return powerMode;
    }

    public void reverseDY() {
        dy = -dy;
    }

    @Override
    public void update(Keyboard keyboard) {
        setX(getX() + dx);
        setY(getY() + dy);

        if (getX() <= 0) {
            dx = -dx;
            setX(1);
        }

        if (getX() + getWidth() >= Constants.WINDOW_WIDTH) {
            dx = -dx;
            setX(Constants.WINDOW_WIDTH - getWidth() - 1);
        }

        if (getY() <= 0) {
            dy = -dy;
            setY(1);
        }
    }

    public boolean isDead() {
        return getY() > Constants.WINDOW_HEIGHT;
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (powerMode) {
            graphics.setColor(Color.RED);
        } else {
            graphics.setColor(Color.CYAN);
        }
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}