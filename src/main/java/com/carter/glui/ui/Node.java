package com.carter.glui.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import com.carter.glui.DrawContext;
import com.jogamp.opengl.GLAutoDrawable;

public abstract class Node
{
	protected List<Node> children;
	protected boolean pressed;
	protected boolean hovered;
	protected Node parent;
	protected String name;
	
	public Node()
	{
		this.children = new LinkedList<Node>();
		parent = null;
	}
	
	public void addChild(Node child)
	{
		this.children.add(child);
		child.parent = this;
	}
	
	public void removeChild(Node child)
	{
		this.children.remove(child);
		child.parent = null;
	}
	
	public void removeallChildren()
	{
		this.children.clear();
	}
	
	public void removeFromParent()
	{
		parent.removeChild(this);
	}

	public void hovered() 
	{
		this.hovered = true;
	};
	public void unhovered() 
	{
		this.hovered = false;
	};
	public void pressed(Point2D.Double glPoint) 
	{
		this.pressed = true;
	};
	public void released()
	{
		this.pressed = false;
	};
	
	public void dragged(Point2D.Double glPoint)
	{
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name == null ? super.toString() : name;
	}
	
	public abstract Rectangle getBounds();
	public abstract Rectangle computeViewport();
	
	
    
}
