package com.test.arvato.ArvatoUIAutomation.pages;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
public class JobDescriptionPage {

	
	public static final Logger log = Logger.getLogger(JobDescriptionPage.class.getName());
	WebDriver driver;
	
	public JobDescriptionPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(text(),'Send Your CV')]//following-sibling::a/b[text()='cv@arvato.ee']")
	WebElement contactEmail;
	
	@FindBy(xpath="//*[contains(text(),'+372 6100 307')]")
	WebElement contactPhone;
	
	@FindBy(xpath="(//*[contains(text(),'looking')]/following-sibling::p)[1]")
	WebElement genericPosition;
	
	public String getPositionTitle() throws UnsupportedEncodingException{
		String beforeDecoding = genericPosition.getText().toString();
		String afterDecoding = URLDecoder.decode(beforeDecoding, "UTF-8");
		//return genericPosition.getText().toString();	
		return afterDecoding;
	}
	
	
}
