package GameEngine;

import javax.swing.*;
import java.awt.*;

/**
 * Right side panel that displays the 3 most recent game results.
 * Uses a JList backed by LatestRunsQueue's DefaultListModel.
 */
public class LatestRunsPanel extends JPanel {

    private JList<String> runsList;
    private LatestRunsQueue latestRunsQueue;

    public LatestRunsPanel(LatestRunsQueue latestRunsQueue) {
        this.latestRunsQueue = latestRunsQueue;

        setPreferredSize(new Dimension(160, 600));
        setBackground(new Color(20, 20, 40));
        setLayout(new BorderLayout(5, 5));

        // Title label
        JLabel title = new JLabel("LATEST RUNS", SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        add(title, BorderLayout.NORTH);

        // JList using the DefaultListModel from LatestRunsQueue
        runsList = new JList<>(latestRunsQueue.getListModel());
        runsList.setBackground(new Color(20, 20, 40));
        runsList.setForeground(Color.WHITE);
        runsList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        runsList.setSelectionBackground(new Color(20, 20, 40));
        runsList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

        JScrollPane scrollPane = new JScrollPane(runsList);
        scrollPane.setBackground(new Color(20, 20, 40));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(20, 20, 40));
        add(scrollPane, BorderLayout.CENTER);
    }
}
