package OrangeHRMLive.ActionClasses;

import java.io.IOException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import OrangeHRMLive.DataProvider.OrangeHRM_BaseDataProvider;
import OrangeHRMLive.PageClasses.LoginPage;
import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;
import static OrangeHRMLive.DataProvider.OrangeHRM_BaseDataProvider.GetCellData;



public class LoginAction extends OrangeHRM_BaseClass  {
	
	LoginPage lp;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		setUp();
		System.out.print("Before the method");
	}
	
	@Test(dataProvider="TransactionalData",dataProviderClass=OrangeHRM_BaseDataProvider.class)
	public void LoginTest(String testName)
	{
		OrangeHRM_BaseDataProvider b= new OrangeHRM_BaseDataProvider();
		String uname, pass, testType;
		
		uname=GetCellData("Login", testName, "Username").toString();
		pass=GetCellData("Login", testName, "Password").toString();
		testType=GetCellData("Login", testName, "TestType").toString();
		
		System.out.println(testName+" "+uname+" "+pass);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp= new LoginPage(driver);
		System.out.println(uname +" "+pass);
		
		
		lp.loginToOrangeHrm(uname, pass);
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.titleIs("OrangeHRM"));
		
		//if(testType.equalsIgnoreCase("positive"))
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[text()= \"Dashboard\"]"))));
		
		//else
			//wait.until(ExpectedConditions.visibilityOf(lp.wrongCreds));
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
