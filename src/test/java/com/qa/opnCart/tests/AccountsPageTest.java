package com.qa.opnCart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.openCart.constants.AppConstants;
import com.qa.opnCart.baseTest.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;


@Epic("Epic - 200: Design the accounts page feature for open cart")
@Story("US - 201: design the accounts page features")
public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertEquals(accPage.isLogoutLinkExist(), true);
	}
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test
	public void accPageHeaderTest() {
		List<String> actSecHeaderList=accPage.getAccountSectionsHeaderList();
		System.out.println("==========Actual Headers======"+actSecHeaderList);
		Assert.assertEquals(actSecHeaderList, AppConstants.EXPECTED_ACCOUNT_HEADERS_LIST);
	}
	
}
