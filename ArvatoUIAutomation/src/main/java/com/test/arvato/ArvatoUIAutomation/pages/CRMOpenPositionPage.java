package com.test.arvato.ArvatoUIAutomation.pages;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CRMOpenPositionPage {

	public static final Logger log = Logger.getLogger(CRMOpenPositionPage.class.getName());
	WebDriver driver;
	JobDescriptionPage jobDescriptionPage;
	
	public CRMOpenPositionPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jobDescriptionPage = new JobDescriptionPage(driver);
	}
	
	@FindBy(xpath="//h3[contains(text(),'Career in Arvato')]")
	WebElement headerCareer;
	
	@FindBy(xpath="//h2[contains(text(),'Opening up new perspectives')]")
	WebElement headerOpening;
	
	@FindBy(xpath="//div[@class='position']//parent::a")
	List<WebElement> linkPositions;
	
	@FindBy(xpath="//div[@class='position']/h4")
	List<WebElement> titlePositions;
	
	public void verifyCRMOPenPositionPage(){
		boolean isThisCRMOpenPositionPage = headerCareer.isDisplayed() && headerOpening.isDisplayed();
		Assert.assertEquals(isThisCRMOpenPositionPage, true, "Error!!! One or more elements from CRM open position page is missing");
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
			verifyCRMOPenPositionPage();
		}
		
	}
	
	public void navigateBack(){
		driver.navigate().back();
	}
	
	
}
