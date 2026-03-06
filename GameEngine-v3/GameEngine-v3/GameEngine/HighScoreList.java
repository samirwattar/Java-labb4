package GameEngine;

import javax.swing.DefaultListModel;

public class HighScoreList {
    private static int MAX_SIZE = 10;
    private static String FILENAME = "highscores.txt";

    private DefaultListModel<String> listModel;

    private int[] scores;
    private String[] names;
    private int size;

    public HighScoreList() {
        listModel = new DefaultListModel<>();
        scores = new int[MAX_SIZE];
        names = new String[MAX_SIZE];
        size = 0;
        loadFromFile();
    }

    public boolean qualifies(int score) {
        if (size < MAX_SIZE) return true;
        return score > scores[size - 1];
    }

    public void add(int score, String name) {
        if (!qualifies(score)) return;
        // om namnet är större än 3 bokstäver så trima den till bara 3 bokstävder o ändra till uppercase
        if (name.length() > 3) {
            name = name.substring(0, 3).toUpperCase();
        } else {
            name = name.toUpperCase();
        }

        int insertAt = size;
        for (int i = 0; i < size; i++) {
            if (score > scores[i]) {
                insertAt = i;
                break;
            }
        }

        // If list is full, we'll overwrite the last slot after shifting
        int end = (size < MAX_SIZE) ? size : MAX_SIZE - 1;

        // Shift elements down to make room
        for (int i = end - 1; i >= insertAt; i--) {
            scores[i + 1] = scores[i];
            names[i + 1] = names[i];
        }

        scores[insertAt] = score;
        names[insertAt] = name;

        if (size < MAX_SIZE) size++;

        refreshModel();
        saveToFile();
    }


    private void refreshModel() {
        listModel.clear();
        for (int i = 0; i < size; i++) {
            listModel.addElement((i + 1) + ". " + names[i] + " " + scores[i]);
        }
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }


    private void saveToFile() {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(FILENAME))) {
            for (int i = 0; i < size; i++) {
                bw.write(names[i] + " " + scores[i]);
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error saving highscores: " + e.getMessage());
        }
    }


    private void loadFromFile() {
        java.io.File file = new java.io.File(FILENAME);
        if (!file.exists()) return;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            size = 0;
            while ((line = br.readLine()) != null && size < MAX_SIZE) {
                String[] parts = line.trim().split(" ");
                if (parts.length == 2) {
                    names[size] = parts[0];
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
