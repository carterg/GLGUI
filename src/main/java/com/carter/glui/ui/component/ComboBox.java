package com.carter.glui.ui.component;

import java.awt.Rectangle;
import java.awt.geom.Point2D.Double;

import com.carter.glui.DrawContext;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.GL2;

public class ComboBox extends VisibleNode
{
	private static int HEIGHT = 20;
	private static int WIDTH = 100;
	protected boolean expanded;
	
	private ComboBoxArrow comboBoxArrow;
	
	public ComboBox(int x, int y) 
	{
		super(x, y);
		this.comboBoxArrow = new ComboBoxArrow(100, 100);
		this.addChild(this.comboBoxArrow);
	}

	@Override
	public void doPressed(Double glPoint) 
	{
		
	}

	@Override
	public void doReleased() 
	{
		
	}

	@Override
	public void doDraw(DrawContext dc) 
	{
		//draw the top box
		GL2 gl = dc.getGL2();
		gl.glColor3f(1.0f, 0.2f, 0.5f);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2d(x - WIDTH/2, y - HEIGHT/2);
		gl.glVertex2d(x - WIDTH/2, y + HEIGHT/2);
		gl.glVertex2d(x + WIDTH/2, y + HEIGHT/2);
		gl.glVertex2d(x + WIDTH/2, y - HEIGHT/2);
		gl.glEnd();
		
		// start down arrow box
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex2d(x + WIDTH/2 - 15, y + HEIGHT/2);
		gl.glVertex2d(x + WIDTH/2, y + HEIGHT/2);
		gl.glVertex2d(x + WIDTH/2, y - HEIGHT/2);
		gl.glVertex2d(x + WIDTH/2 - 15, y - HEIGHT/2);
		gl.glEnd();
		
		// draw triangle
		gl.glBegin(GL2.GL_TRIANGLES);
		gl.glVertex2d(x + WIDTH/2 - 13, y + HEIGHT/2 - 5);
		gl.glVertex2d(x + WIDTH/2 - 2, y + HEIGHT/2 - 5);
		gl.glVertex2d(x + WIDTH/2 -7, y - HEIGHT /2 + 3);
		gl.glEnd();
		
	}

	@Override
	public Rectangle getBounds() 
	{
		return null;
	}

	@Override
	public Rectangle computeViewport() 
	{
		return null;
	}

}
