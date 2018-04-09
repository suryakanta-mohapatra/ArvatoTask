package com.test.arvato.ArvatoUIAutomation.ContactsPageTests;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.arvato.ArvatoUIAutomation.pages.ContactPage;
import com.test.arvato.ArvatoUIAutomation.pages.FrontPage;
import com.test.arvato.ArvatoUIAutomation.testBase.TestBase;

public class TC06_VerifyContactPage extends TestBase {

	FrontPage frontPage;
	ContactPage  contactPage;
	
	@DataProvider(name="contactForm")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData_1.xlsx", "Validation");
		return testRecords;
	}
	
	
	@BeforeClass
	public void beforeClass(){
		frontPage = new FrontPage(driver);
		contactPage = new ContactPage(driver);
	}
	
	@BeforeTest
	public void setUp() throws IOException{
		System.out.println("Inside SetUp - TC02_VerifyCRMCustomerService");
		initialize();
	}
	
	@Test(enabled = true, priority=1)
	public void verifyUIElements(){
		frontPage.navigateToMenuItem(FrontPage.MenuType.CONTACT);
		contactPage.verifyAllElements();		
	}
	
	@Test(enabled = true, priority=2, dataProvider="contactForm")
	public void validateFields(String name, String company, String email, String phone, String message, String expectedError){
		contactPage.validateFields(name, company, email, phone, message, expectedError);	
	}
	
}
