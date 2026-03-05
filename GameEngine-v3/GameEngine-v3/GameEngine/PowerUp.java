package GameEngine;

import java.awt.*;
import java.util.Random;

public class PowerUp extends Sprite{
    public enum Type{
        EXTRA_LIFE(30),
        DOUBLE_BALL(30),
        POWER_BALL(10),
        SPEED(50),
        BIGGER_BAT(70),
        MISSILE(100);

        private final int weight;

        Type(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public static Type getRandomWeighted(Random rand) {
            int totalWeight = 0;
            for (Type type : values()) {
                totalWeight += type.weight;
            }

            int random = rand.nextInt(totalWeight);
            int sum = 0;

            for (Type type : values()) {
                sum += type.weight;
                if (random < sum) {
                    return type;
                }
            }

            return EXTRA_LIFE;
        }
    }

    private Type type;
    private Color color;
    private int speed = 2;

    public PowerUp(int x, int y, Type type){
        super(x, y, 20, 20);
        this.type = type;

        switch (type){
            case EXTRA_LIFE -> color = Color.pink;
            case DOUBLE_BALL -> color = Color.cyan;
            case POWER_BALL -> color = Color.red;
            case SPEED -> color = Color.YELLOW;
            case BIGGER_BAT -> color = Color.decode("#9118ed");
            case MISSILE -> color = Color.decode("#edaf02");
        }
    }

    @Override
    public void update(Keyboard keyboard) {
        setY(getY() + speed);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());

        graphics.setColor(Color.black);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        switch(type) {
            case EXTRA_LIFE:
                graphics.drawString("+", getX() + 6, getY() + 15);
                break;
            case DOUBLE_BALL:
                graphics.drawString("x2", getX() + 3, getY() + 15);
                break;
            case POWER_BALL:
                graphics.drawString("!", getX() + 7, getY() + 15);
                break;
            case SPEED:
                graphics.drawString("S", getX() + 7, getY() + 15);
                break;
            case BIGGER_BAT:
                graphics.drawString("_", getX() + 7, getY() + 15);
                break;
            case MISSILE:
                graphics.drawString("|", getX() + 7, getY() + 15);
                break;
        }
    }

    public Type getType() {
        return type;
    }

    public boolean isOutOfBounds() {
        return getY() > Constants.WINDOW_HEIGHT;
    }
}