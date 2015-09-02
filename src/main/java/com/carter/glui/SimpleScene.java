package com.carter.glui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.List;

import com.carter.glui.ui.Button;
import com.carter.glui.ui.DrawHelper;
import com.carter.glui.ui.Node;
import com.carter.glui.ui.VisibleNode;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;

public class SimpleScene implements GLEventListener, MouseListener, MouseMotionListener
{
	private List<Node> children;
	
	private int width;
	private int height;
	
	private Node hoveredChild = null;
	private Point2D.Double lastCursorPoint;
	private Node lastHoveredNode = null;
	private Node grabbedNode;
	
	private boolean mousePressed = false;
	
	public SimpleScene()
	{
		this.children = new LinkedList<Node>();
		this.lastCursorPoint = new Point2D.Double(0, 0);
	}
	
	public void addChild(Node child)
	{
		this.children.add(child);
	}
	
	@Override
	public void display(GLAutoDrawable drawable)
	{
		DrawContext dc = new DrawContext(drawable);
		update();
		pick(dc);
		render(dc);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		this.width = width;
		this.height = height;
		
        GL2 gl = drawable.getGL().getGL2();
        height = (height == 0) ? 1 : height;
 
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
 
        gl.glOrtho(0d, width, 0d, height, -5, 5);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}
	
	private void pick(DrawContext dc)
	{
		GL2 gl = dc.getGL2();
		
		dc.setPickingMode(true);
		render(dc);
		dc.setPickingMode(false);
		
		gl.glFlush();
		gl.glFinish();
		
		ByteBuffer buffer = ByteBuffer.allocate(3);
		gl.glReadPixels((int)lastCursorPoint.x, (int)lastCursorPoint.y, 1, 1, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, buffer);
		byte rgb[] = buffer.array();
		Color pickedColor = new Color((int)rgb[0] & 0xFF, (int)rgb[1] & 0xFF, (int)rgb[2] & 0xFF);
		
		Node node = dc.getPickableObject(pickedColor.getRGB());
		
		if(hoveredChild != null && hoveredChild != node)
		{
			hoveredChild.unhovered();
		}
		this.hoveredChild = node;
		
		if(mousePressed)
		{
			mousePressed = false;
			if(node != null)
			{
				node.pressed(lastCursorPoint);
			}
		}
		else
		{
			if(node != null)
			{
				node.hovered();
			}
		}
	}
	private void update()
	{
	}
	
	private void render(DrawContext dc)
	{
		
		GL2 gl = dc.getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, width, 0, height, -1, 1);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glViewport(0, 0, width, height);
		
		for(Node child : children)
		{
			if(child instanceof VisibleNode)
			{
				((VisibleNode)child).draw(dc);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		Point2D.Double glPoint = DrawHelper.convertAWTPointToOpenGLPoint(e.getPoint(), width, height);
		this.lastCursorPoint = glPoint;
		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		mousePressed = false;
		children.forEach((c) -> c.released());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		Point2D.Double glPoint = DrawHelper.convertAWTPointToOpenGLPoint(e.getPoint(), width, height);
		this.lastCursorPoint = glPoint;
		
		if(this.hoveredChild != null)
		{
			hoveredChild.dragged(glPoint);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		Point2D.Double glPoint = DrawHelper.convertAWTPointToOpenGLPoint(e.getPoint(), width, height);
		this.lastCursorPoint = glPoint;
		
	}
}
