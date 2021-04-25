package doubleFraming;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoubleFraming {
	public boolean doubleCalling() throws IOException {
		return calling();
	}
	
	public boolean calling() throws IOException {
		String urlTxt = "http://localhost:8080/doubleFraming/fictitious2.html";
		URL url = new URL(urlTxt);
		int responseCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
		System.out.println("ResponseCode: "+responseCode);
		if(responseCode==200)
			return true;
		else
			return false;
		
	}
}
