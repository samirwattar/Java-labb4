package GameEngine;

import java.util.ArrayList;

public class CollisionHandler {

    public static boolean checkOverlap(Sprite sprite1, Sprite sprite2) {
        boolean verticalOverlap = (sprite1.getY() + sprite1.getHeight() >= sprite2.getY()) &&
                (sprite1.getY() <= sprite2.getY() + sprite2.getHeight());

        boolean horizontalOverlap = (sprite1.getX() + sprite1.getWidth() >= sprite2.getX()) &&
                (sprite1.getX() <= sprite2.getX() + sprite2.getWidth());

        return verticalOverlap && horizontalOverlap;
    }


    public static void handleBallBatCollision(Balls ball, Bat bat) {
        if (checkOverlap(ball, bat)) {
            ball.reverseDY();
        }
    }

    public static void handleBallBlockCollision(Balls ball, ArrayList<Block> blocks,
                                                GameStates gameState, ArrayList<PowerUp> powerUps) {
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);

            if (checkOverlap(ball, block)) {
                ball.reverseDY();

                if (ball.isPowerMode()) {
                    block.hit();
                    block.hit();
                } else {
                    block.hit();
                }

                gameState.addScore(block.getPoints());

                if (block.isDestroyed()) {
                    if (block.hasPowerUp()) {
                        PowerUp powerUp = block.dropPowerUp();
                        if (powerUp != null) {
                            powerUps.add(powerUp);
                        }
                    }
                    blocks.remove(i);
                    i--;
                }

                break;
            }
        }
    }

    public static boolean handleMissileBlockCollision(Missile missile, ArrayList<Block> blocks,
                                                      GameStates gameState) {
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);

            if (checkOverlap(missile, block)) {
                block.hit();
                block.hit();
                block.hit();

                gameState.addScore(block.getPoints());

                if (block.isDestroyed()) {
                    blocks.remove(i);
                }

                return true;
            }
        }
        return false;
    }

    public static PowerUp checkPowerUpCollection(Bat bat, ArrayList<PowerUp> powerUps) {
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);

            if (checkOverlap(bat, powerUp)) {
                powerUps.remove(i);
                return powerUp;
            }
        }
        return null;
    }
}