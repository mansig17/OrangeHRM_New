package OrangeHRMLive.ActionClasses;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.testng.annotations.*;

import OrangeHRM.Live.Utilities.OrangeHRMRobot;
import OrangeHRMLive.DataProvider.OrangeHRM_AV_DataProvider;
import OrangeHRMLive.PageClasses.*;
import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class AddCandidateAction extends OrangeHRM_BaseClass

{

	DashboardPage db;	
	AddCandidatePage acp;
	LoginPage lp;
	OrangeHRMRobot rob;
	@BeforeMethod
	public void InitialSetup() throws IOException, InterruptedException
	{
		setUp();
	}
	
	
	@Test(dataProvider="TransactionalData",dataProviderClass=OrangeHRM_AV_DataProvider.class)
	public void addCand(String testName) throws InterruptedException, AWTException
	{
		
		db = new DashboardPage(driver);
		acp= new AddCandidatePage(driver);
		lp= new LoginPage(driver);
		rob= new OrangeHRMRobot();
		
		System.out.println(testName);
		lp.loginToOrangeHrm("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(3000);
		db.clickOnRecruitment();
		Thread.sleep(2000);
		acp.clickOnRecruitButton();
		acp.sendFirstName("Mansi");
		acp.sendLastName("Gupta");
		//acp.sendMiddleName(null);
		acp.sendContact("890809090");
		Date d= new Date(124,04,16);
		
		Thread.sleep(3000);
		acp.selectDate(d);
		acp.sendNoteso("New Candidate");
		acp.uploadResume();
		rob.uploadInBrowser("Stockholm-Resume-Template-Simple.pdf");
		System.out.println("Candidate added");
		
	}

}
