package com.carter.glui.ui.component;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.LinkedList;
import java.util.List;

import com.carter.glui.DrawContext;
import com.carter.glui.action.CheckboxListener;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * X and Y is at CENTER of box
 * @author pemad_000
 *
 */
public class CheckBox extends VisibleNode
{
	
	protected boolean checked;
	protected List<CheckboxListener> listeners;
	
	public CheckBox(int x, int y) 
	{
		super(x, y);
		this.listeners = new LinkedList<CheckboxListener>();
	}

	
	public boolean isChecked()
	{
		return checked;
	}
	
	public void addCheckboxListener(CheckboxListener listener)
	{
		this.listeners.add(listener);
	}
	public void removeCheckboxListener(CheckboxListener listener)
	{
		this.listeners.remove(listener);
	}
	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	@Override
	public void doReleased()
	{
		
		
	}

	@Override
	public void doDraw(DrawContext dc) 
	{
		GL2 gl = dc.getGL2();
		
		if(dc.isPickingMode())
		{
			Color uniqueColor = dc.getUniquePickingColor();
			gl.glColor3ub((byte)uniqueColor.getRed(), (byte)uniqueColor.getGreen(), (byte)uniqueColor.getBlue());
			dc.registerPickableObject(uniqueColor.getRGB(), this);
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex2d(x - 10, y - 10);
			gl.glVertex2d(x - 10, y + 10);
			gl.glVertex2d(x + 10, y + 10);
			gl.glVertex2d(x + 10, y - 11);
			gl.glEnd();
		}
		else
		{
			if(hovered)
				gl.glColor4f(0.4f, 0.2f, 0.8f, 0.5f);
			else
				gl.glColor3f(0.2f, 0.2f, 0.2f);
			gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glVertex2d(x - 10, y - 10);
			gl.glVertex2d(x - 10, y + 10);
			gl.glVertex2d(x + 10, y + 10);
			gl.glVertex2d(x + 10, y - 10);
			gl.glEnd();
			
			if(checked)
			{
				gl.glBegin(GL.GL_LINES);
				gl.glVertex2d(x - 8, y + 8);
				gl.glVertex2d(x + 8, y - 8);
				gl.glVertex2d(x - 8, y - 8);
				gl.glVertex2d(x + 8, y + 8);
				gl.glEnd();
			}
		}
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void doPressed(Point2D.Double glPoint)
	{
		checked = !checked;
		listeners.forEach((l) -> l.checkboxStateChanged(checked));
	}

}
