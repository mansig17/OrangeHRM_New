package OrangeHRM.Live.Utilities;

import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class GetScreenshot extends OrangeHRM_BaseClass

{
	public void getScreenshot(String testName, String transaction) throws IOException
	{
		TakesScreenshot scr;
		scr= ((TakesScreenshot)driver);
		
		File srcFile= scr.getScreenshotAs(OutputType.FILE);
		File destFile= new File("C:\\Users\\SRIJAN\\eclipse-workspace\\OrangeHRM_AV\\screenshots\\"+testName+transaction+".png");
		FileUtils.copyFile(srcFile, destFile);
		
		
	}

}
