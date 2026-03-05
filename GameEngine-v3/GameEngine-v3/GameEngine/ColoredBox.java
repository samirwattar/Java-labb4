package GameEngine;

import java.awt.*;
import java.awt.Color;

public abstract class ColoredBox extends Sprite{
    private Color color;

    public ColoredBox(int x, int y, int w, int h, Color color){
        super(x, y, w, h);
        this.color = color;


    }
    public void setColor(Color color) {
        this.color = color;
    }
    @Override
    public void update(Keyboard keyboard) {

    }
    @Override
    public void draw(Graphics2D graphics) {
    graphics.setColor(color);
    graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}
