package GameEngine;
import java.awt.*;

public class Block extends ColoredBox{

    private PowerUp.Type powerUpType;
    private int hits;
    private int points;
    public Block(int x, int y, int w, int h, int hits, int points, PowerUp.Type powerUpType) {
        super(x, y, w, h, getColorFromHits(hits));
        this.hits = hits;
        this.points = points;
        this.powerUpType = powerUpType;
    }
    public int getPoints(){
        return points;
    }

    @Override
    public void update(Keyboard keyboard){
    }

    private static Color getColorFromHits(int hits) {
        if (hits == 3) return Color.GREEN;
        if (hits == 2) return Color.ORANGE;
        if (hits == 1) return Color.RED;
        return Color.GRAY;
    }
    public boolean hasPowerUp() {
        return powerUpType != null;
    }
    public PowerUp dropPowerUp() {
        if (hasPowerUp()) {
            return new PowerUp(getX() + getWidth()/2 - 10, getY(), powerUpType);
        }
        return null;
    }
    public void hit(){
        hits--;
        if (hits == 2){
            setColor(Color.orange);
        }
        if (hits == 1){
            setColor(Color.red);
        }
    }
    public boolean isDestroyed(){
        return hits <= 0;
    }
    public int getHits() {
        return hits;
    }
}
