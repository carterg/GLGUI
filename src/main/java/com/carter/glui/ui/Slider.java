package com.carter.glui.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.carter.glui.DrawContext;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Slider extends VisibleNode
{
	private static final int HEIGHT = 10;
	private static final int LINE_WIDTH = 3;
	private int min;
	private int max;
	private int width;
	private int current;
	
	
	public Slider(int x, int y, int width, int min, int max, int current)
	{
		super(x, y);
		this.min = min;
		this.max = max;
		this.width = width;
		this.current = current;
		this.hovered = false;
	}
	
	
	@Override
	public void doDraw(DrawContext dc)
	{
		GL2 gl = dc.getGL2();
		
		if(!hovered)
		{
			gl.glColor3d(0.5, 0.5, 0.5);
		}
		else
		{
			gl.glColor3d(1.0, 1.0, 1.0);
		}
		gl.glLineWidth(LINE_WIDTH);
		gl.glBegin(GL2.GL_LINES);
//		gl.glVertex2d(center.x - width/2, center.y);
//		gl.glVertex2d(center.x + width/2, center.y);
		gl.glEnd();
		gl.glLineWidth(1);
		
	}
	
	@Override
	public Rectangle getBounds() 
	{
//		return new Rectangle((int)(center.x - width/2), (int)(center.y - 5), width, 10);
		return new Rectangle(0, 0, 1, 1);
	}
	
	public Rectangle computeViewport()
	{
		return new Rectangle(10, 10, 100, 100);
	}


	@Override
	public void doPressed(Double glPoint) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doReleased() {
		// TODO Auto-generated method stub
		
	}

}
