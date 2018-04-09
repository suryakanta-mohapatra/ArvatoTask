package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CRMCustomerServicePage {

	public static final Logger log = Logger.getLogger(CRMCustomerServicePage.class.getName());
	WebDriver driver;
	CRMOpenPositionPage crmOpenPositionPage;
	
	public CRMCustomerServicePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		crmOpenPositionPage = new CRMOpenPositionPage(driver);
	}
	
	@FindBy(xpath="//h3[contains(text(),'Building relationships')]")
	WebElement header;
	
	@FindBy(xpath="//strong[text()='employees']")
	WebElement txtPeople;
	
	@FindBy(xpath="//strong[text()='employees']//parent::div//preceding-sibling::div//*[text()='> 300']")
	WebElement txtPeopleStrength;
	
	@FindBy(xpath="//strong[text()='employees']//parent::div//preceding-sibling::div//img[contains(@src,'person')]")
	WebElement imgPeople;
	
	@FindBy(xpath="//strong[text()='languages']")
	WebElement txtLanguage;
	
	@FindBy(xpath="//strong[text()='languages']//parent::div//preceding-sibling::div//*[text()='15']")
	WebElement txtLanguageNum;
	
	@FindBy(xpath="//strong[text()='languages']//parent::div//preceding-sibling::div//img[contains(@src,'globe')]")
	WebElement imgLanguage;
	
	@FindBy(xpath="//strong[text()='contacts per month']")
	WebElement txtCPM;
	
	@FindBy(xpath="//strong[text()='contacts per month']//parent::div//preceding-sibling::div//*[text()='> 100 000']")
	WebElement txtCPMNum;
	
	@FindBy(xpath="//strong[text()='contacts per month']//parent::div//preceding-sibling::div//img[contains(@src,'phone')]")
	WebElement imgCPM;
	
	@FindBy(xpath="//strong[text()='CRM service provider']")
	WebElement txtProvider;
	
	@FindBy(xpath="//strong[text()='CRM service provider']//parent::div//preceding-sibling::div//*[text()='Leading']")
	WebElement txtProviderStatus;
	
	@FindBy(xpath="//strong[text()='CRM service provider']//parent::div//preceding-sibling::div//img[contains(@src,'cart')]")
	WebElement imgProvider;
	
	@FindBy(xpath="//a[text()='Open positions']")
	WebElement btnOpenPositions;
	
	public void verifyCRMCustServicePage(){
		boolean isThisCRMOPenPage = header.isDisplayed() && btnOpenPositions.isDisplayed();
		Assert.assertEquals(isThisCRMOPenPage, true, "Error!!! This is not CRM OPen Page");
	}
	
	public void verifyAllLink(){
		verifyCRMCustServicePage();
		boolean isAllElemetDisplayed = (txtPeople.isDisplayed() && txtPeopleStrength.isDisplayed() &&
				imgPeople.isDisplayed() && txtLanguage.isDisplayed() && txtLanguageNum.isDisplayed() &&
				imgLanguage.isDisplayed() && txtCPM.isDisplayed() && txtCPMNum.isDisplayed() &&
				imgCPM.isDisplayed() && txtProvider.isDisplayed() && txtProviderStatus.isDisplayed() &&
				imgProvider.isDisplayed());
		Assert.assertEquals(isAllElemetDisplayed, true, "Error!!! One or more element is missing from CRM Customer Service Page");
				
	}
	
	public void verifyOpenPositionLink(){
		btnOpenPositions.click();
		crmOpenPositionPage.verifyCRMOPenPositionPage();
	}
}
