package disablingJavascript;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;

public class mainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");	
		DisablingJavascript d=new DisablingJavascript();
		System.out.println(d.disablingJavascriptRun());
	}
	
	
	/*public static String getOriginalCode(int port) throws IOException {
	ChromeDriver driver = new ChromeDriver();
	driver.get("http://localhost:"+port);
	String source=driver.getPageSource();
	driver.quit();
	return source;
}*/
	
	
}
