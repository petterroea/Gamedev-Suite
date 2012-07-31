package me.petterroea.gamedev.levelVector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Editor extends JPanel implements MouseMotionListener, MouseListener{
	int height = 400;
	float xoff = 0.0f;
	LinkedList<Layer> layers;
	int panelHeight = 200;
	Rectangle settingsArea;
	Rectangle workArea;
	BufferedImage add;
	BufferedImage edit;
	int mode = 1;
	int hover = 0;
	LinkedList<Vector> placedVectors;
	public Editor()
	{
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		try {
			add = ImageIO.read(Editor.class.getResourceAsStream("cursorAdd.png"));
			edit = ImageIO.read(Editor.class.getResourceAsStream("cursorEdit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		placedVectors = new LinkedList<Vector>();
		settingsArea = new Rectangle(0, this.getHeight()-panelHeight, this.getWidth(), panelHeight);
		workArea = new Rectangle(0, 21+30, this.getWidth(), ((this.getHeight()-21-30-panelHeight))+21+30);
		layers = new LinkedList<Layer>();
	}
	@Override
	public void paint(Graphics g)
	{
		settingsArea = new Rectangle(0, this.getHeight()-panelHeight, this.getWidth(), panelHeight);
		height = ((this.getHeight()-21-30-panelHeight))+21+30;
		workArea = new Rectangle(0, 21+30, this.getWidth(), height);
		g.setColor(new Color(40, 40, 40));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(45, 45, 45));
		g.fillRect(0, 0, this.getWidth(), 20);
		if(placedVectors.size()==0)
			g.setColor(new Color(43, 43, 43));
		if(placedVectors.size()!=0)
			g.setColor(new Color(70, 43, 43));
		g.fillRect(0, 20, this.getWidth(), 30);
		g.setColor(new Color(55, 55, 55));
		g.drawLine(0, 20, this.getWidth(), 20);
		g.drawLine(0, 20+30, this.getWidth(), 20+30);
		g.setColor(new Color(60, 60, 60));
		if(mode==1)
		{
			g.fillRect(0, 20, 30, 30);
		}
		else if(mode==2)
		{
			g.fillRect(30, 20, 30, 30);
		}
		g.setColor(new Color(50, 50, 50));
		if(hover==1)
		{
			g.fillRect(0, 21, 30, 29);
		}
		else if(hover==2)
		{
			g.fillRect(30, 21, 30, 29);
		}
		
		g.drawImage(add, 3, 20+3, null);
		g.drawImage(edit, 3+20+3+3, 20+3, null);
		
		renderLevel(g);
		g.setColor(new Color(128, 30, 30));
		g.drawLine(0, 20+(height/2), this.getWidth(), 20+(height/2));
		g.setColor(new Color(45, 45, 45));
		g.fillRect(settingsArea.x, settingsArea.y, settingsArea.width, settingsArea.height);
	}

	private void renderLevel(Graphics g) {
		g.setColor(new Color(40, 128, 40));
		for(int i = 0; i < placedVectors.size(); i++)
		{
			float x = (placedVectors.get(i).x-xoff)*((float)this.getWidth()/2)+((float)this.getWidth()/2);
			float y = ((placedVectors.get(i).y*(float)((float)height/2)))+((float)height/2)+21+30;
			g.drawLine((int)x, (int)y, (int)x, (int)y);
		}
		for(int i = 1; i < placedVectors.size(); i++)
		{
			float x = (placedVectors.get(i).x-xoff)*((float)this.getWidth()/2)+((float)this.getWidth()/2);
			float y = ((placedVectors.get(i).y*(float)((float)height/2)))+((float)height/2)+21+30;
			float xb = (placedVectors.get(i-1).x-xoff)*((float)this.getWidth()/2)+((float)this.getWidth()/2);
			float yb = ((placedVectors.get(i-1).y*(float)((float)height/2)))+((float)height/2)+21+30;
			g.drawLine((int)x, (int)y, (int)xb, (int)yb);
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Rectangle add = new Rectangle(0, 20, 30, 30);
		Rectangle edit = new Rectangle(30, 20, 30, 30);
		boolean repaint = false;
		if(add.contains(arg0.getX(), arg0.getY()))
		{
			if(mode!=1)
				repaint=true;
			mode = 1;
		}
		else if(edit.contains(arg0.getX(), arg0.getY()))
		{
			if(mode!=2)
				repaint=true;
			mode = 2;
		}
		else if(workArea.contains(arg0.getX(), arg0.getY()))
		{
			float locx = xoff+((float)arg0.getX()/((float)this.getWidth()/2))-1;
			float locy = (((float)arg0.getY()-21-30)/((float)height/2))-1;
			System.out.println("Added vector at " + locx + ", " + locy);
			placedVectors.add(new Vector(locx, locy));
			repaint=true;
		}
		if(repaint)
		{
			this.repaint();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		Rectangle add = new Rectangle(0, 20, 30, 30);
		Rectangle edit = new Rectangle(30, 20, 30, 30);
		boolean repaint = false;
		if(add.contains(arg0.getX(), arg0.getY()))
		{
			if(hover!=1)
				repaint=true;
			hover = 1;
		}
		else if(edit.contains(arg0.getX(), arg0.getY()))
		{
			if(hover!=2)
				repaint=true;
			hover = 2;
		}
		else
		{
			if(hover!=0)
				repaint=true;
			hover = 0;
		}
		if(repaint)
		{
			this.repaint();
		}
		
	}

}
