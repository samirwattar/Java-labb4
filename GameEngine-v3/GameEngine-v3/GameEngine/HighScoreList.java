package GameEngine;

import javax.swing.DefaultListModel;

public class HighScoreList {
    private static int MAX_ENTRIES = 10;
    private static String FILENAME = "highscores.txt";

    // The underlying model used by JList for display
    private DefaultListModel<String> listModel;

    // Parallel arrays to store scores and initials
    private int[] scores;
    private String[] initials;
    private int size;

    public HighScoreList() {
        listModel = new DefaultListModel<>();
        scores = new int[MAX_ENTRIES];
        initials = new String[MAX_ENTRIES];
        size = 0;
        loadFromFile();
    }

    public boolean qualifies(int score) {
        if (size < MAX_ENTRIES) return true;
        return score > scores[size - 1]; // scores[size-1] is the lowest (list is sorted desc)
    }

    public void add(int score, String initial) {
        if (!qualifies(score)) return;

        // Clamp initials to 3 characters
        if (initial.length() > 3) {
            initial = initial.substring(0, 3).toUpperCase();
        } else {
            initial = initial.toUpperCase();
        }

        // Find insertion index (sorted descending)
        int insertAt = size;
        for (int i = 0; i < size; i++) {
            if (score > scores[i]) {
                insertAt = i;
                break;
            }
        }

        // If list is full, we'll overwrite the last slot after shifting
        int end = (size < MAX_ENTRIES) ? size : MAX_ENTRIES - 1;

        // Shift elements down to make room
        for (int i = end - 1; i >= insertAt; i--) {
            scores[i + 1] = scores[i];
            initials[i + 1] = initials[i];
        }

        scores[insertAt] = score;
        initials[insertAt] = initial;

        if (size < MAX_ENTRIES) size++;

        refreshModel();
        saveToFile();
    }

    /**
     * Rebuilds the DefaultListModel from the current scores/initials arrays.
     * Format: "1. INI 12345"
     */
    private void refreshModel() {
        listModel.clear();
        for (int i = 0; i < size; i++) {
            listModel.addElement((i + 1) + ". " + initials[i] + " " + scores[i]);
        }
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    // -------------------------------------------------------------------------
    // File persistence
    // -------------------------------------------------------------------------

    /**
     * Saves the highscore list to file.
     * Format per line: INITIAL SCORE
     */
    private void saveToFile() {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(FILENAME))) {
            for (int i = 0; i < size; i++) {
                bw.write(initials[i] + " " + scores[i]);
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error saving highscores: " + e.getMessage());
        }
    }

    /**
     * Loads the highscore list from file.
     * Silently ignores missing or malformed files.
     */
    private void loadFromFile() {
        java.io.File file = new java.io.File(FILENAME);
        if (!file.exists()) return;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            size = 0;
            while ((line = br.readLine()) != null && size < MAX_ENTRIES) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 2) {
                    initials[size] = parts[0];
                    scores[size] = Integer.parseInt(parts[1]);
                    size++;
                }
            }
        } catch (java.io.IOException | NumberFormatException e) {
            System.err.println("Error loading highscores: " + e.getMessage());
        }

        refreshModel();
    }
}
