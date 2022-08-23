package com.qa.openCart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		//act=new Actions(driver);
	}
	public void doSendKeys(By locator,String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	public void doSendKeys(String locatorType, String selector,String value)
	{
		By locator=getBy(locatorType,selector);
		getElement(locator).sendKeys(value);
		
	}
	public WebElement getElement(By locator){
		return driver.findElement(locator);
	}
	public void doClick(By locator) {
		getElement(locator).click();
	}
	public void doClick(String locatorType, String selector)
	{
		By locator=getBy(locatorType,selector);
		getElement(locator).click();
	}
	public String doElementGetText(By locator)
	{
		return getElement(locator).getText();
	}
	public By getBy(String locatorType,String selector) {
		By locator=null;
		switch(locatorType) {
		case  "id":
			locator=By.id(selector);
			break;
		case  "name":
			locator=By.name(selector);
			break;
		case  "class":
			locator=By.className(selector);
			break;
		case  "xpath":
			locator=By.xpath(selector);
			break;
		case  "cssselector":
			locator=By.cssSelector(selector);
			break;
		case  "linktext":
			locator=By.linkText(selector);
			break;
		case  "partiallinktext":
			locator=By.partialLinkText(selector);
			break;
		case  "tagname":
			locator=By.tagName(selector);
			break;
		default:
			break;
		}
		return locator;
	}
	public int getEmptyElementTextList(By locator) {
		int totalLinks=getPageElementsCount(locator);
		int totalNonEmptyLinks=getElementsTextCount(locator);
		return (totalLinks-totalNonEmptyLinks);
		
	}
	public int getElementsTextCount(By locator) {
	
		return getElementsTextList(locator).size();
	}
	public List <String> getElementsTextList(By locator) {
		List <WebElement> eleList = getElements(locator);
		List <String> eleTextList = new ArrayList <String> ();
		for(WebElement e : eleList) {
			String text=e.getText();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	public int getPageElementsCount(By locator) {
		return getElements(locator).size();
	}
	public List <WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public List <String> getEleAttributeList(By locator, String attributeName) {
		List<WebElement> eleList=getElements(locator);
		List <String> attributeList= new ArrayList<String> ();
		for(WebElement e:eleList) {
			String attrText=e.getAttribute("href");
			attributeList.add(attrText);
		}
		return attributeList;
		
	}
	public void performSearch(By searchBoxLocator,By suggListLocator, String searchKey,String SuggName) throws InterruptedException  {
		doSendKeys(searchBoxLocator,searchKey);
		Thread.sleep(3000);
		List <WebElement> suggList=getElements(suggListLocator);
		System.out.println("Total items in sugestion list are "+suggList.size());
		for(WebElement e:suggList) {
			String text=e.getText();
			System.out.println(text);
			if (text.contains(SuggName))
			{
				e.click();
				break;
			}
		}
	}
    public void selectItem(String prodName) {
		
		By locator=By.xpath("//li[text()='"+prodName+"']");
		doClick(locator);
	}
    
    
    //**************************Element displayed util**************************//
    public boolean doIsDisplayed(By locator) {
    	return getElement(locator).isDisplayed();
    }
    public int getElementsCount(By locator) {
    	return getElements(locator).size();
    }
    public boolean checkSingleElementExist(By locator) {
    	if (getElementsCount(locator)==1) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public boolean checkElementMultipleExist(By locator) {
    	if (getElementsCount(locator)>1) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    //*************************Select tag based dropdown util***************************
    public void doSelectByIndex(By countryLocator, int index) {
		Select select=new Select(getElement(countryLocator));
		select.selectByIndex(index);
		
	}
	public void doSelectByVisibleText(By countryLocator, String visibleText) {
		Select select=new Select(getElement(countryLocator));
		select.selectByVisibleText(visibleText);
		
	}
	public void doSelectByValue(By countryLocator, String value) {
		Select select=new Select(getElement(countryLocator));
		select.selectByValue(value);
		
	}
	public int getDropDownCount(By countryLocator) {
		Select select=new Select(getElement(countryLocator));
		List<WebElement> countryList=select.getOptions();
		return countryList.size();
	}
	public List<String> getDropdownValuesList(By countryLocator) {
		Select select=new Select(getElement(countryLocator));
		List <WebElement> optionsList=select.getOptions();
		List <String> dropdownValues=new ArrayList<String>();
		for (WebElement e:optionsList) {
			String text=e.getText();
			dropdownValues.add(text);
		}
		return dropdownValues;
		
	}
	public void doSelectValueFromDropDown(By countryLocator, String value) {
		List<WebElement> dropdownList=getElements(countryLocator);
		for(WebElement e : dropdownList) {
			String text=e.getText();
			System.out.println(text);
			if (text.equals("India")) {
				e.click();
				break;
			}
		}
	}
	public void doSelectValueUsingOptions(By countryLocator) {
		Select select=new Select(getElement(countryLocator));
		List<WebElement> dropdownList=select.getOptions();
		for(WebElement e : dropdownList) {
			String text=e.getText();
			if (text.equals("India")) {
				e.click();
				break;
			}
		}
	}
	/**************with wait utilities********************/
	public void waitforFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));	
	}
	public void waitforFrameByName(int timeOut,String frameName) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));	
	}
	public void waitforFrameById(int timeOut, int frameId) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));	
	}
	public void waitforFrameByWebelememt(int timeOut, By frameLocator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElement(frameLocator)));	
	}
	public String waitForTitleContains(int timeOut,String titleFraction) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		else {
			System.out.println("Title not found");
			return null;
		}
		
	}
	public String waitForTitleToBe(int timeOut,String title) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		else {
			System.out.println("Title not found");
			return null;
		}
		
	}

    public String waitForUrl(int timeOut,String fractionValue) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(fractionValue))) {
			return driver.getCurrentUrl();
		}
		else
			return null;
		
	}
    public String waitForUrlToBe(int timeOut,String urlValue) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		}
		else
			return null;
		
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}
	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}
	public void sendKeysToAlert(String value,int timeOut) {
		waitForAlert(timeOut).sendKeys(value);
	}
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public void doSendKeysWithWait(String value,By locator, int timeOut) {
		WebElement ele=waitForElementVisible(locator,timeOut);
		ele.clear();
		ele.sendKeys(value);
		
	}
	public void doClickWithWait(By locator, int timeOut) {
		waitForElementPresence(locator,timeOut).click();
	}
    public WebElement waitForElementVisible(By locator, int timeOut) {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitForElementPresence(By locator, int timeOut) {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void clickWhenReady(By locator,int timeOut) {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
    	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
    }
    public void clickElementWhenReady(By locator,int timeOut) {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
    	wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();;
    }
    public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut) {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public void printAllElementsText(By locator,int timeOut) {
    	List <WebElement> eleList=waitForElementsToBeVisible(locator,timeOut);
    	for(WebElement e: eleList) {
			System.out.println(e.getText());
		}
    }
    /*public List<String> printAllElementsTextList(By locator,int timeOut) {
    	List <WebElement> eleList=waitForElementsToBeVisible(locator,timeOut);
    	List <String> eleTextList=new ArrayList<String>();
    	for(WebElement e: eleList) {
			String text=e.getText();
			eleTextList.add(text);
		}
    	return eleTextList;
    }*/
    public List<String> getElementsTextList(By locator,int timeOut) {
    	List <WebElement> eleList=waitForElementsToBeVisible(locator,timeOut);
    	List <String> eleTextList=new ArrayList<String>();
    	for(WebElement e: eleList) {
			String text=e.getText();
			eleTextList.add(text);
		}
    	return eleTextList;
    }
    /****************utilities with polling time**********/
    public WebElement waitForElementVisibleWithPollingTime(By locator,int timeOut, int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	
    }
    public WebElement waitForElementPresenceWithPollingTimeit(By locator, int timeOut, int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void doSendKeysWithPollingTime(String value,By locator, int timeOut,int pollingTime) {
    	
    	waitForElementVisibleWithFluentWait(locator,timeOut,pollingTime).sendKeys(value);
	}
    public void clickWhenReadyWithPollingTime(By locator,int timeOut,int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
    }
    public void clickElementWhenReadyWithPollingTime(By locator,int timeOut,int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();;
    }
    public void doClickWithPollingTimeait(By locator, int timeOut,int pollingTime) {
    	waitForElementPresenceWithFluentWait(locator,timeOut,pollingTime).click();
	}
    /****************utilities with FluentWait************/
    public WebElement waitForElementVisibleWithFluentWait(By locator,int timeOut, int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	
    }
    public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void doSendKeysWithFluentWait(String value,By locator, int timeOut,int pollingTime) {
    	
    	waitForElementVisibleWithFluentWait(locator,timeOut,pollingTime).sendKeys(value);
	}
    public void clickWhenReadyWithFluentWait(By locator,int timeOut,int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();;
    }
    public void clickElementWhenReadyWithFluentWait(By locator,int timeOut,int pollingTime) {
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
    			.withTimeout(Duration.ofSeconds(timeOut))
    			.pollingEvery(Duration.ofSeconds(pollingTime))
    			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
    			.withMessage("Element is not found");
    	wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();;
    }
    public void doClickWithFluentWait(By locator, int timeOut,int pollingTime) {
    	waitForElementPresenceWithFluentWait(locator,timeOut,pollingTime).click();
	}
    /*************custom wait****************/
    public static void shortWait() {
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void mediumWait() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void longWait() {
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void waitFor(int timeOut) {
		try {
			Thread.sleep(timeOut*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
    public WebElement retryingElementByCustomWait(By locator, int timeOut,int pollingTime) {
		WebElement ele=null;
		int attempts=0;
		while(attempts<timeOut) {
			try {
				ele=getElement(locator);
				break;
			}
			catch(Exception e) {
				System.out.println("Element is not found in attempt number:"+attempts);
				try {
					Thread.sleep(pollingTime);
				}
				catch(InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if(ele==null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			}
			catch(Exception e3) {
				System.out.println("Element not found exception..tried for"+timeOut+" times with interval of "+pollingTime);
			}
		}
		return ele;
			
	}
    
    public void waitForPageLoad(int timeOut) {
		long endTime=System.currentTimeMillis()+timeOut;
		while(System.currentTimeMillis()<endTime) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String pageState=js.executeScript("return document.readyState;").toString();
			System.out.println("Page loading state is: "+pageState);
			if(pageState.equals("complete")) {
				System.out.println("page is fully loaded with all scripts, images,css ...");
				break;
			}
		}
	}
    
}
