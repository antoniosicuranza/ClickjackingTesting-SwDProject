package com.dependability.clickjacking.testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.cration.doubleFraming.Fictitious2;
import com.dependability.clickjacking.cration.filterXss.chrome.FilterXssChrome;
import com.dependability.clickjacking.cration.filterXss.iE.FilterXssIE;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptChrome;
import com.dependability.clickjacking.creation.disablingJavascript.DisablingJavascriptIE;
import com.dependability.clickjacking.creation.onBeforeUnloadEvent.OnBeforeUnloadEvent;
import com.dependability.clickjacking.creation.redefiningLocation.RedefiningLocation;

public class TestingClickJacking {
	
	private String url;
	private boolean[] listAttack;
	private ClickJacking[] listClickJacking;
	private int[] browser;
	private boolean[] results;
	private static int chrome = 0;
	private static int internet_explorer = 1;
	public TestingClickJacking(String url, boolean[] listAttack) {
		this.listAttack = listAttack;
		this.url = url;
		listClickJacking = new ClickJacking[7];
		browser = new int[7];
		results= new boolean[7]; 
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
		for(int i = 0; i < listAttack.length; i++) {
			if(listAttack[i]) {
				int idAttack = i;
				selectAttack(idAttack);
			}
		}
		
	}
	public void  selectAttack(int idAttack) throws IOException {
		ClickJacking click;
		switch (idAttack){
		    case 0://disablingJavascriptTest
		    	click = new DisablingJavascriptChrome(url);
		    	click.creation();
		    	listClickJacking[0] = click;
		    	browser[0] = chrome;
		        break;
		        
		    case 1://disablingJavascriptTest2
		    	click = new DisablingJavascriptIE(url);
		    	click.creation();
		    	listClickJacking[1] = click;
		    	browser[1] = internet_explorer;
		        break;
		        
		    case 2://doublFraming
		    	click = new Fictitious2(url);
		    	click.creation();
		    	listClickJacking[2] = click;
		    	browser[2] = chrome;
		        break;
		        
		    case 3://filter_xss_chrome
		    	click = new FilterXssChrome(url);
		    	click.creation();
		    	listClickJacking[3] = click;
		    	browser[3] = chrome;
		        break;
		        
		    case 4://filter_xss_IE8
		    	click = new FilterXssIE(url);
		    	click.creation();
		    	listClickJacking[4] = click;
		    	browser[4] = internet_explorer;
		        break;
		        
		    case 5://onBeforeUnloadEventTest
		    	click = new OnBeforeUnloadEvent(url);
		    	click.creation();
		    	listClickJacking[5] = click;
		    	browser[5] = chrome;
		        break;
		        
		    case 6://redefiningLocationIE
		    	click = new RedefiningLocation(url);
		    	click.creation();
		    	listClickJacking[6] = click;
		    	browser[6] = chrome;
		        break;
			}
	}
	
	
	private boolean test(ClickJacking clickJacking, int browser, int idAttack) throws IOException {// browser tra i parametri
		try {
			Thread.sleep(10000);//Wait  time for the deploy of the html generated on server
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result = true;
		String link = "http://localhost:8080/ClickjackingTest/html_generated/"+clickJacking.getHtmlFile().getName();
        if(browser == 0) {
			System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
			ChromeDriver driverSwitchChrome = new ChromeDriver();
			ChromeDriver driverChrome;
			driverSwitchChrome.get(link);
			try {
				while(true)
					driverSwitchChrome.switchTo().frame(driverSwitchChrome.findElementByClassName("malicious"));
			}
			catch (NoSuchElementException exception) {
				driverChrome = driverSwitchChrome;
			}
			try {
				//System.out.println(driverChrome.getPageSource());
				driverChrome.findElementByTagName("style");
			}
			catch (NoSuchElementException exception2) {
				result = false;
			}
			driverChrome.quit();
		}
		else if(browser == 1) {
			System.setProperty("webdriver.ie.driver", "C:\\browserdrivers\\IEDriverServer.exe");
			InternetExplorerDriver driverSwitchIE = new InternetExplorerDriver();
			InternetExplorerDriver driverIE;
			driverSwitchIE.get(link);

			try {
				while(true)
					driverSwitchIE.switchTo().frame(driverSwitchIE.findElementByClassName("malicious"));
			}
			catch (NoSuchElementException exception) {
				driverIE = driverSwitchIE;
			}
			try {
				//System.out.println(driverIE.getPageSource());
				driverIE.findElementByTagName("style");
			}
			catch (NoSuchElementException | JavascriptException exception2) {
				result = false;
			}
			driverIE.quit();
		}
        System.out.println("IE: "+result);
		results[idAttack] = result;
		return result;//true vulnerable, false not vulnerable

	}
	
	public String executionTest() throws IOException {
		for(int i = 0; i < listAttack.length;i++) {
			if(listAttack[i]) {
				test(listClickJacking[i], browser[i], i);
			}
		}
		return ""+(results[0]&&results[1]? "1":"0")+(results[2]? "1":"0")+(results[3]&&results[4]? "1":"0")+(results[5]? "1":"0")+(results[6]? "1":"0");
	}
	
	public String writeCSV() throws IOException {
		String csv="C:\\Users\\sicur\\git\\ClickjackingTesting-SwDProject\\ClickjackingTest\\src\\main\\webapp\\csv\\test.csv";
		FileWriter sb = new FileWriter(new File(csv));

		      sb.append("DisablingJavascript");
		      sb.append(',');
		      sb.append("DoubleFraming");
		      sb.append(',');
		      sb.append("FilterXSS");
		      sb.append(',');
		      sb.append("onBeforeUnloadEvent");
		      sb.append(',');
		      sb.append("Redefininglocation");
		      sb.append('\n');

		      if(listAttack[0]) {sb.append((results[0]&&results[1]? "false":"true"));
		      sb.append(',');}else {sb.append("null,");}
		      if(listAttack[2]) {sb.append((results[2]? "false":"true"));
		      sb.append(',');}else {sb.append("null,");}
		      if(listAttack[3]) {sb.append((results[3]&&results[4]? "false":"true"));
		      sb.append(',');}else {sb.append("null,");}
		      if(listAttack[5]) {sb.append((results[5]? "false":"true"));
		      sb.append(',');}else {sb.append("null,");}
		      if(listAttack[6]) {sb.append((results[6]? "false":"true"));
		      sb.append('\n');}else {sb.append("null\n");}

		      sb.flush();
		      sb.close();
		      
		     return csv;
		  	}
	
	public void cleanEnviroment() {
		for(int i = 0; i < listAttack.length; i++) {
			if(listAttack[i]) {
				DeleteFile(listClickJacking[i].getHtmlFile().getPath());
			}
		} 
	}
	public void DeleteFile(String path) {
		File f = new File(path); 
	    if (f.delete()) { 
	      System.out.println("Deleted the file: " + f.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    }
	}
	
}