package com.mtdev.autonmz;

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
			
			GlideMouse(1000,500, fromX, fromY);

			autoClicker.setProgressState(true);
			JOptionPane.showMessageDialog(null, "Done.");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void GlideMouse(int toX, int toY, int fromX, int fromY)
	{
		boolean XShouldMove = true;
		boolean YShouldMove = true;
		
		int xDif = toX - fromX;
		int yDif = toY - fromY;

		double xyDifTally = 0;
		double yxDifTally = 0;
		
		//float xyDif = Math.abs(xDif)/Math.abs(yDif);
		//float yxDif = Math.abs(yDif)/Math.abs(xDif);
		
		//Remember to take care of divide by zero. 
		//float xyDif = Math.abs(xDif/yDif);
		//float yxDif = Math.abs(yDif/xDif);
		
		float xyDif = (Math.abs(xDif) * 100.0f) / Math.abs(yDif);
		float yxDif = (Math.abs(yDif) * 100.0f) / Math.abs(xDif);
		
		System.out.println("Y: " + yDif);
		System.out.println("X: " + xDif);
		
		try
		{
			while (!XYReached)
			{
				int rxint = randomGenerator.nextInt(3 + 1 + -1) - 1;
				int ryint = randomGenerator.nextInt(3);
				currentMouse = MouseInfo.getPointerInfo();
				currentPos = currentMouse.getLocation();
				currentX = (int) currentPos.getX();
				currentY = (int) currentPos.getY();
				
				int currentXDif = toX - currentX;
				int currentYDif = toY - currentY;
				
				yxDifTally += yxDif;
				xyDifTally += xyDif;
				
				System.out.println("YX: " + yxDifTally);
				System.out.println("XY: " + xyDifTally);
				/*
				if (xyDifTally > 1)
				{
					YShouldMove = true;
				}
				else
				{
					YShouldMove = false;
				}
				
				if (yxDifTally > 1)
				{
					XShouldMove = true;
				}
				else
				{
					XShouldMove = false;
				}
				*/
				
				
				//Add an arc to it. 
				//Add random RNG +1 or +2 pixels for speed. Should be faster in middle xDif/2 and yDif/2
				//Moves to the destination by incrementing by 1*negative or positive difference. 
				if (xyDifTally > 100)
				{
					robot.mouseMove((int)(currentX+(2*Math.signum(currentXDif))), currentY);
					xyDifTally = 0;
				}
				
				if (yxDifTally > 100)
				{
					robot.mouseMove(currentX, (int)(currentY+(2*Math.signum(currentYDif))));
					yxDifTally = 0;
				}
				
				int randomInt1 = randomGenerator.nextInt(2 + 1 + -1) - 1;
				//System.out.println(randomInt1);
				int randomInt2 = randomGenerator.nextInt(2 + 1 + -1) - 1;
				

				
				//robot.mouseMove((int)(currentX+(randomInt1*Math.signum(xDif))), (int)(currentY+(randomInt2*Math.signum(yDif))));
				

				
				//Helps Check for two conditions. Remember to condense and use ? to check instead. 
				if ((currentX > toX - 20 && currentX < toX + 20))
				{
					XNotReached=false;
				}
				else
				{
					XNotReached=true;
					XYReached=false;
				}
				
				if ((currentY > toY - 20 && currentY < toY + 20))
				{
					YNotReached=false;
				}
				else
				{
					YNotReached=true;
					XYReached=false;
				}
				
				if (!XNotReached && !YNotReached)
				{
					XYReached = true;
				}
				
				
				int randomInt = randomGenerator.nextInt(15);
				if (randomInt > 10)
				{
					randomInt = 1;
				}
	
				Thread.sleep(randomInt);
			}
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	
	

}