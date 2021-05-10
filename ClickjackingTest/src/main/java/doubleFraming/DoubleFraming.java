package doubleFraming;

import java.io.IOException;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class DoubleFraming {
	public boolean doubleCalling() throws IOException {
		return calling();
	}
	
	public boolean calling() throws IOException {
		boolean result = true;
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/fictitious2.html");
		try {
			driver.switchTo().frame(driver.findElementByTagName("iframe"));
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}
		System.out.println("Restricted: "+result);
	    driver.quit();
	    return result ;
		
	}
}
