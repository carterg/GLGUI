package com.carter.glui.ui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Point2D.Double;

import com.carter.glui.DrawContext;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.util.awt.TextRenderer;

public class Label extends VisibleNode 
{
	protected String text;
	protected TextRenderer renderer;
	
	public Label(String text, int x, int y)
	{
		super(x,y);
		this.text = text;
		renderer = new TextRenderer(new Font("Arial", Font.BOLD, 50));
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}

	@Override
	public void doPressed(Double glPoint) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doReleased()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDraw(DrawContext dc)
	{

		renderer.beginRendering(dc.getSurfaceWidth(), dc.getSurfaceHeight());
		renderer.setColor(Color.BLUE);
		renderer.draw(text, x, y);
		renderer.endRendering();
	}

	@Override
	public Rectangle getBounds() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle computeViewport()
	{
		return parent.computeViewport();
	}
}
