package OrangeHRMLive.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class LoginPage extends OrangeHRM_BaseClass{
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//input[@name='username']")
	public	WebElement username;
	
	@FindBy(how=How.XPATH, using="//input[@name='password']")
	public WebElement password;
	
	@FindBy(how=How.XPATH, using="//button[@type='submit']")
	public WebElement submit;
	
	@FindBy(how=How.XPATH,using="//p[text()= \"Invalid credentials\"]")
	public WebElement wrongCreds;
	
	@FindBy(how=How.XPATH, using="//h6[text()= \"Dashboard\"]")
	public WebElement ele;
	
	public void loginToOrangeHrm(String uname, String pass)
	{
		username.sendKeys(uname);
		password.sendKeys(pass);
		submit.click();
	}
	
	

}
