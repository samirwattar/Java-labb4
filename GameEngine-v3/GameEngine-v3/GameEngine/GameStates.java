package GameEngine;

public class GameStates{
    private int score;
    private int lives;

    public GameStates() {
        this.score = 0;
        this.lives = 3;
    }

    public void addScore(int points) {
        score += points;
    }

    public void loseLife() {
        lives--;
    }

    public void addLife() {
        lives++;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return lives <= 0;
    }
}