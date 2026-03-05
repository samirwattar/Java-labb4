package GameEngine;

import java.awt.Color;
import java.util.ArrayList;


public class PowerUpHandler {


    public static void applyPowerUp(PowerUp powerUp, Bat bat, GameStates gameState, ArrayList<Balls> balls) {
        switch(powerUp.getType()) {
            case EXTRA_LIFE:
                gameState.addLife();
                break;

            case DOUBLE_BALL:
                if (!balls.isEmpty()) {
                    Balls original = balls.get(0);
                    balls.add(new Balls(original.getX(), original.getY(), 20, 20));
                }
                break;

            case POWER_BALL:
                for (Balls ball : balls) {
                    ball.setPowerMode(true);
                }
                break;

            case SPEED:
                bat.setSpeed(20);
                bat.setColor(Color.decode("#f8fa9d"));
                break;

            case BIGGER_BAT:
                bat.setWidth(200);
                break;

            case MISSILE:
                bat.addMissile();
                break;
        }
    }
}