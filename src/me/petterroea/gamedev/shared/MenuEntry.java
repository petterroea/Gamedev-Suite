package me.petterroea.gamedev.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEntry {
	public String name = "Entry";
	public ActionListener onClick=new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Unimplemented
			
		}};
	public MenuEntry(String name, ActionListener actionListener)
	{
		this.name = name;
		if(actionListener==null)
		{
			
		}
		else
		{
			this.onClick=actionListener;
		}
	}
}
