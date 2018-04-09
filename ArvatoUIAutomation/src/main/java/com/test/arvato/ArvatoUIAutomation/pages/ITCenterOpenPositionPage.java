package com.test.arvato.ArvatoUIAutomation.pages;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ITCenterOpenPositionPage {

	public static final Logger log = Logger.getLogger(ITCenterOpenPositionPage.class.getName());
	WebDriver driver;
	JobDescriptionPage jobDescriptionPage;
	
	public ITCenterOpenPositionPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jobDescriptionPage = new JobDescriptionPage(driver);
	}
	
	@FindBy(xpath="//div[@class='position']//parent::a")
	List<WebElement> linkPositions;
	
	@FindBy(xpath="//div[@class='position']/h4")
	List<WebElement> titlePositions;
	
	@FindBy(xpath="//h4[text()='We offer you']")
	WebElement headerWeOffer;
	
	@FindBy(xpath="//div[text()='Large-scale international projects']")
	WebElement textLSIP;
	
	@FindBy(xpath="//*[text()='Top-notch custom made office']")
	WebElement textCustomOfc;
	
	@FindBy(xpath="//*[contains(text(),'Excellent coffee')]")
	WebElement textCoffee;
	
	@FindBy(xpath="//*[contains(text(),'benefits packages')]")
	WebElement textBenefits;
	
	public void verifyITOPenPositionPage(){
		boolean isThisITMOpenPositionPage = headerWeOffer.isDisplayed();
		Assert.assertEquals(isThisITMOpenPositionPage, true, "Error!!! Element is missing from IT open position page");
	}
	
	public void verifyAllElements(){
		verifyITOPenPositionPage();
		boolean allElements = textLSIP.isDisplayed() && textCustomOfc.isDisplayed() &&
							  textCoffee.isDisplayed() && textBenefits.isDisplayed();
		Assert.assertEquals(allElements, true, "Error!!! One or more elements are missing from IT open position page");
	}

	public void verifyAllLinksWorking() throws UnsupportedEncodingException{
		int positionCount = titlePositions.size();
		for(int i=0;i<positionCount;i++){
			WebElement position = titlePositions.get(i);
			String title = position.getText().toString();
			linkPositions.get(i).click();
			String jobTitleInJD = jobDescriptionPage.getPositionTitle();
			Assert.assertTrue(jobTitleInJD.toUpperCase().contains(title.toUpperCase()), "Error!!! The title in JD page and CRM Open position page are not same");	
			navigateBack();
			verifyITOPenPositionPage();
		}	
	}

	public void navigateBack(){
		driver.navigate().back();
	}

}
