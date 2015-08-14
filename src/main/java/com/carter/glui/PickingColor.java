package com.carter.glui;

import java.awt.Color;

public class PickingColor 
{
	private static int currentColorIndex = 0;
	
	public static Color getUniquePickingColor()
	{
		return new Color(currentColorIndex++);
	}
}
