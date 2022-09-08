package com.qa.opnCart.baseTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.pages.AccountsPage;
import com.qa.openCart.pages.LoginPage;
import com.qa.openCart.pages.ProductInfoPage;
import com.qa.openCart.pages.RegisterPage;
import com.qa.openCart.pages.SearchResultsPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public DriverFactory df;
	public LoginPage loginPage;
	public AccountsPage  accPage;
	public SearchResultsPage searchResPage;
	public ProductInfoPage prodInfoPage;
	public RegisterPage regPage;
	
	public SoftAssert softAssert;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setup(String browserName, String browserVersion) {
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
		
	}
	
	@AfterTest
	public void tearDown() {
		//driver.quit();
	}
}
