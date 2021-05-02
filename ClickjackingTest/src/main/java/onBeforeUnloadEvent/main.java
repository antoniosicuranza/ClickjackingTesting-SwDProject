package onBeforeUnloadEvent;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");	

		OnBeforeUnloadEvent x= new OnBeforeUnloadEvent();
		System.out.println(x.onBeforeUnloadEvent());
	}

}
