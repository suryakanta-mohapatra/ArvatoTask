package com.test.arvato.ArvatoUIAutomation.homePageTest;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.arvato.ArvatoUIAutomation.pages.ContactPage;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.pages.HomePage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC01_VerifyPage extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC01_VerifyPage.class.getName());
	HomePage homePage;
	FrontPage f;
	ContactPage p;
	
	@DataProvider(name="testData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData_1.xlsx", "Validation");
		return testRecords;
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp- TC01_VerifyPage");
		initialize();
	}
	
	@Test(enabled = true)
	public void verifyHomePageLinks(){
		f = new FrontPage(driver);
		p = new ContactPage(driver);
		changeToEnglishIfEE();
		f.checkIfLinksWorking();
	}
	
	
	@Test(enabled = false)
	public void verifyTitle(){
		log.info("********Starting Test for TC01_VerifyPage->verifyTitle()********");
		homePage = new HomePage(driver);
		homePage.verifyLogo();
		log.info("********Finished Test for TC01_VerifyPage->verifyTitle()********");
	}
	
}
