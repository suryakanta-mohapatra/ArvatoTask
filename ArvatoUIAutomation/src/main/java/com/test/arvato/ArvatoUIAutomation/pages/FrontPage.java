package com.test.arvato.ArvatoUIAutomation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FrontPage {
	public static final Logger log = Logger.getLogger(FrontPage.class.getName());
	WebDriver driver;
	CRMCustomerServicePage crmCustomerServicePage;
	ITDevCenterPage itDevCenterPage;
	CRMOpenPositionPage crmOpenPositionPage;
	ITCenterOpenPositionPage itCenterOpenPositionPage;
	EUProjectPage euProjectPage;
	ContactPage contactPage;
	public enum MenuType {
	    HOME , CRM_CUST , ITD , CRM_OPEN , IT_OPEN , EU, CONTACT ;
	}
	
	public FrontPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		crmCustomerServicePage = new CRMCustomerServicePage(driver);
		itDevCenterPage = new ITDevCenterPage(driver);
		crmOpenPositionPage = new CRMOpenPositionPage(driver);
		itCenterOpenPositionPage = new ITCenterOpenPositionPage(driver);
		euProjectPage = new EUProjectPage(driver);
		contactPage = new ContactPage(driver);
		
	}
	
	@FindBy(xpath="//img[@alt='Arvato Estonia']")
	WebElement arvatoLogo;
	
	@FindBy(xpath="//img[contains(@src,'eu-project-en')]")
	WebElement euLogoEn;
	
	@FindBy(xpath="//h4[contains(text(),'IT Dev')]//following-sibling::div/a[text()='More info']")
	WebElement linkMoreInfoITD;
	
	@FindBy(xpath="//h4[contains(text(),'CRM')]//following-sibling::div/a[text()='More info']")
	WebElement linkMoreInfoCRM;
	
	@FindBy(xpath="//a[text()='en']")
	WebElement linkLangEN;
	
	@FindBy(xpath="//a[text()='ee']")
	WebElement linkLangEE;
	
	
	@FindBy(xpath="(//a[contains(text(),'Home')])[1]")
	WebElement linkHomeTop;
	
	@FindBy(xpath="(//a[contains(text(),'Home')])[2]")
	WebElement linkHomeButtom;
	
	@FindBy(xpath="(//a[contains(text(),'CRM & Customer Services')])[1]")
	WebElement linkCRMCustServiceTop;
	
	@FindBy(xpath="(//a[contains(text(),'CRM & Customer Services')])[2]")
	WebElement linkCRMCustServiceButtom;
	
	@FindBy(xpath="(//a[contains(text(),'IT Development Centre')])[1]")
	WebElement linkITDevCenterTop;
	
	@FindBy(xpath="(//a[contains(text(),'IT Development Centre')])[2]")
	WebElement linkITDevCenterButtom;
	
	@FindBy(xpath="(//a[contains(text(),'CRM Open positions')])[1]")
	WebElement linkCRMOpenPositionTop;
	
	@FindBy(xpath="(//a[contains(text(),'CRM Open positions')])[2]")
	WebElement linkCRMOpenPositionButtom;
	
	@FindBy(xpath="(//a[contains(text(),'IT Centre Open positions')])[1]")
	WebElement linkITCenterOpenPositionTop;
	
	@FindBy(xpath="(//a[contains(text(),'IT Centre Open positions')])[2]")
	WebElement linkITCenterOpenPositionButtom;
	
	@FindBy(xpath="(//a[contains(text(),'EU Project')])[1]")
	WebElement linkEUProjectTop;
	
	@FindBy(xpath="(//a[contains(text(),'EU Project')])[2]")
	WebElement linkEUProjectButtom;
	
	@FindBy(xpath="(//a[contains(text(),'Contact')])[1]")
	WebElement linkContactTop;
	
	@FindBy(xpath="(//a[contains(text(),'Contact')])[2]")
	WebElement linkContactButtom;
	
	public void verifyHeaderAndFooterMenu(){
		boolean headerlinksDisplayed = (linkHomeTop.isDisplayed() && linkCRMCustServiceTop.isDisplayed() && linkITDevCenterTop.isDisplayed() &&
							   linkCRMOpenPositionTop.isDisplayed() && linkITCenterOpenPositionTop.isDisplayed() && linkEUProjectTop.isDisplayed() &&
							   linkContactTop.isDisplayed());
		boolean footerlinksDisplayed = (linkHomeButtom.isDisplayed() && linkCRMCustServiceButtom.isDisplayed() && linkITDevCenterButtom.isDisplayed() &&
				   linkCRMOpenPositionButtom.isDisplayed() && linkITCenterOpenPositionButtom.isDisplayed() && linkEUProjectButtom.isDisplayed() &&
				   linkContactButtom.isDisplayed());
		Assert.assertEquals(headerlinksDisplayed && footerlinksDisplayed, true, "Error!!! One or more menu items is missing from either footer or header");
		
	}
	
	public void verifyMoreInfo(){
		boolean linkMOreInfoPresent = (linkMoreInfoITD.isDisplayed() && linkMoreInfoCRM.isDisplayed());
		Assert.assertEquals(linkMOreInfoPresent, true, "Error!!! One of the more info links is not present");
	}
	
	public void verifyLogos(){
		boolean isLogoDisplayed = arvatoLogo.isDisplayed() && euLogoEn.isDisplayed();
		Assert.assertEquals(isLogoDisplayed, true, "Error!!! One of the more logo is not present");
	}
	
	public void verifyLanguageLinks(){
		boolean isLangLinkPresent = linkLangEN.isDisplayed() && linkLangEE.isDisplayed();
		Assert.assertEquals(isLangLinkPresent, true, "Error!!! One of the more logo is not present");
	}
	
	public void checkIfLinksWorking(){
		navigateAndVerify(MenuType.HOME);
		//click Home and make sure you are in home page
		navigateAndVerify(MenuType.HOME);
		//click CRM and customer service and make sure you are in that page
		navigateAndVerify(MenuType.CRM_CUST);
		//click ITD and make sure in you are in ITD
		navigateAndVerify(MenuType.ITD);
		//click CRM open positions and make sure you are in that page
		navigateAndVerify(MenuType.CRM_OPEN);
		//click IT open positions and make sure you are in that page
		navigateAndVerify(MenuType.IT_OPEN);
		//click EU and make sure you are in EU
		navigateAndVerify(MenuType.EU);
		//click contacts and make sure you are in contacts
		navigateAndVerify(MenuType.CONTACT);
		//click CRM & ITD more info and make sure you are in that page
		verifyMoreInfoLink();
	}
	
	public void navigateAndVerify(MenuType menuItem){
		switch (menuItem) {
			case HOME: 
				linkHomeTop.click();
				verifyMoreInfo();
				linkHomeButtom.click();
				verifyMoreInfo();
			break;

			case CRM_CUST: 
				linkCRMCustServiceTop.click();
				//verify CRM Customer service page
				crmCustomerServicePage.verifyCRMCustServicePage();
				navigateBack();
				linkCRMCustServiceButtom.click();
				//verify CRM Customer service page
				crmCustomerServicePage.verifyCRMCustServicePage();
				navigateBack();
				verifyMoreInfo();
			break;
				
			case ITD: 
				linkITDevCenterTop.click();
				//verify ITD page
				itDevCenterPage.verifyITDPage();
				navigateBack();
				linkITDevCenterButtom.click();
				//verify ITD page
				itDevCenterPage.verifyITDPage();
				navigateBack();
				verifyMoreInfo();
			break;
				
			case CRM_OPEN: 
				linkCRMOpenPositionTop.click();
				//verify CRM Open page
				crmOpenPositionPage.verifyCRMOPenPositionPage();
				navigateBack();
				linkCRMOpenPositionButtom.click();
				//verify CRM Open page
				crmOpenPositionPage.verifyCRMOPenPositionPage();
				navigateBack();
				verifyMoreInfo();
			break;
				
			case IT_OPEN: 
				linkITCenterOpenPositionTop.click();
				//verify IT Open page
				itCenterOpenPositionPage.verifyITOPenPositionPage();
				navigateBack();
				linkITCenterOpenPositionButtom.click();
				//verify IT Open page
				itCenterOpenPositionPage.verifyITOPenPositionPage();
				navigateBack();
				verifyMoreInfo();
			break;
				
			case EU: 
				linkEUProjectTop.click();
				//verify EU page
				euProjectPage.verifyEUProjectPage();
				navigateBack();
				linkEUProjectButtom.click();
				//verify EU page
				euProjectPage.verifyEUProjectPage();
				navigateBack();
				verifyMoreInfo();
			break;
				
			case CONTACT: 
				linkContactTop.click();
				//verify Contact page
				contactPage.verifyContactPage();
				navigateBack();
				linkContactButtom.click();
				//verify Contact page
				contactPage.verifyContactPage();
				navigateBack();
				verifyMoreInfo();
			break;	
				
			default:
			break;
		}
			
	}
	
	public void verifyMoreInfoLink(){
		linkMoreInfoCRM.click();
		crmCustomerServicePage.verifyCRMCustServicePage();
		navigateBack();
		verifyMoreInfo();
		
		linkMoreInfoITD.click();
		itDevCenterPage.verifyITDPage();
		navigateBack();
		verifyMoreInfo();	
	}
	
	public void navigateToMenuItem(MenuType menuItem){
		switch (menuItem) {
		case HOME: 
			linkHomeTop.click();
		break;

		case CRM_CUST: 
			linkCRMCustServiceTop.click();
		break;
			
		case ITD: 
			linkITDevCenterTop.click();
		break;
			
		case CRM_OPEN: 
			linkCRMOpenPositionTop.click();
		break;
			
		case IT_OPEN: 
			linkITCenterOpenPositionTop.click();
		break;
			
		case EU: 
			linkEUProjectTop.click();
		break;
			
		case CONTACT: 
			linkContactTop.click();
		break;	
			
		default:
		break;
	}
	}
	
	public void navigateBack(){
		driver.navigate().back();
	}
	
	
}
