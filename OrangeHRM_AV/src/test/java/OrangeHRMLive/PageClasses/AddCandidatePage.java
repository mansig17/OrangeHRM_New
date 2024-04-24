package OrangeHRMLive.PageClasses;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class AddCandidatePage extends OrangeHRM_BaseClass
{
	public AddCandidatePage(WebDriver driver)
	{
		OrangeHRM_BaseClass.driver= driver;
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(how=How.XPATH, using="//button[text()=' Add ']")
		WebElement addRecruitButton ;
		
		@FindBy(how=How.XPATH, using="//input[@name='firstName']")
		WebElement firstName ;
		
		@FindBy(how=How.XPATH, using="//input[@name='middleName']")
		WebElement middleName ;
		
		@FindBy(how=How.XPATH, using="//input[@name='lastName']")
		WebElement lastName;
		
		@FindBy(how=How.XPATH, using="//div/div[text()='-- Select --']")
		WebElement selectRole ;
		
		@FindBy(how=How.XPATH, using="//label[text()='Email']/../../div[2]/input")
		WebElement email ;
		
		@FindBy(how=How.XPATH, using="//label[text()='Contact Number']/../../div[2]/input")
		WebElement contactNum;
		
		@FindBy(how=How.XPATH, using="//label[text()='Date of Application']/../../div[2]/div/div/input")
		WebElement dateCalender ;
		
		@FindBy(how=How.XPATH, using="//label[text()='Notes']/../../div[2]/textarea")
		WebElement notesTextArea;
		
		@FindBy(how=How.XPATH, using="//div/div/div[2]/div[2]/div/div/form/div[8]/button[2]")
		WebElement saveButton ;
		
		@FindBy(how=How.XPATH, using="//div[text()='Browse']")
		WebElement upload ;
		
		
		
		
		
		public void clickOnRecruitButton()
		{
			addRecruitButton.click();
		}
		
		public void sendFirstName(String name)
		{
			firstName.sendKeys(name);
		}
		
		public void sendMiddleName(String name)
		{
			middleName.sendKeys(name);
		}
		
		public void sendLastName(String name)
		{
			lastName.sendKeys(name);
		}
		
		public void sendEmail(String mail)
		{
			email.sendKeys(mail);
		}
		
		public void sendContact(String contact)
		{
			contactNum.sendKeys(contact);
		}
		
		public void sendNoteso(String notes)
		{
			notesTextArea.sendKeys(notes);
		}
		
		public void clickSave()
		{
			saveButton.click();
		}
		
		public void selectDate(Date d)
		{
			dateCalender.clear();
			//dateCalender.sendKeys("2024-18-04");
		}
		
		public void uploadResume()
		{
			upload.click();
		}
	
}
