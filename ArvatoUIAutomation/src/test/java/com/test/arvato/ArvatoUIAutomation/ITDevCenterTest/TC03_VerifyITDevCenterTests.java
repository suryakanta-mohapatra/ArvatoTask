package com.test.arvato.ArvatoUIAutomation.ITDevCenterTest;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.pages.ITDevCenterPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC03_VerifyITDevCenterTests extends TestBase {

	FrontPage frontPage;
	ITDevCenterPage  itDevCenterPage;
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
		itDevCenterPage = new ITDevCenterPage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC03_VerifyITDevCenterTests");
		initialize();
	}
	
	@Test(enabled = true, priority=1)
	public void verifyITDevCenterPage(){
		frontPage.navigateToMenuItem(FrontPage.MenuType.ITD);
		itDevCenterPage.verifyITDPage();
		itDevCenterPage.verifyOpenPositionLink();
	}
	
}
