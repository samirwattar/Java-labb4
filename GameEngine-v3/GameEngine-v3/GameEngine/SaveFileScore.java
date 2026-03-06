package GameEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


public class SaveFileScore {
    private static final String FILENAME = "ScoreLog.txt";


    public static void saveScore(int score) {
        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write("Score: " + score);
            writer.newLine();

            writer.close();
            fileWriter.close();

            System.out.println("Score saved: " + score);

        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }
}