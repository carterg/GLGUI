package com.carter.glui;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import com.carter.glui.ui.Node;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class DrawContext 
{
	private GLAutoDrawable autoDrawable;
	private boolean pickingMode;
	
	private Map<Integer, Node> pickableObjects;
	
	public DrawContext(GLAutoDrawable autoDrawable)
	{
		this.autoDrawable = autoDrawable;
		this.pickingMode = false;
		this.pickableObjects = new HashMap<Integer, Node>();
	}
	
	public boolean isPickingMode()
	{
		return pickingMode;
	}
	
	public void setPickingMode(boolean isPickingMode)
	{
		this.pickingMode = isPickingMode;
	}
	
	public GL2 getGL2()
	{
		return autoDrawable.getGL().getGL2();
	}
	
	public int getSurfaceWidth()
	{
		return autoDrawable.getSurfaceWidth();
	}
	
	public int getSurfaceHeight()
	{
		return autoDrawable.getSurfaceHeight();
	}
	
	public Color getUniquePickingColor()
	{
		return PickingColor.getUniquePickingColor();
	}
	
	public void registerPickableObject(int color, Node object)
	{
		pickableObjects.put(color, object);
	}
	
	public Node getPickableObject(int color)
	{
		return pickableObjects.get(color);
	}
	public void drawQuad(int x, int y, int width, int height)
	{
		GL2 gl = getGL2();
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex2d(x, y);
		gl.glVertex2d(x, height + y);
		gl.glVertex2d(width + x, height + y);
		gl.glVertex2d(width + x, y);
		gl.glEnd();
	}
	
	public void setViewport(Rectangle viewport)
	{
		GL2 gl = getGL2();
		gl.glViewport(viewport.x, viewport.y, viewport.height, viewport.width);
	}
}
