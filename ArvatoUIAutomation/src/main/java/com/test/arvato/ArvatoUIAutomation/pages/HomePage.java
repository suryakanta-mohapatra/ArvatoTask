package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="//img[@alt='Arvato Estonia']")
	WebElement logo;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLogo(){
		log.info("Verifying Logo");
		Assert.assertEquals(driver.getTitle(), "Arvato Services Estonia");
		Assert.assertEquals(logo.isDisplayed(), true,"Error!!! Logo not displayed");
	
	}
	
}
