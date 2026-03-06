package GameEngine;

import javax.swing.*;
import java.awt.*;


public class LatestRunsPanel extends JPanel {

    private JList<String> runsList;
    private LatestRunsQueue latestRunsQueue;

    public LatestRunsPanel(LatestRunsQueue latestRunsQueue) {
        this.latestRunsQueue = latestRunsQueue;

        setPreferredSize(new Dimension(100, 600));
        setBackground(new Color(20, 20, 40));
        setLayout(new BorderLayout(5, 5));

        // titel på panelen
        JLabel title = new JLabel("LATEST RUNS", SwingConstants.CENTER);
        title.setForeground(Color.CYAN);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        add(title, BorderLayout.NORTH);

        //
        runsList = new JList<>(latestRunsQueue.getListModel());
        runsList.setBackground(new Color(20, 20, 40));
        runsList.setForeground(Color.WHITE);
        runsList.setFont(new Font("Arial", Font.PLAIN, 12));
        runsList.setSelectionBackground(new Color(20, 20, 40));

        JScrollPane scrollPane = new JScrollPane(runsList);
        scrollPane.setBackground(new Color(20, 20, 40));
        scrollPane.getViewport().setBackground(new Color(20, 20, 40));
        add(scrollPane, BorderLayout.CENTER);
    }
}
