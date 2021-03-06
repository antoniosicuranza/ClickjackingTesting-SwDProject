package com.dependability.clickjacking.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptChrome;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptIE;
import com.dependability.clickjacking.creation.doubleFraming.Fictitious2;
import com.dependability.clickjacking.creation.filterXss.FilterXssChrome;
import com.dependability.clickjacking.creation.filterXss.FilterXssIE;
import com.dependability.clickjacking.creation.onBeforeUnloadEvent.OnBeforeUnloadEvent;
import com.dependability.clickjacking.creation.redefiningLocation.RedefiningLocation;
import com.dependability.clickjacking.file.FileCustom;
import com.dependability.clickjacking.properties.ManageProperties;
import com.dependability.connection.TestPageOk;
import com.dependability.exception.ErrorPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptChrome;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptIE;
import com.dependability.clickjacking.creation.doubleFraming.Fictitious2;
import com.dependability.clickjacking.creation.onBeforeUnloadEvent.OnBeforeUnloadEvent;
import com.dependability.clickjacking.creation.redefiningLocation.RedefiningLocation;
import com.dependability.clickjacking.file.FileCustom;
import com.dependability.clickjacking.properties.ManageProperties;
import com.dependability.connection.TestPageOk;
import com.dependability.exception.ErrorPage;

public class TestingClickJacking {

	private String url;
	private boolean[] listAttack;
	private ClickJacking[] listClickJacking;
	private int[] browser;
	private boolean[] results;
	private static int chrome = 0;
	private static int internet_explorer = 1;
	private boolean failConnection;

	public TestingClickJacking(String url, boolean[] listAttack) {
		this.listAttack = listAttack;
		this.url = url;
		listClickJacking = new ClickJacking[7];
		browser = new int[7];
		results = new boolean[7];
		failConnection = false;
	}

	public boolean[] getAttack() {
		return listAttack;
	}

	public void setAttack(boolean[] attack) {
		this.listAttack = attack;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void creationEnvironment() throws IOException {
		for (int i = 0; i < listAttack.length; i++) {
			if (listAttack[i]) {
				int idAttack = i;
				selectAttack(idAttack);
			}
		}

	}

	public void selectAttack(int idAttack) throws IOException {
		ClickJacking click;
		switch (idAttack) {
		case 0:// disablingJavascriptTest
			click = new DisablingJavascriptChrome(url);
			click.creation();
			listClickJacking[0] = click;
			browser[0] = chrome;
			break;

		case 1:// disablingJavascriptTest2
			click = new DisablingJavascriptIE(url);
			click.creation();
			listClickJacking[1] = click;
			browser[1] = internet_explorer;
			break;

		case 2:// doublFraming
			click = new Fictitious2(url);
			click.creation();
			listClickJacking[2] = click;
			browser[2] = chrome;
			break;

		case 3:// filter_xss_chrome
			click = new FilterXssChrome(url);
			click.creation();
			listClickJacking[3] = click;
			browser[3] = chrome;
			break;

		case 4:// filter_xss_IE8
			click = new FilterXssIE(url);
			click.creation();
			listClickJacking[4] = click;
			browser[4] = internet_explorer;
			break;

		case 5:// onBeforeUnloadEventTest
			click = new OnBeforeUnloadEvent(url);
			click.creation();
			listClickJacking[5] = click;
			browser[5] = chrome;
			break;

		case 6:// redefiningLocationIE
			click = new RedefiningLocation(url);
			click.creation();
			listClickJacking[6] = click;
			browser[6] = chrome;
			break;
		}
	}

	public boolean test(ClickJacking clickJacking, int browser, int idAttack) throws IOException {
		ManageProperties mP = new ManageProperties();
		String pathChromeDriver = mP.retrievingProperty("path_driver_Chrome");
		String pathExplorerDriver = mP.retrievingProperty("path_driver_IE");
		boolean result = true;
		String link = "http://localhost:8080/ClickjackingTest/html_generated/" + clickJacking.getHtmlFile().getName();
		TestPageOk testPage = new TestPageOk(link, 5);
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		ChromeDriver notAttackedPage = new ChromeDriver();
		try {
			System.out.println(testPage.testPageExist());
		} catch (ErrorPage e) {
			System.out.println(e.getMessage());
			this.failConnection = true;
		} finally {
			testPage.disconnect();
		}
		if (!failConnection) {
			if (browser == 0) {

				System.setProperty("webdriver.chrome.driver", pathChromeDriver);
				ChromeDriver driverSwitchChrome = new ChromeDriver();
				ChromeDriver attackedSiteChrome;

				notAttackedPage.get(clickJacking.getSrc());
				driverSwitchChrome.get(link);

				try {
					while (true)
						driverSwitchChrome.switchTo().frame(driverSwitchChrome.findElementByClassName("malicious"));
				} catch (NoSuchElementException exception) {
					attackedSiteChrome = driverSwitchChrome;
				}
				ArrayList<String> elClickjackingSite = takingHref(attackedSiteChrome);
				ArrayList<String> elOriginalSite = takingHref(notAttackedPage);
				boolean styleClickAttack = takingStyle(attackedSiteChrome);
				boolean styleOriginal = takingStyle(notAttackedPage);
				result = comparisonResult(elClickjackingSite, elOriginalSite, styleClickAttack, styleOriginal);
				notAttackedPage.quit();
				attackedSiteChrome.quit();
			} else if (browser == 1) {
				System.setProperty("webdriver.ie.driver", pathExplorerDriver);
				InternetExplorerDriver driverSwitchIE = new InternetExplorerDriver();
				InternetExplorerDriver attackedSiteIE;

				notAttackedPage.get(clickJacking.getSrc());
				driverSwitchIE.get(link);

				try {
					while (true)
						driverSwitchIE.switchTo().frame(driverSwitchIE.findElementByClassName("malicious"));
				} catch (NoSuchElementException exception) {
					attackedSiteIE = driverSwitchIE;
				}
				ArrayList<String> elClickjackingSite = takingHref(attackedSiteIE);
				ArrayList<String> elOriginalSIte = takingHref(notAttackedPage);
				boolean styleClickAttack = takingStyle(attackedSiteIE);
				boolean styleOriginal = takingStyle(notAttackedPage);
				result = comparisonResult(elClickjackingSite, elOriginalSIte, styleClickAttack, styleOriginal);
				notAttackedPage.quit();
				attackedSiteIE.quit();
			}
			System.out.println("result: " + result);
			results[idAttack] = result;
		}
		return failConnection;
	}

	public ArrayList<String> takingHref(WebDriver driver) {
		List<WebElement> el = new ArrayList<WebElement>();
		ArrayList<String> elResult = new ArrayList<String>();
		String nameDriver = driver.getClass().getSimpleName();
		boolean existLink = true;

		if (nameDriver.equals("ChromeDriver")) {
			ChromeDriver driverChrome = (ChromeDriver) driver;

			try {
				el = driverChrome.findElementsByTagName("link");
			} catch (NoSuchElementException exception) {
				existLink = false;
				System.out.println("there aren't link in the site");
			}
		}

		else if (nameDriver.equals("InternetExplorerDriver")) {
			InternetExplorerDriver driverIE = (InternetExplorerDriver) driver;

			try {
				el = driverIE.findElementsByTagName("link");
			} catch (NoSuchElementException exception) {
				existLink = false;
				System.out.println("there aren't link in the site");
			}
		}

		if (existLink) {
			for (WebElement e : el) {

				boolean existRel = true;
				boolean existHref = true;

				try {
					e.getAttribute("rel");
				} catch (NullPointerException exception) {
					System.out.println("there isn't rel attribute in this link");
					existRel = false;
				}

				if (existRel) {
					if (e.getAttribute("rel").equalsIgnoreCase("stylesheet")) {
						try {
							e.getAttribute("href");
						} catch (NullPointerException exception) {
							System.out.println("there isn't href attribute in this link");
							existHref = false;
						}
						if (existHref) {
							System.out.println("href " + e.getAttribute("href"));
							elResult.add(e.getAttribute("href"));
						}
					}
				}
			}
		}
		return elResult;
	}

	public boolean takingStyle(WebDriver driver) {
		boolean result = true;
		String nameDriver = driver.getClass().getSimpleName();
		if (nameDriver.equals("ChromeDriver")) {
			ChromeDriver driverChrome = (ChromeDriver) driver;
			System.out.println(driverChrome.getPageSource());
			try {
				driverChrome.findElementByTagName("style");
			} catch (NoSuchElementException exception) {
				result = false;
				System.out.println("there aren't style in the site");
			}
		}
		if (nameDriver.equals("InternetExplorerDriver")) {
			InternetExplorerDriver driverIE = (InternetExplorerDriver) driver;
			try {
				driverIE.findElementByTagName("style");
			} catch (NoSuchElementException exception) {
				result = false;
				System.out.println("there aren't style in the site");
			}
		}
		return result;
	}

	public boolean comparisonResult(ArrayList<String> elClickjackingSite, ArrayList<String> elOriginalSite,
			boolean styleClickAttack, boolean styleOriginal) {

		boolean resultStyle = false;
		boolean resultHref = false;
		
		if (styleClickAttack == styleOriginal)
			resultStyle = true;
		
		if (elOriginalSite.size() == elClickjackingSite.size()) 
			resultHref = true;

		System.out.println("result style: " + resultStyle);
		System.out.println("result href: " + resultHref);
		if (resultStyle && resultHref)
			return true;
		else
			return false;

	}

	public String executionTest() throws IOException {
		for (int i = 0; i < listAttack.length; i++) {
			if (listAttack[i]) {
				if (test(listClickJacking[i], browser[i], i))
					break;
			}
		}
		return "" + (results[0] ? "1" : "0") + (results[1] ? "1" : "0") + (results[2] ? "1" : "0")
				+ (results[3] ? "1" : "0") + (results[4] ? "1" : "0") + (results[5] ? "1" : "0")
				+ (results[6] ? "1" : "0");
	}

	public boolean getFailConnection() {
		return this.failConnection;
	}

	

	public void cleanEnviroment() {
		for (int i = 0; i < listAttack.length; i++) {
			if (listAttack[i]) {
				if (i != 2)
					DeleteFile(listClickJacking[i].getHtmlFile());
				else {
					DeleteFile(listClickJacking[i].getHtmlFile());
					DeleteFile(((Fictitious2) listClickJacking[i]).getHtmlFile2());
				}
			}
		}
	}

	public void DeleteFile(File f) {
		if (f.delete()) {
			System.out.println("Deleted the file: " + f.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
	}

}

