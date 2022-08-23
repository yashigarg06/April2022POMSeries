package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By conPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public boolean userRegistration(String firstName, String lastName, String email, String phone, String password, String subscribe) {
		eleUtil.doSendKeysWithWait(firstName, this.firstName, AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, phone);
		eleUtil.doSendKeys(this.pwd, password);
		eleUtil.doSendKeys(this.conPwd, password);
		
		if(subscribe.equalsIgnoreCase("yes")){
			eleUtil.doClick(this.subscribeYes);
		}
		else
		{
			eleUtil.doClick(this.subscribeNo);
		}
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueBtn);
		
		String actSuccessMsg = eleUtil.waitForElementVisible(this.successMsg, AppConstants.MEDIUM_DEFAULT_TIME_OUT).getText();
		if (actSuccessMsg.contains(AppConstants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(this.logoutLink);
			eleUtil.doClick(this.registerLink);
			return true;
		}else {
			return false;
		}
	}
	
}
