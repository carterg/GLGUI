package com.carter.glui.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import com.carter.glui.DrawContext;
import com.carter.glui.action.ButtonAction;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

/**
 * A generic Button UI component.  
 * @author CARTER-DEV
 *
 */
public class Button extends VisibleNode 
{
	private int width;
	private int height;
	private String text;
	protected ButtonAction action;
	
	public Button(int x, int y, int width, int height, String text)
	{
		super(x, y);
		this.width = width;
		this.height = height;
		this.text = text;
		this.action = new ButtonAction() {};
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
		}
		else
		{
			gl.glColor4f(0.4f, 0.2f, 0.8f, 0.5f);
		}
		
		
		// draw background
		dc.drawQuad(x, y, width, height);
		
		if(pressed)
		{
			DrawHelper.drawLoweredBox(dc, x, y, width, height);
		}
		else if(hovered)
		{
			DrawHelper.drawHoveredBox(dc, x, y, width, height);
		}
		else
		{
			DrawHelper.drawRaisedBox(dc, x, y, width, height);
		}
	}
	
	private void drawText(DrawContext dc)
	{
		TextRenderer textRenderer = new TextRenderer(new Font("Veranda", Font.BOLD, 12));
		textRenderer.beginRendering(dc.getSurfaceWidth(), dc.getSurfaceHeight());
		textRenderer.setColor(Color.YELLOW);
		textRenderer.setSmoothing(true);
		
		Rectangle2D bounds = textRenderer.getBounds(text);
		int fillerw = 200 - (int)bounds.getWidth();
		int fillerh = 50 - (int)bounds.getHeight();
		
		textRenderer.draw(text, 10 + fillerw/2, 10 + fillerh/2);
		textRenderer.endRendering();
	}
	
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
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void doPressed(Double glPoint)
	{
		action.pressedButtonAction();
		
	}

	@Override
	public void doReleased()
	{
		if(pressed)
		{
			action.releasedButtonAction();
		}
	}
}
