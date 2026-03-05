package GameEngine;

import java.awt.Color;
import java.awt.*;

public class Missile extends Sprite{
    private int speed = 10;
    private Color color;

    public Missile(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void update(Keyboard keyboard) {
        setY(getY() - speed);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isOutOfBounds() {
        return getY() + getHeight() < 0;
    }
}