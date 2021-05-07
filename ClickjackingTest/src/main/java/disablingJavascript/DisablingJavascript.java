package disablingJavascript;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.HttpURLConnection;

public class DisablingJavascript {
	
	
	public boolean disablingJavascriptRun() throws IOException {
	return securityRestricted() && sandbox();
	}
	
	public boolean securityRestricted() throws IOException {
		boolean result=true;
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/disablingJavascriptTest.html");
		driver.switchTo().frame(driver.findElementByTagName("iframe"));
		try {
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}		
		System.out.println("Restricted: "+result);
	    driver.quit();
	    return result ;

	}
	
	public boolean sandbox() throws IOException {
		boolean result=true;
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/disablingJavascriptTest2.html");
		driver.switchTo().frame(driver.findElementByTagName("iframe"));
		try {
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}
		System.out.println("Sandbox: "+result);
	    driver.quit();
	    return result ;
	}
	
	
}
