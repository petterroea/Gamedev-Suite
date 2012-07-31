package me.petterroea.gamedev.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileBrowser {

	/**
	 * @param args
	 */
	Workspace master;
	File currentDir;
	int currentHover = -1;
	int fsHover = -1;
	public FileBrowser(Workspace master)
	{
		currentDir=File.listRoots()[0];
		this.master = master;
	}
	public boolean isProper(String fileName)
	{
		if(fileName.endsWith(".png"))
		{
			return true;
		}
		return false;
	}
	public void draw(Graphics g, Workspace master)
	{
		g.setColor(new Color(40, 40, 40));
		g.fillRect(0, 0, master.getWidth(), master.getHeight());
		g.setColor(new Color(60, 60, 60));
		g.drawLine(100, 0, 100, master.getHeight());
		g.setColor(new Color(90, 90, 90));
		g.fillRect(101, 0, master.getWidth()-101, 15);
		g.setColor(new Color(40, 40, 40));
		g.drawString("...", ((master.getWidth()-100)/2)+100, 10);
		g.setColor(new Color(90, 90, 90));
		for(int i = 0; i < File.listRoots().length; i++)
		{
			if(i==fsHover)
			{
				g.setColor(new Color(50, 50, 50));
				g.fillRect(0, (i*15)+15, 100, 15);
				g.setColor(new Color(90, 90, 90));
			}
			g.drawString(File.listRoots()[i].getAbsolutePath(), 6, (i*15)+26);
		}
		for(int i = 0; i < currentDir.listFiles().length; i++)
		{
			if(i==currentHover)
			{
				g.setColor(new Color(50, 50, 50));
				g.fillRect(100, (i*15)+15, master.getWidth()-100, 15);
				g.setColor(new Color(90, 90, 90));
			}
			String build = currentDir.listFiles()[i].getName();
			if(currentDir.listFiles()[i].isDirectory())
			{
				build = build + "/";
			}
			if(isProper(build))
			{
				g.setColor(new Color(90, 128, 90));
			}
			else if(!build.endsWith("/"))
			{
				g.setColor(new Color(128, 90, 90));
			}
			g.drawString(build, 110, (i*15)+26);
			g.setColor(new Color(90, 90, 90));
		}
		if(currentHover>-1&&currentHover<currentDir.listFiles().length)
		{
			if(currentDir.listFiles()[currentHover].isDirectory())
			{
				g.drawString("Current folder: "+currentDir.listFiles()[currentHover].getName()+"/", 110, master.getHeight()-6);
			}
			else
			{
				g.drawString("Current file: "+currentDir.listFiles()[currentHover].getName(), 110, master.getHeight()-6);
			}
		}
	}
	public void click(int x, int y)
	{
		Rectangle open = new Rectangle(0, 0, 100, master.getHeight());
		if(open.contains(x, y))
		{
			if(((y)/15)-1<File.listRoots().length&&((y)/15)-1>-1)
			{
				currentDir = File.listRoots()[((y)/15)-1];
			}
			master.repaint();
			return;
		}
		open = new Rectangle(101, 0, master.getWidth()-101, master.getHeight());
		if(open.contains(x, y))
		{
			if(y<15)
			{
				if(currentDir.getParentFile()!=null)
				{
					currentDir = currentDir.getParentFile();
					master.repaint();
					return;
				}
			}
			if(currentHover>-1&&currentHover<currentDir.listFiles().length)
			{
				if(currentDir.listFiles()[currentHover].isDirectory())
				{
					currentDir=currentDir.listFiles()[currentHover];
					master.repaint();
					return;
				}
				else if(isProper(currentDir.listFiles()[currentHover].getName()))
				{
					try {
						master.image = ImageIO.read(currentDir.listFiles()[currentHover]);
						currentDir=File.listRoots()[0];
						master.inFileBrowser=false;
						master.repaint();
						master.imageLoaded();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public void moved(int x, int y)
	{
		Rectangle open = new Rectangle(101, 0, master.getWidth()-101, master.getHeight());
		if(open.contains(x, y))
		{
			currentHover = ((y)/15)-1;
			master.repaint();
		}
		else
		{
			currentHover = -1;
			master.repaint();
		}
		open = new Rectangle(0, 0, 100, master.getHeight());
		if(open.contains(x, y))
		{
			fsHover = ((y)/15)-1;
			master.repaint();
		}
		else
		{
			fsHover = -1;
			master.repaint();
		}
	}

}
