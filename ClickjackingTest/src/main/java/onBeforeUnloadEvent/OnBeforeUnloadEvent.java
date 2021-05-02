package onBeforeUnloadEvent;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

public class OnBeforeUnloadEvent {
	
	public boolean onBeforeUnloadEvent() throws IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/onBeforeUnloadEventTest.html");
		driver.switchTo().frame(driver.findElementByTagName("iframe"));
		boolean result=driver.getPageSource().contains("<style");
		System.out.println("Restricted: "+result);
	    driver.quit();
	    return result ;

	}
}
