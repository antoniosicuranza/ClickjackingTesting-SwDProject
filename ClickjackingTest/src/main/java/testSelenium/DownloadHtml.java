package testSelenium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class DownloadHtml {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/ClickjackingTest/disablingJavascriptTest2.html");
		//int size = driver.findElements(By.tagName("iframe")).size();
		driver.switchTo().frame(driver.findElementByTagName("iframe"));
		System.out.println(driver.getPageSource());
	}

}
