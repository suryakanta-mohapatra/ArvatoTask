package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EUProjectPage {

	public static final Logger log = Logger.getLogger(EUProjectPage.class.getName());
	WebDriver driver;
	
	public EUProjectPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[contains(@src,'development_fund')]")
	WebElement imgEUDevFund;
	
	public void verifyEUProjectPage(){
		Assert.assertEquals(imgEUDevFund.isDisplayed(), true, "Error!!! Element is missing from EU project page");
	}
	
}
