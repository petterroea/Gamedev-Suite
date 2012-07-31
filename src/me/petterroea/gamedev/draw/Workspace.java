package me.petterroea.gamedev.draw;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.petterroea.gamedev.shared.MenuDropdown;
import me.petterroea.gamedev.shared.MenuEntry;

public class Workspace extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	BufferedImage image;
	float scale = 1.0f;
	static boolean inFileBrowser = false;
	FileBrowser browser;
	Workspace me = this;
	CodeWindow code;
	MenuDropdown file = new MenuDropdown("File", 0, 0, new MenuEntry[]{new MenuEntry("Open", new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			inFileBrowser=true;
			me.repaint();
			
		}}), new MenuEntry("Yey!", null)});
	JFrame parent;
	public Workspace(JFrame parent)
	{
		code = new CodeWindow();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		parent.addKeyListener(this);
		this.parent = parent;
		code.setLocation(this.parent.getLocation().x+this.parent.getWidth(), this.parent.getLocation().y);
		browser = new FileBrowser(this);
	}
	public void imageLoaded()
	{
		int xOverFlow = image.getWidth()-(this.getWidth()+200);
		int yOverFlow = image.getHeight()-(this.getHeight()+200);
		if(xOverFlow>0||yOverFlow>0)
		{
			if(xOverFlow>yOverFlow)
			{
				scale = (float)(this.getWidth()-200)/(float)image.getWidth();
			}
			else
			{
				scale = (float)(this.getHeight()-200)/(float)image.getHeight();
			}
			
		}
	}
	@Override
	public void paint(Graphics g)
	{
		if(!inFileBrowser)
		{
			g.setColor(new Color(40, 40, 40));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			renderImage(g);
			g.setColor(Color.gray);
			g.drawString("Scale: " + scale, 0, this.getHeight()-1);
			g.setColor(new Color(45, 45, 45));
			g.fillRect(0, 0, this.getWidth(), 20);
			g.setColor(new Color(55, 55, 55));
			g.drawLine(0, 20, this.getWidth(), 20);
			file.draw(g);
		}
		else
		{
			browser.draw(g, this);
		}
	}

	private void renderImage(Graphics g) {
		if(image==null)
		{
			
		}
		else
		{
			int wScaled=(int) (image.getWidth()*scale);
			int hScaled=(int) (image.getHeight()*scale);
			if(wScaled>this.getWidth()||hScaled>this.getHeight())
			{
				for(int x = 0; x < this.getWidth(); x++)
				{
					for(int y = 0; y < this.getHeight(); y++)
					{
						int drawX = (this.getWidth()/2)-(wScaled/2);
						int drawY = (this.getHeight()/2)-(hScaled/2);
						int xOnImage = x-drawX;
								
					}
				}
			}
			else
			{
				int drawX = (this.getWidth()/2)-(wScaled/2);
				int drawY = (this.getHeight()/2)-(hScaled/2);
				for(int x = 0; x < wScaled; x++)
				{
					for(int y=0;y<hScaled;y++)
					{
						float xFactor = x/wScaled;
						float yFactor = y/hScaled;
						float imagex=(float)((float)x/(float)wScaled)*(float)image.getWidth();
						float imagey=(float)((float)y/(float)hScaled)*(float)image.getHeight();
						g.setColor(new Color(image.getRGB((int)imagex, (int)imagey)));
						g.drawLine(x+drawX, y+drawY, x+drawX, y+drawY);
					}
				}
			}
		}
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(inFileBrowser)
		{
			browser.moved(e.getX(), e.getY());
		}
		else
		{
			
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(inFileBrowser)
		{
			browser.moved(e.getX(), e.getY());
		}
		else
		{
			if(file.mouseMove(e.getX(), e.getY()))
			{
				this.repaint();
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(inFileBrowser)
		{
			browser.click(e.getX(), e.getY());
		}
		else
		{
			if(file.click(e.getX(), e.getY()))
			{
				this.repaint();
			}
		}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_MINUS)
		{
			scale = scale*0.9f;
			this.repaint();
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_PLUS)
		{
			scale = scale*1.1f;
			this.repaint();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
