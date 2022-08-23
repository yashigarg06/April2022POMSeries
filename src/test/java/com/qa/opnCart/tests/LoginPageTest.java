package com.qa.opnCart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.openCart.constants.AppConstants;
import com.qa.opnCart.baseTest.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: Design the login page feature for open cart")
@Story("US - 101: design the login pagefeatures with login, forgot pwd, title, url")
public class LoginPageTest extends BaseTest{

	
	@Test
	@Description("TC_01: login page title test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	@Test
	@Description("TC_02: login page url test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl=loginPage.getLoginPageUrl();
		Assert.assertEquals(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),true);
	}
	@Test
	@Description("TC_03: verify forgot pwd link exist on login page")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkExistTest() {
		Assert.assertEquals(loginPage.isForgotPwdLinkExist(), true);
		
	}
	@Test
	@Description("TC_04: verify user is able to login with correct username and password on login page")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
        accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test(enabled = false)
	public void loginPageFooterTest() {
		System.out.println("login page footer test");
	}
}
