package com.carter.glui.ui;

import java.awt.geom.Point2D;

import com.carter.glui.DrawContext;

public abstract class VisibleNode extends Node
{
	protected int x;
	protected int y;
	protected boolean pressed;
	
	public VisibleNode(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public void pressed(Point2D.Double glPoint)
	{
		this.pressed = true;
		doPressed(glPoint);
	}
	
	@Override
	public void released()
	{
		doReleased();
		this.pressed = false;
	}
	
	public void draw(DrawContext dc)
	{
		this.doDraw(dc);
		for(Node child : children)
		{
			if(child instanceof VisibleNode)
			{
				((VisibleNode)child).draw(dc);
			}
		}
	}
	public abstract void doDraw(DrawContext dc);
	public abstract void doPressed(Point2D.Double glPoint);
	public abstract void doReleased();
	
}
