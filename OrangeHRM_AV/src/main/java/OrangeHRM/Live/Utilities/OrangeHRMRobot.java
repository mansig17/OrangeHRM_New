package OrangeHRM.Live.Utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class OrangeHRMRobot 

{
	Robot rob;
	public void uploadInBrowser(String loc) throws AWTException, InterruptedException
	{
		rob= new Robot();
		int l = loc.length();
		for(int i=0;i<l;i++)
		{
			System.out.println(loc.charAt(i));
			char ch=loc.charAt(i);
			int c=loc.charAt(i);
			Thread.sleep(1000);
			if(Character.isUpperCase(ch))
				{
				rob.keyPress(KeyEvent.VK_SHIFT);
				rob.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
				rob.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
				rob.keyRelease(KeyEvent.VK_SHIFT);
			
				}
			else
			{
				rob.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
				rob.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
				
			}
		}
		
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		
	
	}
}

