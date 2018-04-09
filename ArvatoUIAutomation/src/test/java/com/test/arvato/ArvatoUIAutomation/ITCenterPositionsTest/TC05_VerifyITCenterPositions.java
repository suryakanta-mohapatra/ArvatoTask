package com.test.arvato.ArvatoUIAutomation.ITCenterPositionsTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.arvato.ArvatoUIAutomation.homePageTest.TC01_VerifyPage;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.pages.ITCenterOpenPositionPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC05_VerifyITCenterPositions extends TestBase {

public static final Logger log = Logger.getLogger(TC01_VerifyPage.class.getName());
	
	FrontPage frontPage;
	ITCenterOpenPositionPage itCenterOpenPositionPage;
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
		itCenterOpenPositionPage =  new ITCenterOpenPositionPage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC05_VerifyITCenterPositions");
		initialize();
	}
	
	@Test(enabled = true, priority=1)
	public void verifyAllUIElements(){
		frontPage.navigateToMenuItem(FrontPage.MenuType.IT_OPEN);
		itCenterOpenPositionPage.verifyITOPenPositionPage();
		itCenterOpenPositionPage.verifyAllElements();
	}
	
	@Test(enabled = true, priority=2)
	public void verifyAllPositionLinks() throws UnsupportedEncodingException{
		itCenterOpenPositionPage.verifyAllLinksWorking();	
	}
	
	
	
}
