package redefiningLocation;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class RedefiningLocation {

		public boolean redefiningLocation() throws IOException {
			boolean result=true;
			InternetExplorerDriver driver = new InternetExplorerDriver();
			driver.get("http://localhost:8080/ClickjackingTest/redefiningLocationIE.html");
			try {
				driver.switchTo().frame(driver.findElementByTagName("iframe"));
				 driver.findElementByTagName("style");
			}catch (NoSuchElementException exception) {
				result = false;
			}
			System.out.println("IE: "+result);
		    driver.quit();
		    return result ;

		}
}
