package com.carter.glui;

import java.awt.Color;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import com.carter.glui.action.CheckboxListener;
import com.carter.glui.ui.Button;
import com.carter.glui.ui.Slider;
import com.carter.glui.ui.component.CheckBox;
import com.carter.glui.ui.component.ComboBox;
import com.carter.glui.ui.component.Label;
import com.carter.glui.ui.panel.MoveablePanel;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Test
{
	static
	{
		GLProfile.initSingleton();
	}
	public static void main(String[] args)
	{
		
		System.setProperty("sun.awt.noerasebackground", "true");
		
		SimpleScene scene = new SimpleScene();

		Button button = new Button(20, 20, 50, 20, "Ac");
		button.setFontSize(60);
		MoveablePanel panel = new MoveablePanel(10, 10, 300, 300);
		panel.setName("Yellow Panel");
		panel.setBackground(Color.YELLOW);
		panel.addChild(button);
//		//Label label = new Label("Test Label", 100, 100);
//		//panel.addChild(label);
//		//CheckBox cb = new CheckBox(250, 100);
//		//ComboBox cb = new ComboBox(250, 100);
//		//panel.addChild(cb);
		scene.addChild(panel);
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(scene);
		
		JFrame frame = new JFrame("A Window");
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.addMouseListener(scene);
		canvas.addMouseMotionListener(scene);
		
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
		
	}
}
