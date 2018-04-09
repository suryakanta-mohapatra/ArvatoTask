package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ITDevCenterPage {

	public static final Logger log = Logger.getLogger(ITDevCenterPage.class.getName());
	WebDriver driver;
	ITCenterOpenPositionPage itCenterOpenPositionPage;
	
	@FindBy(xpath="//h3[contains(text(),'IT Development')]")
	WebElement header;
	
	@FindBy(xpath="//a/button[text()='Open positions']")
	WebElement btnOpenPositions;
	
	public ITDevCenterPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		itCenterOpenPositionPage= new ITCenterOpenPositionPage(driver);
	}
	
	public void verifyITDPage(){
		boolean isThisITDPage = header.isDisplayed() && btnOpenPositions.isDisplayed();
		Assert.assertEquals(isThisITDPage, true, "Error!!! IT Development page is missing one or more element");
	}
	
	public void verifyOpenPositionLink(){
		btnOpenPositions.click();
		itCenterOpenPositionPage.verifyITOPenPositionPage();
		
	}
}
