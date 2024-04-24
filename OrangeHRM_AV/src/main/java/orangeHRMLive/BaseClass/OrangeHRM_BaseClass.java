package orangeHRMLive.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class OrangeHRM_BaseClass {

	public static WebDriver driver;

	
	public static void setUp() throws IOException, InterruptedException
	{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\SRIJAN\\eclipse-workspace\\OrangeHRM_AV\\src\\main\\java\\globalProperties.properties");

		prop.load(fis);

		String browser= prop.getProperty("BROWSER");
		String url= prop.getProperty("URL");
		
		System.out.print(url);

		switch(browser) 

		{

		case "Firefox": System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		System.out.println("firefox");
		break;

		}

		driver.get("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		


	}


}
