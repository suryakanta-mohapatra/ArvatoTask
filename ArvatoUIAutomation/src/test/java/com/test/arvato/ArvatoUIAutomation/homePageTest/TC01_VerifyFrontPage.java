package com.test.arvato.ArvatoUIAutomation.homePageTest;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC01_VerifyFrontPage extends TestBase {

	public static final Logger log = Logger.getLogger(TC01_VerifyPage.class.getName());
	
	FrontPage frontPage;
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC01_VerifyFrontPage");
		initialize();
	}
	
	
	@Test(enabled = true, priority=1)
	public void verifyFrontPageUIElements(){
		frontPage.verifyHeaderAndFooterMenu();
		frontPage.verifyMoreInfo();
		frontPage.verifyLogos();
		frontPage.verifyLanguageLinks();
	}
	
	
	@Test(enabled = true, priority=2)
	public void verifyMoreInfoLinks(){
		frontPage.verifyMoreInfoLink();		
	}
	
	@Test(enabled = true, priority=3)
	public void verifyFrontPageLinks(){
		changeToEnglishIfEE();
		frontPage.checkIfLinksWorking();
	}
}

