 package com.carter.glui.ui.component;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.carter.glui.DrawContext;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.GL2;

public class ResizeComponent extends VisibleNode
{
	protected Resizable resizable;
	
	public ResizeComponent(Resizable resizeable, int x, int y) 
	{
		super(x, y);
		this.resizable = resizeable;
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
			gl.glColor3d(1.0, 1.0, 1.0);
		}
		
		//gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(x, y);
		gl.glVertex2d(x - 20, y);
		gl.glVertex2d(x, y + 20);
		gl.glEnd();
	}
	
	@Override
	public void dragged(Point2D.Double pressPoint)
	{
	}
	
	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x, y, 20, 20);
	}

	@Override
	public Rectangle computeViewport() 
	{
		return new Rectangle(x, y, 20, 20);
	}

	@Override
	public void doPressed(Double glPoint) 
	{
	}

	@Override
	public void doReleased() 
	{
	}

}
