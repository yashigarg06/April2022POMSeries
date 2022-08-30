package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By locator
	private By emailId= By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPwdLink= By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3. Page Actions
	@Step("getting login page title on the login page")
	public String getLoginPageTitle() {
		String title=eleUtil.waitForTitleToBe( AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login page title is: "+title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		String url=eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println("Login page url is: "+url);
		return url;
	}
	
	@Step("is Forgot Pwd Link Exist or not")
	public boolean isForgotPwdLinkExist() {
		System.out.println("checking forgot pwd link exist");
		return eleUtil.waitForElementPresence(forgotPwdLink,AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}
	
	@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		System.out.println("app creds: "+userName+": "+pwd);
		eleUtil.doSendKeysWithWait(userName, emailId, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//return eleUtil.waitForTitleToBe(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE);
		return new AccountsPage(driver);
		
	}
	
	@Step("perform search for the product: {0}")
	public SearchResultsPage performSearch(String name) {
		System.out.println("product name is:"+ name);
		AccountsPage accPage = new AccountsPage(driver);
		return accPage.doSearch(name);
	}
	
	public RegisterPage goToRegisterPage() {
		System.out.println("navigating to registration page");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
}
