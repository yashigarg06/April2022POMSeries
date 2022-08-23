package com.qa.openCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author prate
 *
 */
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	/**
	 * this method is used to initialize the driver on the basis of giver browsername
	 * @param browserName
	 * @return this method returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName=prop.getProperty("browser");
		System.out.println("browser name is :"+ browserName);
		optionsManager=new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}else
			if(browserName.equalsIgnoreCase("safari")) {
				WebDriverManager.safaridriver().setup();
				tlDriver.set(new SafariDriver());
			}else
				if(browserName.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				}else
					if(browserName.equalsIgnoreCase("edge")) {
						WebDriverManager.edgedriver().setup();
						tlDriver.set(new EdgeDriver());
					}else
					     {
						    System.out.println("Please pass right browser name "+ browserName );
					     }
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * 
	 * @return this returns properties reference with all config properties
	 */
	
	public Properties initProp() {
	    prop=new Properties();
	    FileInputStream ip = null;
	    //command line arg --> maven
	    //mvn clean install -- -Denv="stage" -Dbrowser="chrome"
	    String envName = System.getProperty("env");
	    System.out.println("Running test cases on environment:" + envName);
	    if(envName == null) {
	    	System.out.println("No env is given.. hence running it on QA env by default");
	    	try {
				ip = new FileInputStream("./src/testresources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    }
	    else 
	    {
	    	try {
	    	switch(envName) {
	    	case "qa":
	    		ip = new FileInputStream("./src/testresources/config/qa.config.properties");
	    		break;
	    	case "stage":
	    		ip = new FileInputStream("./src/testresources/config/stage.config.properties");
	    		break;
	    	case "div":
	    		ip = new FileInputStream("./src/testresources/config/div.config.properties");
	    		break;
	    	case "prod":
	    		ip = new FileInputStream("./src/testresources/config/config.properties");
	    		break;
	    	case "uat":
	    		ip = new FileInputStream("./src/testresources/config/uat.config.properties");
	    		break;
	    	default:
	    		System.out.println("Please pass the right environment.."+ envName);
	    	}
	    	}catch(FileNotFoundException e) {
	    		e.printStackTrace();
	    	}
	    }
	    try 
	    {
	    	prop.load(ip);
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
		return prop;
	}
	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir") + "./screenshot"+methodName+".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
