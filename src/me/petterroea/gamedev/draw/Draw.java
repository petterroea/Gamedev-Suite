package me.petterroea.gamedev.draw;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Draw extends JFrame {
	public static void main(String[] args)
	{
		Draw draw = new Draw();
	}
	public Draw()
	{
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		this.setTitle("Petterroea's gamedev suite - Draw");
		this.setSize(800, 600);
		initComponents();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void initComponents()
	{
		this.setLayout(new BorderLayout());
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem newdiag = new JMenuItem("New");
		JMenuItem saveas = new JMenuItem("Save As...");
		file.add(newdiag);
		file.addSeparator();
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		bar.add(file);
		this.add(new ColorSel(), BorderLayout.WEST);
		this.add(new JLabel("HAI"));
		this.setJMenuBar(bar);
	}
}
