package me.petterroea.gamedev.draw;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class ColorSel extends JPanel {
	public ColorSel()
	{
		Dimension min = new Dimension(100, 100);
		this.setMinimumSize(min);
		this.setPreferredSize(min);
		JScrollBar hbar = new JScrollBar(JScrollBar.VERTICAL, 30, 20, 0, 300);
		this.setLayout(new BorderLayout());
		this.add(hbar, BorderLayout.WEST);
	}
}
