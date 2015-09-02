package com.carter.glui.ui.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;

import com.carter.glui.DrawContext;
import com.carter.glui.ui.component.Resizable;
import com.carter.glui.ui.component.ResizeComponent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

public class MoveablePanel extends AbstractPanel implements Resizable
{
	
	private int xOffset;
	private int yOffset;
	
	private ResizeComponent resizeComponent;
	
	public MoveablePanel(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		this.pressed = false;
		this.resizeComponent = new ResizeComponent(this, width, 0);
		this.children.add(resizeComponent);
	}
	
	@Override
	public void released()
	{
		this.pressed = false;
		this.children.forEach((c) -> c.released());
	}
	@Override
	public void doPressed(Point2D.Double glPoint)
	{
		this.xOffset = (int)glPoint.x - x;
		this.yOffset = (int)glPoint.y - y;
	}
	
	@Override
	public void dragged(Point2D.Double glPoint)
	{
		if(pressed)
		{
			x = (int)glPoint.x - xOffset;
			y = (int)glPoint.y - yOffset;
		}
	}
	
	@Override
	public void doDraw(DrawContext dc)
	{
		GL2 gl = dc.getGL2();
		
		
		if(dc.isPickingMode())
		{ 
			Color pickingColor = dc.getUniquePickingColor();
			gl.glColor3ub((byte)pickingColor.getRed(), (byte)pickingColor.getGreen(), (byte)pickingColor.getBlue());
			dc.registerPickableObject(pickingColor.getRGB(), this);
		}
		else
		{
			gl.glColor3ub((byte)background.getRed(), (byte)background.getGreen(), (byte)background.getBlue());
		}
		
		drawPanel(dc);
	}

	@Override
	public void doReleased() 
	{
	}

	@Override
	public void resized(int width, int height) 
	{
		this.width = width;
		this.height = height;
	}
}
