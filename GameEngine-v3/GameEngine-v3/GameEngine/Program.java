package GameEngine;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Program extends JFrame {
	GameBoard board;
	ScorePanel scorePanel;

	public Program() {
		board = new GameBoard();
		scorePanel = new ScorePanel();

		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(scorePanel, BorderLayout.EAST);

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