
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;



public class disablingJavascript {
 
	public boolean disablingJavascriptRun() throws IOException {
	return securityRestricted() && sandbox();
	}
	
	public boolean securityRestricted() throws IOException {
		String urltext = "http://localhost:8080/ClickjackingTest/disablingJavascriptTest.html";
	    URL url = new URL(urltext);
	    int responseCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
	    if(responseCode==200) {
		return true;}
	    else {
	    	return false;
	    }
	}
	public boolean sandbox() throws IOException {
		String urltext = "http://localhost:8080/ClickjackingTest/disablingJavascriptTest2.html";
	    URL url = new URL(urltext);
	    int responseCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
	    if(responseCode==200) {
		return true;}
	    else {
	    	return false;
	    }
	}
}
