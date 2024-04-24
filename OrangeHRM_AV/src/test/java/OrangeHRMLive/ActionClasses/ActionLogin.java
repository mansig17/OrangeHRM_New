package OrangeHRMLive.ActionClasses;

import static OrangeHRMLive.DataProvider.OrangeHRM_BaseDataProvider.GetCellData;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import OrangeHRM.Live.Utilities.GetScreenshot;
import OrangeHRMLive.DataProvider.OrangeHRM_AV_DataProvider;
import OrangeHRMLive.DataProvider.OrangeHRM_BaseDataProvider;
import OrangeHRMLive.PageClasses.LoginPage;
import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;
import orangeHRMLive.Report.generateExtentReports;

public class ActionLogin extends OrangeHRM_BaseClass
{
	LoginPage lp;
	generateExtentReports gr;
	GetScreenshot screenshot;
	@BeforeSuite
	void getReporter()
	{
		gr= new generateExtentReports();
		gr.generateReport();
	}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{

		setUp();
		System.out.print("Before the method");
	}

	@Test(dataProvider="TransactionalData",dataProviderClass=OrangeHRM_AV_DataProvider.class)
	public void LoginTest(String testName) throws IOException
	{
		OrangeHRM_BaseDataProvider b= new OrangeHRM_BaseDataProvider();
		ExtentTest test= gr.report.createTest(testName).assignCategory("Login");
		screenshot= new GetScreenshot();
		

		String uname, pass, testType;

		uname=GetCellData("Login", testName, "Username").toString();
		pass=GetCellData("Login", testName, "Password").toString();
		testType=GetCellData("Login", testName, "TestType").toString();

		System.out.println(testName+" "+uname+" "+pass);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp= new LoginPage(driver);
		System.out.println(uname +" "+pass);


		lp.loginToOrangeHrm(uname, pass);
		test.log(Status.PASS, "Passed the username and password");

		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.titleIs("OrangeHRM"));

		try
		{
		if(wait.until(ExpectedConditions.visibilityOf(lp.ele))!=null)
			test.log(Status.PASS, "Logged in to the WebPage");
		}
		
		catch(Exception e)
		{
			System.out.println("In catch");
			
			test.log(Status.FAIL, e.getMessage());
			screenshot.getScreenshot(testName, "Login");
			test.fail("Login Invalid", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\SRIJAN\\eclipse-workspace\\OrangeHRM_AV\\screenshots\\"+testName+"Login"+".png").build());
			
			
		
		}
		//else
		//wait.until(ExpectedConditions.visibilityOf(lp.wrongCreds));

	}

	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

	@AfterSuite
	void displayRep() throws IOException
	{
		gr.report.flush();

		Desktop.getDesktop().browse(new File("rep.html").toURI());
	}


}
