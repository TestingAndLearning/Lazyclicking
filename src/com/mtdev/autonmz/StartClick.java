package com.mtdev.autonmz;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class StartClick implements Runnable
{

	private int nClick = 0;
	private int interval = 0;
	private Robot robot;
	private AutoClicker autoClicker;
	PointerInfo currentMouse = MouseInfo.getPointerInfo();
	Point currentPos = currentMouse.getLocation();
	int currentX = (int) currentPos.getX();
	int currentY = (int) currentPos.getY();
	Random randomGenerator = new Random();
	boolean XNotReached=true;
	boolean YNotReached=true;
	boolean XYReached=false;
	
	public StartClick(AutoClicker autoClicker, int nClick, int interval)
	{
		try
		{
			robot = new Robot();

			this.nClick = nClick;
			this.interval = interval;
			this.autoClicker = autoClicker;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void run()
	{
		try
		{
			autoClicker.setProgressState(false);
			Thread.sleep(3000);
			
			
/*
			for (int i = 0; i < nClick; i++)
			{
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(interval);
			}
			
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_1);
*/
			currentMouse = MouseInfo.getPointerInfo();
			currentPos = currentMouse.getLocation();
			int fromX = (int) currentPos.getX();
			int fromY = (int) currentPos.getY();
			
			GlideMouse(fromX,fromY, 1000, 500, 5000, 1000);

			autoClicker.setProgressState(true);
			JOptionPane.showMessageDialog(null, "Done.");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void GlideMouse(int x1, int y1, int x2, int y2, int t, int n)
	{
	    try {
	        double dx = (x2 - x1) / ((double) n);
	        double dy = (y2 - y1) / ((double) n);
	        double dt = t / ((double) n);
	        for (int step = 1; step <= n; step++) {
	            Thread.sleep((int) dt);
	            robot.mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	
	

}