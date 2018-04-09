package com.test.arvato.ArvatoUIAutomation.CRMCustServiceTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.arvato.ArvatoUIAutomation.homePageTest.TC01_VerifyPage;
import com.test.arvato.ArvatoUIAutomation.pages.CRMCustomerServicePage;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC02_VerifyCRMCustomerService extends TestBase {

public static final Logger log = Logger.getLogger(TC01_VerifyPage.class.getName());
	
	FrontPage frontPage;
	CRMCustomerServicePage  crmCustomerServicePage;
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
		crmCustomerServicePage = new CRMCustomerServicePage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC02_VerifyCRMCustomerService");
		initialize();
	}
	
	@Test(enabled = true, priority=1)
	public void verifyCRMCustServicePageUIElements(){
		frontPage.navigateToMenuItem(FrontPage.MenuType.CRM_CUST);
		crmCustomerServicePage.verifyAllLink();
	}
	
	@Test(enabled = true, priority=2)
	public void verifyLinkToCRMOpenPosition(){
		crmCustomerServicePage.verifyOpenPositionLink();
	}
	
}
