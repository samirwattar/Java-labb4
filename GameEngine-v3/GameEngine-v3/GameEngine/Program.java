package GameEngine;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Program extends JFrame {
	GameBoard board;
	HighScorePanel highScorePanel;
	LatestRunsPanel latestRunsPanel;

	public Program() {
		// Create the shared ADT instances
		HighScoreList highScoreList = new HighScoreList();
		LatestRunsQueue latestRunsQueue = new LatestRunsQueue();

		// Create panels backed by the ADTs
		highScorePanel = new HighScorePanel(highScoreList);
		latestRunsPanel = new LatestRunsPanel(latestRunsQueue);


		// GameBoard receives the ADTs so Game can update them
		board = new GameBoard(highScoreList, latestRunsQueue);

		setLayout(new BorderLayout());
		add(highScorePanel, BorderLayout.WEST);   // Highscore till vänster
		add(board, BorderLayout.CENTER);
		add(latestRunsPanel, BorderLayout.EAST);  // Latest runs till höger

		setResizable(false);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		setFocusable(true);
		requestFocus();

		board.start();
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public static void main(String[] args) {
		Program program = new Program();
	}
}
