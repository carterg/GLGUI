package com.carter.glui.ui.panel;

import java.awt.Color;
import java.awt.Rectangle;

import com.carter.glui.DrawContext;
import com.carter.glui.ui.Node;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.GL2;

/**
 * A base Panel that other custom panels can extend from.
 * @author Gary Carter
 *
 */
public abstract class AbstractPanel extends VisibleNode
{
	protected Color background;
	protected int width;
	protected int height;
	
	public AbstractPanel(int x, int y, int width, int height)
	{
		super(x, y);
		this.background = Color.WHITE;
		this.width = width;
		this.height = height;
	}
	
	public void setBackground(Color background)
	{
		this.background = background;
	}
	
	public Color getBackground()
	{
		return background;
	}
	
	protected void drawPanel(DrawContext dc)
	{
		dc.drawQuad(x, y, width, height);
	}
	
//	public abstract void doDraw(DrawContext dc);
	
	public void setViewportForChildren(DrawContext dc)
	{
		GL2 gl = dc.getGL2();
		
		// The viewport should be set to the panel since that is our new drawing surface
		Rectangle viewport = computeViewport();
		dc.setViewport(viewport);
		//  Reset the perspective matrix to the full size of the panel
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, viewport.width, 0, viewport.height, -1, 1);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	@Override
	public void draw(DrawContext dc)
	{
		doDraw(dc);
		setViewportForChildren(dc);
		for(Node c : children)
		{
			if(c instanceof VisibleNode)
			{
				((VisibleNode) c).draw(dc);
			}
		}
	}
	
	@Override
	public Rectangle computeViewport() 
	{
		if(parent == null)
		{
			return new Rectangle(x, y, width, height);
		}
		else
		{
			Rectangle parentViewport = parent.computeViewport();
			
			int newWidth = width;
			if(x + width > parentViewport.x + parentViewport.width)
			{
				newWidth = parentViewport.x + parentViewport.width - x;
			}
			
			int newHeight = height;
			if(y + height > parentViewport.y + parentViewport.height)
			{
				newHeight = parentViewport.y + parentViewport.height - y;
			}
			
			return new Rectangle(parentViewport.x + x, parentViewport.y + y, newWidth, newHeight);
		}
	}
	
	@Override
	public Rectangle getBounds() 
	{
		return computeViewport();
	}
	
	@Override
	public void released()
	{
		this.children.forEach((c)->c.released());
	}
	

}
