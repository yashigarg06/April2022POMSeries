package com.qa.openCart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//1. By locator
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By accPageHeaders= By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");
	
	//2.constructor
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3.page actions
	public String getAccountPageTitle() {
		String title=eleUtil.waitForTitleToBe( AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE);
		System.out.println("Account page title is: "+title);
		return title;
	}
	public String getAccountPageUrl() {
		String url=eleUtil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("Account page url is: "+url);
		return url;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}
	public List<String> getAccountSectionsHeaderList() {
		return eleUtil.getElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}
	//Common Page actions:
	public SearchResultsPage doSearch(String productName) {
		System.out.println("searching for: "+ productName);
		eleUtil.doSendKeysWithWait(productName, searchField, AppConstants.SMALL_DEFAULT_TIME_OUT);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
	
}
