package GameEngine;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScorePanel extends JPanel {
    private JTextArea scoreArea;
    private JList list;

    public ScorePanel() {
        setPreferredSize(new Dimension(150, 600));
        setBackground(Color.DARK_GRAY);

        scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setBackground(Color.DARK_GRAY);
        scoreArea.setForeground(Color.WHITE);

        add(scoreArea);

        loadScores();
    }

    public void loadScores() {
        String text = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader("ScoreLog.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }

            reader.close();

        } catch (IOException e) {
            text = "No scores yet";
        }

        scoreArea.setText(text);
    }
}