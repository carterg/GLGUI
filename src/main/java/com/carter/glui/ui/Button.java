package com.carter.glui.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import com.carter.glui.DrawContext;
import com.carter.glui.action.ButtonAction;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * A generic Button UI component.  
 * @author CARTER-DEV
 *
 */
public class Button extends VisibleNode 
{
	private static final int DEFAULT_FONT_SIZE = 20;
	private int width;
	private int height;
	private String text;
	private GLUT glut = new GLUT();
	
	protected ButtonAction action;
	protected TextRenderer textRenderer;
	protected int fontSize;
	
	protected String cachedDrawText = null;
	
	public Button(int x, int y, int width, int height, String text)
	{
		super(x, y);
		this.textRenderer = new TextRenderer(new Font("Dialog", Font.PLAIN, DEFAULT_FONT_SIZE));
		this.width = width;
		this.height = height;
		this.text = text;
		this.action = new ButtonAction() {};
	}

	public void setFontSize(int fontSize)
	{
		//this.fontSize = fontSize;
		//this.textRenderer = new TextRenderer(new Font("Veranda", Font.BOLD, fontSize));
	}
	
	public int getFontSize()
	{
		return fontSize;
	}
	
	@Override
	public void doDraw(DrawContext dc) 
	{
		GL2 gl = dc.getGL2();
		
		if(dc.isPickingMode())
		{
			Point drawPoint = getDrawCoordinates();
			Color uniqueColor = dc.getUniquePickingColor();
			gl.glColor3ub((byte)uniqueColor.getRed(), (byte)uniqueColor.getGreen(), (byte)uniqueColor.getBlue());
			dc.registerPickableObject(uniqueColor.getRGB(), this);
			dc.drawQuad(drawPoint.x, drawPoint.y, width, height);
		}
		else
		{
			drawBorder(gl, 126.0f/255.0f,180.0f/255.0f, 235.0f/255.0f);
			drawText(dc);
		}
	}
	
	private void drawText(DrawContext dc)
	{
		Point drawPoint = getDrawCoordinates();
		textRenderer.beginRendering(dc.getSurfaceWidth(), dc.getSurfaceHeight());
		textRenderer.setColor(Color.RED);
		textRenderer.setSmoothing(true);
		
		if(cachedDrawText == null)
		{
			double textWidth = textRenderer.getBounds(text).getWidth();
			if(textWidth > width)
			{
				// we need to shorten the text
				String drawText = text.substring(0, text.length()-1);
				
				while(textRenderer.getBounds(drawText).getWidth() > width)
				{
					drawText = drawText.substring(0, drawText.length() - 1);
				}
				cachedDrawText = drawText;
			}
			else
			{
				cachedDrawText = text;
			}
		}

		Rectangle2D bounds = textRenderer.getBounds(cachedDrawText);
		double textWidth = bounds.getWidth();
		double textHeight = bounds.getHeight();
		double xLeft = width - textWidth;
		double yLeft = height - textHeight;
			
		textRenderer.draw(cachedDrawText, (int) (drawPoint.x + xLeft/2), (int) (drawPoint.y + yLeft/2));
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
	
	private Point getDrawCoordinates()
	{
		int drawX = x;
		int drawY = y;
		
		if(parent instanceof VisibleNode)
		{
			VisibleNode p = (VisibleNode)parent;
			drawX = p.x + x;
			drawY = p.y + y;
		}
		
		return new Point(drawX, drawY);
	}
	private void drawBorder(GL2 gl, float r, float g, float b)
	{
		Point drawPoint = getDrawCoordinates();
		
		gl.glColor3f(r, g, b);
		gl.glBegin(GL2.GL_LINE_LOOP);
		// bottom left
		gl.glVertex2d(drawPoint.x - 1, drawPoint.y - 1);
		// top left
		gl.glVertex2d(drawPoint.x-1, drawPoint.y + height + 1);
		//top right
		gl.glVertex2d(drawPoint.x + width + 1, drawPoint.y + height + 1);
		// bottom right
		gl.glVertex2d(drawPoint.x + width + 1, drawPoint.y - 1);
		gl.glEnd();
	}
}
