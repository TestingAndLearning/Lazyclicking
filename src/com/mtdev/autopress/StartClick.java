package com.mtdev.autopress;

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
*/
			for (int i=0; i < nClick; i++)
			{
					  Random randomGenerator = new Random();
					  int randomInt1 = randomGenerator.nextInt(480000);
					  
					  if (randomInt1 < 240000)
					  {
						  randomInt1 += 200000;
					  }
					  
					  Random randomGenerator2 = new Random();
					  int randomInt2 = randomGenerator2.nextInt(28);
					  
					  System.out.println(randomInt1);
					  System.out.println(randomInt2);
					  
					  Thread.sleep(randomInt1);
					  
				        switch (randomInt2) 
				        {
			            case 0:  robot.keyPress(KeyEvent.VK_1);
	                     		 break;
				        case 1:  robot.keyPress(KeyEvent.VK_A);
			                     break;
			            case 2:  robot.keyPress(KeyEvent.VK_B);
			                     break;
			            case 3:  robot.keyPress(KeyEvent.VK_C);
			                     break;
			            case 4:  robot.keyPress(KeyEvent.VK_D);
			                     break;
			            case 5:  robot.keyPress(KeyEvent.VK_E);
			                     break;
			            case 6:  robot.keyPress(KeyEvent.VK_F);
			                     break;
			            case 7:  robot.keyPress(KeyEvent.VK_G);
			                     break;
			            case 8:  robot.keyPress(KeyEvent.VK_H);
			                     break;
			            case 9:  robot.keyPress(KeyEvent.VK_I);
			                     break;
			            case 10: robot.keyPress(KeyEvent.VK_J);
			                     break;
			            case 11: robot.keyPress(KeyEvent.VK_K);
			                     break;
			            case 12: robot.keyPress(KeyEvent.VK_L);
			                     break;
			            case 13: robot.keyPress(KeyEvent.VK_M);
	                     		 break;
			            case 14: robot.keyPress(KeyEvent.VK_N);
			                     break;
			            case 15: robot.keyPress(KeyEvent.VK_O);
			                     break;
			            case 16: robot.keyPress(KeyEvent.VK_P);
			                     break;
			            case 17: robot.keyPress(KeyEvent.VK_Q);
			                     break;
			            case 18: robot.keyPress(KeyEvent.VK_R);
			                     break;
			            case 19: robot.keyPress(KeyEvent.VK_S);
			                     break;
			            case 20: robot.keyPress(KeyEvent.VK_T);
			                     break;
			            case 21: robot.keyPress(KeyEvent.VK_U);
			                     break;
			            case 22: robot.keyPress(KeyEvent.VK_V);
			                     break;
			            case 23: robot.keyPress(KeyEvent.VK_W);
			                     break;
			            case 24: robot.keyPress(KeyEvent.VK_X);
			                     break;
			            case 25: robot.keyPress(KeyEvent.VK_Y);
	                     		 break;
			            case 26: robot.keyPress(KeyEvent.VK_Z);
	                     		 break;
			            case 27: robot.keyPress(KeyEvent.VK_BACK_SPACE);
                		 		 break;
			            case 28: robot.keyPress(KeyEvent.VK_BACK_SPACE);
                		 		 break;
			            default: robot.keyPress(KeyEvent.VK_0);
			                     break;
				        }
				        
				        System.out.println(i+"/"+nClick);
			
			}
			autoClicker.setProgressState(true);
			JOptionPane.showMessageDialog(null, "Done.");
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}