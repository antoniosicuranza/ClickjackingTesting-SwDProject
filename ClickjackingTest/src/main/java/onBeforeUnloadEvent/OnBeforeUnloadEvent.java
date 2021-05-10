package onBeforeUnloadEvent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

public class OnBeforeUnloadEvent {
	
	public boolean onBeforeUnloadEvent() throws IOException {
		boolean result=true;
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/onBeforeUnloadEventTest.html");
		try {
			driver.switchTo().frame(driver.findElementByTagName("iframe"));
			 driver.findElementByTagName("style");
		}catch (NoSuchElementException exception) {
			result = false;
		}		
		System.out.println("onBeforeUnload: "+result);
	    driver.quit();
	    return result ;

	}
}
