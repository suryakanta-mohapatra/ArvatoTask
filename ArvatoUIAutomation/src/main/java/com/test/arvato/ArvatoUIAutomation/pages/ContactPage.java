package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactPage {

	public static final Logger log = Logger.getLogger(ContactPage.class.getName());
	WebDriver driver;
	
	public ContactPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[text()='Contact']")
	WebElement headerContact;
	
	@FindBy(xpath="(//*[contains(text(),' Name (required)')])[1]")
	WebElement labelName;
	
	@FindBy(xpath="(//*[contains(text(),'Company')])[1]")
	WebElement labelCompany;
	
	@FindBy(xpath="(//*[contains(text(),'Email')])[1]")
	WebElement labelEmail;
	
	@FindBy(xpath="(//*[contains(text(),'Phone')])[1]")
	WebElement labelPhone;
	
	@FindBy(xpath="(//*[contains(text(),'Message')])[1]")
	WebElement labelMsg;
	
	
	@FindBy(xpath="(//*[contains(text(),' Name (required)')])[1]//input")
	WebElement editName;
	
	@FindBy(xpath="(//*[contains(text(),'Company')])[1]//input")
	WebElement editCompany;
	
	@FindBy(xpath="(//*[contains(text(),'Email')])[1]//input")
	WebElement editEmail;
	
	@FindBy(xpath="(//*[contains(text(),'Phone')])[1]//input")
	WebElement editPhone;
	
	@FindBy(xpath="(//*[contains(text(),'Message')])[1]//textarea")
	WebElement editMsg;
	
	@FindBy(xpath="(//input[@type='submit'])[1]")
	WebElement btnSubmit;
	
	@FindBy(xpath="(//*[contains(text(),'Please check and')])[2]")
	WebElement errorGeneric;
	
	public void verifyContactPage(){
		Assert.assertEquals(headerContact.isDisplayed(), true, "Error!!! Element is missing from Contact page");
	}
	
	public void verifyAllElements(){
		verifyContactPage();
		boolean isAllElementPresent = labelName.isDisplayed() && labelCompany.isDisplayed() && labelEmail.isDisplayed() &&
									  labelPhone.isDisplayed() && labelMsg.isDisplayed() && editName.isDisplayed() && 
									  editCompany.isDisplayed() && editEmail.isDisplayed() && editPhone.isDisplayed() &&
									  editMsg.isDisplayed() && btnSubmit.isDisplayed();
		Assert.assertEquals(isAllElementPresent, true, "Error!!! One or more elements are missing from Contact page");
	}
	
	public void validateFields(String name, String company, String email, String phone, String message, String expectedError){
		String actualError = "N";
		editName.clear();
		editName.sendKeys(name.trim());
		editCompany.clear();
		editCompany.sendKeys(company.trim());
		editEmail.clear();
		editEmail.sendKeys(email.trim());
		editPhone.clear();
		editPhone.sendKeys(phone.trim());
		editMsg.clear();
		editMsg.sendKeys(message.trim());
		btnSubmit.submit();
		boolean isErrorDisplayed = driver.findElements(By.xpath("(//*[contains(text(),'Please check and')])[2]")).size()>0;//errorGeneric.isDisplayed();
		actualError = isErrorDisplayed?"Y":"N";
		Assert.assertEquals(actualError, expectedError, "Error!!! Field validation error in"
				+ " {"+"Name: "+name+": "+"Company: "+company+": "+"Email: "+email+": "+"Phone: "+phone+": "+"Message: "+message+": "+"Error: "+expectedError+": "+"}");
		
	}
	
}
