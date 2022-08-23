package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.By locators
    By productCount =By.cssSelector("div.product-thumb");
    
    
	//2.Page constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}

	//3.Page actions:
	public int getSearchProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();
		
	}
	public ProductInfoPage selectProduct(String searchProductName) {
		By product=By.linkText(searchProductName);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
	}
	
	
	
}
