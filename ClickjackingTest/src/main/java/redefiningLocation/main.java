package redefiningLocation;

import java.io.IOException;

import disablingJavascript.DisablingJavascript;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.ie.driver", "C:\\browserdriver\\IEDriverServer.exe");
		RedefiningLocation d=new RedefiningLocation();
		System.out.println(d.redefiningLocation());
	}

}
