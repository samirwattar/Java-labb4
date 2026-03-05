package GameEngine;

import javax.swing.DefaultListModel;

public class LatestRunsQueue {
    private static final int MAX_RUNS = 3;
    private static final String FILENAME = "latestruns.txt";

    // Underlying model for JList display
    private DefaultListModel<String> listModel;

    // Simple circular buffer; index 0 = newest
    private int[] runs;
    private int size;

    public LatestRunsQueue() {
        listModel = new DefaultListModel<>();
        runs = new int[MAX_RUNS];
        size = 0;
        loadFromFile();
    }

    public void enqueue(int score) {
        // Shift existing entries towards the end (drop oldest if full)
        int end = (size < MAX_RUNS) ? size : MAX_RUNS - 1;
        for (int i = end - 1; i >= 0; i--) {
            runs[i + 1] = runs[i];
        }
        runs[0] = score;
        if (size < MAX_RUNS) size++;

        refreshModel();
        saveToFile();
    }


    private void refreshModel() {
        listModel.clear();
        for (int i = 0; i < size; i++) {
            listModel.addElement((i + 1) + ". " + runs[i]);
        }
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }


    private void saveToFile() {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(FILENAME))) {
            for (int i = 0; i < size; i++) {
                bw.write(String.valueOf(runs[i]));
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error saving latest runs: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        java.io.File file = new java.io.File(FILENAME);
        if (!file.exists()) return;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            size = 0;
            while ((line = br.readLine()) != null && size < MAX_RUNS) {
                runs[size++] = Integer.parseInt(line.trim());
            }
        } catch (java.io.IOException | NumberFormatException e) {
            System.err.println("Error loading latest runs: " + e.getMessage());
        }

        refreshModel();
    }
}
