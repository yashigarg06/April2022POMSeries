package com.qa.opnCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opnCart.baseTest.BaseTest;



public class RegisterTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		regPage=loginPage.goToRegisterPage();
	}
	@DataProvider
	public Object[][] getRegData() {
		return new Object[][] {
				{"sam112", "automation11", "sam11auto@gmail.com", "756866666", "sam112@123", "yes"},
				{"tom12", "automation12", "tom1auto@gmail.com", "788336666", "tom12@123", "yes"},
				{"raj12", "automation12", "raj1auto@gmail.com", "756466666", "raj12@123", "no"}
		};
	}
	@Test(dataProvider="getRegData")
	public void userRegTest(String firstName, String lastName, String email, String phone, String password, String subscribe) {
		boolean successFlag=regPage.userRegistration(firstName, lastName, email, phone, password, subscribe);
		Assert.assertEquals(successFlag, true);
	}
}
