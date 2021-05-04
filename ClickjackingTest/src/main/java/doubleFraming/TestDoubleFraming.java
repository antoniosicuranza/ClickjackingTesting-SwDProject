package doubleFraming;

import java.io.IOException;

public class TestDoubleFraming {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdrivers\\chromedriver.exe");
		DoubleFraming runDoubleFraming = new DoubleFraming();
		System.out.println("Output: "+runDoubleFraming.calling());
	}

}
