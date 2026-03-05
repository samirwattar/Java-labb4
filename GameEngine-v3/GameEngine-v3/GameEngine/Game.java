package GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Font;

public class Game {

	ArrayList<Block> boxes = new ArrayList<>();
	ArrayList<Balls> bolls = new ArrayList<>();
	ArrayList<PowerUp> powerUps = new ArrayList<>();
	ArrayList<Missile> missiles = new ArrayList<>();
	Bat bat;
	GameStates gameStates;

	private boolean scoreSaved = false;
	private boolean gameOver = false;
	private String gameOverMessage = "";

	public Game(GameBoard board) {
		gameStates = new GameStates();
		boxes = Spawner.createMultipleRows(1, 8);
		bat = new Bat(350, 550, 100, 20, Color.WHITE);
		bolls.add(new Balls(400, 300, 20, 20));
	}

	public void update(Keyboard keyboard) {
		for (int i = 0; i < bolls.size(); i++) {
			Balls ball = bolls.get(i);
			ball.update(keyboard);

			CollisionHandler.handleBallBatCollision(ball, bat);
			CollisionHandler.handleBallBlockCollision(ball, boxes, gameStates, powerUps);

			if (ball.isDead()) {
				bolls.remove(i);
				i--;
				gameStates.loseLife();

				if (!gameStates.isGameOver()) {
					bolls.add(new Balls(400, 300, 20, 20));
				}
			}
		}

		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp powerUp = powerUps.get(i);
			powerUp.update(keyboard);

			if (powerUp.isOutOfBounds()) {
				powerUps.remove(i);
				i--;
			}
		}

		PowerUp collectedPowerUp = CollisionHandler.checkPowerUpCollection(bat, powerUps);
		if (collectedPowerUp != null) {
			PowerUpHandler.applyPowerUp(collectedPowerUp, bat, gameStates, bolls);
		}

		for (int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			missile.update(keyboard);

			boolean hitBlock = CollisionHandler.handleMissileBlockCollision(missile, boxes, gameStates);

			if (hitBlock || missile.isOutOfBounds()) {
				missiles.remove(i);
				i--;
			}
		}


		bat.update(keyboard);

		if (bat.canShootMissile(keyboard)) {
			Missile newMissile = bat.shootMissile();
			if (newMissile != null) {
				missiles.add(newMissile);
			}
		}


		if (bolls.isEmpty() && !gameOver){
			endGame("Game Over!");

		}

		if (boxes.isEmpty() && !gameOver){
			endGame("You Won!");
			bolls.clear();
		}
		if (gameOver && keyboard.isKeyDown(Key.Escape)){
			System.exit(0);

		}
	}

	private void endGame(String message) {
		gameOver = true;
		gameOverMessage = message;

		if (!scoreSaved) {
			SaveFileScore.saveScore(gameStates.getScore());
			scoreSaved = true;
		}
	}

	public void draw(Graphics2D graphics) {
		for (Block box : boxes) {
			box.draw(graphics);
		}
		for (PowerUp powerUp : powerUps) {
			powerUp.draw(graphics);
		}
		for (Balls boll : bolls) {
			boll.draw(graphics);
		}
		for (Missile missile : missiles) {
			missile.draw(graphics);
		}
		bat.draw(graphics);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Arial", Font.BOLD, 20));
		graphics.drawString("Lives: " + gameStates.getLives(), 10, 30);
		graphics.drawString("Score: " + gameStates.getScore(), 10, 60);
		graphics.drawString("Missiles: " + bat.getMissileCount(), 10, 90);

		if (gameOver) {
			graphics.setColor(new Color(0, 0, 0, 200));
			graphics.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("Arial", Font.BOLD, 60));

			int messageWidth = graphics.getFontMetrics().stringWidth(gameOverMessage);
			int messageX = (Constants.WINDOW_WIDTH - messageWidth) / 2;
			graphics.drawString(gameOverMessage, messageX, 250);

			graphics.setFont(new Font("Arial", Font.BOLD, 40));
			String scoreText = "Final Score: " + gameStates.getScore();
			String exitText = "Press Escape to exit";
			int scoreWidth = graphics.getFontMetrics().stringWidth(scoreText);
			int scoreX = (Constants.WINDOW_WIDTH - scoreWidth) / 2;
			graphics.drawString(scoreText, scoreX, 320);
			graphics.drawString(exitText, scoreX - 50 , 370);


		}
	}
}