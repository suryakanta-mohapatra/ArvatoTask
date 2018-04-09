package com.test.arvato.ArvatoUIAutomation.testBase;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.imgscalr.Scalr;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.arvato.ArvatoUIAutomation.excelReader.ExcelReader;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public WebDriver driver;
	ExcelReader excelReader;
	Properties property;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	
	
	static{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extentReport = new ExtentReports(System.getProperty("user.dir")+"/Reports/TestReport"+format.format(calender.getTime())+".html",false);
	}
	
	public void loadData() throws IOException{
		property = new Properties();
		File file = new File(System.getProperty("user.dir")+"/configuration/config.properties");
		FileInputStream fis = new FileInputStream(file);
		property.load(fis);
	}
	
	public void initialize() throws IOException {
		loadData();
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		selectBrowser(property.getProperty("browser"));
		openUrl(property.getProperty("url"));	
	}

	public void openUrl(String url) {
		log.info("Navigating to URL : "+url);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}

	public void selectBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")){
			//For MacOS
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
			log.info("Creating instance of "+browser+" browser");
			//For Windows
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}	
	}
	
	public String[][] getData(String excelFileName, String sheetName){
		String path = System.getProperty("user.dir")+"/data/"+excelFileName;
		excelReader = new ExcelReader(path);
		String[][] data = excelReader.getDataFromSheet(excelFileName, sheetName);
		return data;
	}
	
	public void waitForElement(WebElement element, int Timeout){
		WebDriverWait wait = new WebDriverWait(driver, Timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void getScreenshot(WebDriver driver, String name){
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/Screenshot/";
			File destFile = new File(screenshotDirectory+name+"_"+format.format(calender.getTime())+".jpeg");
			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+ "'><img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver(){
		return driver;
	}

	public void closeDriver(){
		driver.quit();
		log.info("Browser closed");
		extentReport.endTest(extentTest);
		extentReport.flush();
	}

	public String getScreenshotPath(WebDriver driver, String name){
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = null;
		try {
			String screenshotDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/Screenshot/";
			destFile = new File(screenshotDirectory+name+"_"+format.format(calender.getTime())+".jpg");
			BufferedImage originalImage = ImageIO.read(srcFile);
			originalImage= Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 1274, 633);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
	        byte[] imageInByte = baos.toByteArray();
	        baos.close();
	        FileUtils.writeByteArrayToFile(destFile, imageInByte);
			Reporter.log("<a href='"+destFile.getAbsolutePath()+ "'><img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/></a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, result.getName()+" Test is Passed");
		}else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, result.getName()+" Test is Skipped. Reason is : "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.FAILURE){
			extentTest.log(LogStatus.ERROR, result.getName()+" Test is Failed. Reason is : "+result.getThrowable());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(getScreenshotPath(driver, "Fail_EXTNT_"+result.getName())));
		}else if(result.getStatus() == ITestResult.STARTED){
			extentTest.log(LogStatus.INFO, result.getName()+" Test is Started");
		}
	}
	
	public void changeToEnglishIfEE(){
		String xpathInEE = "(//a[text()='Avaleht'])[1]";
		String xpathEN = "//a[text()='en']";
		String xpathInEN = "(//a[contains(text(),'Home')])[1]";
		boolean isEE = driver.findElements(By.xpath(xpathInEE)).size()>0;
		if(isEE){
			WebElement elementEN = driver.findElement(By.xpath(xpathEN));
			elementEN.click();
			WebElement elementInEn = driver.findElement(By.xpath(xpathInEN));
			wait(elementInEn);
		}
		
	}
	
	public void wait(WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	@BeforeMethod
	public void beforeMethod(Method result){
		extentTest = extentReport.startTest(result.getName());
		extentTest.log(LogStatus.INFO, result.getName()+" test started");
	}

	@AfterMethod
	public void afterMethod(ITestResult result){
		getResult(result); 
	}

	@AfterClass(alwaysRun=true)
	public void endTest(){
		closeDriver();
	}

}
