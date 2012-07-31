package me.petterroea.gamedev.levelVector;

import java.awt.Polygon;
import java.util.LinkedList;

public class Layer {
	private float scrollAmount = 1.0f;
	public LinkedList<Polygon> polygons;
	/**
	 * @param args
	 */
	public Layer(int id)
	{
		polygons = new LinkedList<Polygon>();
	}
	public float getScrollAmount() {
		return scrollAmount;
	}
	public void setScrollAmount(float scrollAmount) {
		this.scrollAmount = scrollAmount;
	}

}
