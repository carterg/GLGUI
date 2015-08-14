package com.carter.glui.action;

public interface ButtonAction extends Action
{

	default public void pressedButtonAction()
	{
		System.out.println("PressedButton");
	}
	
	default public void releasedButtonAction()
	{
		System.out.println("ReleasedButton");
	}
}
