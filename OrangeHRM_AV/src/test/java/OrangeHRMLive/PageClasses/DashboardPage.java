package OrangeHRMLive.PageClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.*;

import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class DashboardPage extends OrangeHRM_BaseClass
{
	public DashboardPage(WebDriver driver)
	{
		OrangeHRM_BaseClass.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using="//span[text()='Recruitment']")
	WebElement recruit;
	
	
	public void clickOnRecruitment()
	{
		recruit.click();
	}
	

}
