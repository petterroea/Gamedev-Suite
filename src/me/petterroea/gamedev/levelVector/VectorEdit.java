package me.petterroea.gamedev.levelVector;

import javax.swing.JFrame;

public class VectorEdit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Level");
		frame.setSize(800, 600);
		Editor editor = new Editor();
		frame.add(editor);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
