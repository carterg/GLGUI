package com.carter.glui.ui;

import java.awt.Point;
import java.awt.geom.Point2D;

import com.carter.glui.DrawContext;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class DrawHelper
{
	public static void drawRaisedBox(DrawContext dc, int x, int y, int width, int height)
	{
		int farWidth = x + width;
		int farHeight = y + height;
		
		GL2 gl = dc.getGL2();
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2i(x+1, y+1);
		gl.glVertex2i(farWidth - 1, y+1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
		
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(x, farHeight);
		gl.glVertex2i(x, y);
		gl.glVertex2i(farWidth, y);
		gl.glEnd();
		
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth, y);
		gl.glVertex2i(farWidth, farHeight);
		gl.glVertex2i(x, farHeight);
		gl.glEnd();
		
		gl.glColor3f(.5f, .5f, .5f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth - 1, y + 1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
	}
	
	public static void drawHoveredBox(DrawContext dc, int x, int y, int width, int height)
	{
		int farWidth = x + width;
		int farHeight = y + height;
		
		GL2 gl = dc.getGL2();
		gl.glColor3f(1.0f, 1.0f, 1.0f);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2i(x+1, y+1);
		gl.glVertex2i(farWidth - 1, y+1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
		
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(x, farHeight);
		gl.glVertex2i(x, y);
		gl.glVertex2i(farWidth, y);
		gl.glEnd();
		
		gl.glColor3f(1f, 1f, 1f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth, y);
		gl.glVertex2i(farWidth, farHeight);
		gl.glVertex2i(x, farHeight);
		gl.glEnd();
		
		gl.glColor3f(1f, 1f, 1f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth - 1, y + 1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
	}
	
	public static void drawLoweredBox(DrawContext dc, int x, int y , int width, int height)
	{
		int farWidth = x + width;
		int farHeight = y + height;
		
		GL2 gl = dc.getGL2();
		gl.glColor3f(.5f, .5f, .5f);
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glVertex2i(x+1, y+1);
		gl.glVertex2i(farWidth - 1, y+1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
		
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(x, farHeight);
		gl.glVertex2i(x, y);
		gl.glVertex2i(farWidth, y);
		gl.glEnd();
		
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth, y);
		gl.glVertex2i(farWidth, farHeight);
		gl.glVertex2i(x, farHeight);
		gl.glEnd();
		
		gl.glColor3f(0f, 0f, 0f);
		gl.glBegin(GL.GL_LINE_STRIP);
		gl.glVertex2i(farWidth - 1, y + 1);
		gl.glVertex2i(farWidth - 1, farHeight - 1);
		gl.glVertex2i(x + 1, farHeight - 1);
		gl.glEnd();
	}
	
	public static Point2D.Double normalizePoint(Point point, int coordWidth, int coordHeight)
	{
		double x = (double)point.x / (double)coordWidth;
		double y = (double)point.y / (double)coordHeight;
		return new Point2D.Double(x, y);
	}
	
	public static Point2D.Double convertAWTPointToOpenGLPoint(Point awtPoint, int coordWidth, int coordHeight)
	{
		Point2D.Double normalizedAWTPoint = normalizePoint(awtPoint, coordWidth, coordHeight);
		double px = 0 + normalizedAWTPoint.x * (coordWidth);
		double py = coordHeight + normalizedAWTPoint.y * (-coordHeight);
		
		return new Point2D.Double(px, py);
	}
	
	
}
