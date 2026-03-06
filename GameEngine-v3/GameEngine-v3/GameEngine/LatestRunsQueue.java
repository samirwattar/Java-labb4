package GameEngine;

import javax.swing.DefaultListModel;
import java.io.*;

public class LatestRunsQueue {
    private static int MAX_RUNS = 3;
    private static String FILENAME = "latestruns.txt";

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
        // Flytta befintliga score mot slutet ta bort de äldsta om det är fullt
        int end;
        if (size < MAX_RUNS){
            end = size;
        }
        else {
            end = MAX_RUNS - 1;
        }
        for (int i = end - 1; i >= 0; i--){
            runs[i + 1] = runs[0];
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (int i = 0; i < size; i++) {
                bw.write(String.valueOf(runs[i]));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving latest runs: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILENAME);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            size = 0;
            while ((line = br.readLine()) != null && size < MAX_RUNS) {
                runs[size++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading latest runs: " + e.getMessage());
        }

        refreshModel();
    }
}
