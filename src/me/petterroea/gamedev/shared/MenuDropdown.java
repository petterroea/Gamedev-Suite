package me.petterroea.gamedev.shared;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class MenuDropdown {

	String name = "unnamed";
	int x = 0;
	int y = 0;
	LinkedList<MenuEntry> entryList;
	public boolean open = false;
	boolean mouseOver=false;
	int over = -1;
	public MenuDropdown(String name, int x, int y, MenuEntry[] entries)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		entryList = new LinkedList<MenuEntry>();
		for(int i = 0; i < entries.length; i++)
		{
			entryList.add(entries[i]);
		}
	}
	public boolean mouseMove(int xm, int ym)
	{
		over = -1;
		Rectangle me = new Rectangle(x, y, (name.length()*7)+10, 20);
		if(me.contains(xm, ym))
		{
			if(mouseOver==false)
			{
				mouseOver=true;
				return true;
			}
			over = -1;
			
		}
		else
		{
			if(open)
			{
				int maxLen = 0;
				for(int i = 0; i < entryList.size(); i++)
				{
					if(entryList.get(i).name.length()*6>maxLen)
					{
						maxLen=(entryList.get(i).name.length()*6)+105;
					}
				}
				Rectangle box = new Rectangle(x, y+21, maxLen, (entryList.size()*20)+20);
				if(box.contains(xm, ym))
				{
					over = (ym-21)/20;
					return true;
				}
				else
				{
					over = -1;
					if(mouseOver)
					{
						mouseOver = false;
						return true;
					}
				}
			}
			else
			{
				over = -1;
				if(mouseOver)
				{
					mouseOver = false;
					return true;
				}
			}
			
		}
		over = -1;
		return false;
	}
	public boolean click(int xm, int ym)
	{
		Rectangle me = new Rectangle(x, y, (name.length()*7)+10, 20);
		if(me.contains(xm, ym))
		{
			if(open==false)
			{
				open=true;
				return true;
			}
			
		}
		else
		{
			if(open)
			{
				if(over>-1)
				{
					entryList.get(over).onClick.actionPerformed(null);
				}
				open = false;
				return true;
			}
			
		}
		return false;
	}
	public void draw(Graphics g)
	{
		if(mouseOver)
		{
			g.setColor(new Color(55, 55, 55));
			g.fillRect(x, y, (name.length()*7)+10, 20);
			g.setColor(new Color(100, 100, 100));
			g.drawString(name, x+10, y+13);
		}
		else
		{
			g.setColor(new Color(90, 90, 90));
			g.drawString(name, x+10, y+13);
		}
		if(open)
		{
			int maxLen = 0;
			for(int i = 0; i < entryList.size(); i++)
			{
				if(entryList.get(i).name.length()*6>maxLen)
				{
					maxLen=(entryList.get(i).name.length()*6)+105;
				}
			}
			g.setColor(new Color(45, 45, 45));
			g.fillRect(x, y+21, maxLen, (entryList.size()*20)+20);
			
			g.setColor(new Color(60, 60, 60));
			g.drawRect(x, y+20, maxLen, (entryList.size()*20)+20);
			
			g.setColor(new Color(90, 90, 90));
			for(int i = 0; i < entryList.size(); i++)
			{
				if(i==over)
				{
					g.setColor(new Color(65, 65, 65));
					g.fillRect(x, y+(i*20)+20, maxLen, 20);
					g.setColor(new Color(90, 90, 90));
					g.drawString(entryList.get(i).name, x+5, y+(i*20)+35);
				}
				else
				{
					g.drawString(entryList.get(i).name, x+5, y+(i*20)+35);
				}
			}
		}
	}
}
