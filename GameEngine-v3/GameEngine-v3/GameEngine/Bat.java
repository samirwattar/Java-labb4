package GameEngine;
import java.util.ArrayList;
import java.awt.*;

public class Bat extends Sprite{
    private Color color;
    private int speed = 10;
    private int missileCount = 111;

    public Bat(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public void update(Keyboard keyboard) {
        if(keyboard.isKeyDown(Key.Left)){
            if (getX() > 0){
                setX(getX() - speed);
            }
        }
        if(keyboard.isKeyDown(Key.Right)){
            if (getX() + getWidth() < Constants.WINDOW_WIDTH){
                setX(getX() + speed);
            }
        }
    }

    public boolean canShootMissile(Keyboard keyboard) {
        if (keyboard.isKeyDown(Key.Space) && missileCount > 0) {
            return true;
        }

        return false;
    }

    public Missile shootMissile() {
        if (missileCount > 0) {
            missileCount--;
            int missileX = getX() + getWidth() / 2 - 2;
            int missileY = getY() - 20;
            return new Missile(missileX, missileY, 5, 20, Color.CYAN);
        }
        return null;
    }

    public void addMissile() {
        missileCount++;
    }

    public int getMissileCount() {
        return missileCount;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("arial", Font.BOLD, 12));
        graphics.drawString("SW", getX() + getWidth()/2 - 10, getY() + getHeight()/2 + 5);
    }
}