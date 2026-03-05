package GameEngine;

import java.util.ArrayList;
import java.util.Random;

public class Spawner {
    private static Random rand = new Random();

    public static ArrayList<Block> createRow(int row, int blocksPerRow) {
        ArrayList<Block> blocks = new ArrayList<>();

        int Width = 80;
        int Height = 25;
        int spacing = 20;
        int startX = 50;
        int startY = 50 + (row * (Height + spacing));

        for (int col = 0; col < blocksPerRow; col++) {
            int x = startX + col * (Width + spacing);
            int y = startY;
            int hits = rand.nextInt(1, 4);
            int points = hits * 10;

            PowerUp.Type powerUpType = null;
            
            if (rand.nextInt(100) < 40) {
                powerUpType = PowerUp.Type.getRandomWeighted(rand);
            }

            blocks.add(new Block(x, y, Width, Height, hits, points, powerUpType));
        }

        return blocks;
    }


    public static ArrayList<Block> createMultipleRows(int numberOfRows, int bricksPerRow) {
        ArrayList<Block> allBricks = new ArrayList<>();

        for (int row = 0; row < numberOfRows; row++) {
            allBricks.addAll(createRow(row, bricksPerRow));
        }

        return allBricks;
    }
}