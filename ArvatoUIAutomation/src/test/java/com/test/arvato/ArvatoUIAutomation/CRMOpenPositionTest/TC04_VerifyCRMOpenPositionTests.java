package com.test.arvato.ArvatoUIAutomation.CRMOpenPositionTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.arvato.ArvatoUIAutomation.pages.CRMOpenPositionPage;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;
public class TC04_VerifyCRMOpenPositionTests extends TestBase {

	FrontPage frontPage;
	CRMOpenPositionPage  crmOpenPositionPage;
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
		crmOpenPositionPage = new CRMOpenPositionPage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC04_VerifyCRMOpenPositionTests");
		initialize();
	}
	
	@Test(enabled = true, priority=1)
	public void verifyCRMOpenPositionLinks() throws UnsupportedEncodingException{
		frontPage.navigateToMenuItem(FrontPage.MenuType.CRM_OPEN);
		crmOpenPositionPage.verifyCRMOPenPositionPage();
		crmOpenPositionPage.verifyAllLinksWorking();
		
	}
	
	
}
