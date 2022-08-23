package com.qa.opnCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opnCart.baseTest.BaseTest;


public class ProductSearchTest extends BaseTest{

	@BeforeClass
	public void productSearchSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook", "MacBook Air"},
			{"MacBook", "MacBook Pro"},
			{"Samsung", "Samsung SyncMaster 941BW"}
		};
	}
	@DataProvider
	public Object[][] productInfoData() {
		return new Object[][] {
			{"MacBook", "MacBook Air", 4},
			{"MacBook", "MacBook Pro", 4},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
			{"iMac", "iMac", 3}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void productSearchTest(String searchKey, String productName) {
		searchResPage=accPage.doSearch(searchKey);
		prodInfoPage=searchResPage.selectProduct(productName);
	 	String actProductHeaderName=prodInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}
	@Test(dataProvider="productInfoData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchResPage=accPage.doSearch(searchKey);
		prodInfoPage=searchResPage.selectProduct(productName);
		int actualImagesCount=prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);	
	}
	@Test
	public void productDataTest() {
		searchResPage=accPage.doSearch("MacBook");
		prodInfoPage=searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo=prodInfoPage.getProductInfo();
		
		
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfo.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfo.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("extaxPrice"), "Ex Tax: $2,000.00");
		softAssert.assertAll();
	}
	
	
}
	

