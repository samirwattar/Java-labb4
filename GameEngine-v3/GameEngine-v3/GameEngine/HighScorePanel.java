package GameEngine;

import javax.swing.*;
import java.awt.*;

/**
 * Left side panel that displays the top-10 highscore list.
 * Uses a JList backed by HighScoreList's DefaultListModel.
 */
public class HighScorePanel extends JPanel {

    private JList<String> scoreList;
    private HighScoreList highScoreList;

    public HighScorePanel(HighScoreList highScoreList) {
        this.highScoreList = highScoreList;

        setPreferredSize(new Dimension(160, 600));
        setBackground(new Color(20, 20, 40));
        setLayout(new BorderLayout(5, 5));

        // Title label
        JLabel title = new JLabel("HIGHSCORE", SwingConstants.CENTER);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        add(title, BorderLayout.NORTH);

        // JList using the DefaultListModel from HighScoreList
        scoreList = new JList<>(highScoreList.getListModel());
        scoreList.setBackground(new Color(20, 20, 40));
        scoreList.setForeground(Color.WHITE);
        scoreList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scoreList.setSelectionBackground(new Color(20, 20, 40));
        scoreList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

        JScrollPane scrollPane = new JScrollPane(scoreList);
        scrollPane.setBackground(new Color(20, 20, 40));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(20, 20, 40));
        add(scrollPane, BorderLayout.CENTER);
    }
}
