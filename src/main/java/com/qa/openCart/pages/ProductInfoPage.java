package com.qa.openCart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//1.By locators
	private By productHeader=By.cssSelector("div#content h1");
	private By productImages=By.cssSelector("ul.thumbnails li img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap; 
	
    
	//2.Page constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3.Page actions
	public String getProductHeaderValue() {
		
		String prodHeaderVal=eleUtil.doElementGetText(productHeader);
		System.out.println("prod header: "+prodHeaderVal);
		return prodHeaderVal;
		
	}
	public int getProductImagesCount() {
		int imagesCount=eleUtil.waitForElementsToBeVisible(productImages, AppConstants.SMALL_DEFAULT_TIME_OUT).size();
		System.out.println("images count="+imagesCount);
		return imagesCount;
	}
	public Map<String,String> getProductInfo() {
		productMap = new LinkedHashMap<String, String>();
		
		//Add product name in Map:
		productMap.put("productname",getProductHeaderValue());
		
		getProductMetaData();
		getProductPriceData();
		System.out.println("=======actual product info==========");
		productMap.forEach((k,v) -> System.out.println(k+":"+v));
		return productMap;	
	} 
	private void getProductMetaData() {
		List<WebElement> metaDataList=eleUtil.getElements(productMetaData);
		//Product Meta data:
				//Brand: Apple
				//Product Code: Product 18
				//Reward Points: 800
				//Availability: In Stock
				for(WebElement e: metaDataList) {
					String text=e.getText();
					String meta[]=text.split(":");
					String key=meta[0].trim();
					String value=meta[1].trim();
					productMap.put(key, value);
				}
	}
	private void getProductPriceData() {
		//Product pricing data:
				//$2,000.00
				//Ex Tax: $2,000.00
				List<WebElement> metaPriceList=eleUtil.getElements(productPriceData);
				String productPrice=metaPriceList.get(0).getText().trim();
				String productExTaxPrice=metaPriceList.get(1).getText().trim(); 
				productMap.put("productprice", productPrice);
				productMap.put("extaxPrice", productExTaxPrice);			
	}
}
