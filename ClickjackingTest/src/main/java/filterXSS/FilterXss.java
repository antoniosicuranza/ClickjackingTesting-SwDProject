package filterXSS;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class FilterXss {
	public boolean filterChrome() throws IOException {
		boolean result = true;
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");	

		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/filter_xss_chrome.html");
		try {
			driver.switchTo().frame(driver.findElementByTagName("iframe"));
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}
		System.out.println("Restricted: "+result);
	    driver.quit();
	    return result;

	}
	
	public boolean filterIE8() throws IOException {
		boolean result = true;
		System.setProperty("webdriver.ie.driver", "C:\\browserdrivers\\IEDriverServer.exe");	

		InternetExplorerDriver driver = new InternetExplorerDriver();
		driver.get("http://localhost:8080/ClickjackingTest/filter_xss_IE8.html");
		try {
			driver.switchTo().frame(driver.findElementByTagName("iframe"));
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}
		System.out.println("Restricted: "+result);
	    driver.quit();
	    return result;
	}
}
