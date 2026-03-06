package GameEngine;

import javax.swing.*;
import java.awt.*;

public class HighScorePanel extends JPanel {

    private JList<String> scoreList;
    private HighScoreList highScoreList;

    public HighScorePanel(HighScoreList highScoreList) {
        this.highScoreList = highScoreList;

        setPreferredSize(new Dimension(100, 600));
        setBackground(new Color(20, 20, 40));
        setLayout(new BorderLayout(5, 5));

        // titel för label
        JLabel title = new JLabel("HIGHSCORE", SwingConstants.CENTER);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        add(title, BorderLayout.NORTH);

        // JList som använder highscorelist model för att visa scores
        scoreList = new JList<>(highScoreList.getListModel());
        scoreList.setBackground(new Color(20, 20, 40));
        scoreList.setForeground(Color.WHITE);
        scoreList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scoreList.setSelectionBackground(new Color(20, 20, 40));

        JScrollPane scrollPane = new JScrollPane(scoreList);
        scrollPane.setBackground(new Color(20, 20, 40));
        scrollPane.getViewport().setBackground(new Color(20, 20, 40));
        add(scrollPane, BorderLayout.CENTER);
    }
}
