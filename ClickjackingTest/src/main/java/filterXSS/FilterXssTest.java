package filterXSS;

import java.io.IOException;

public class FilterXssTest {

	public static void main(String[] args) throws IOException {
		FilterXss d = new FilterXss();
		System.out.println(d.filterIE8());
		System.out.println(d.filterChrome());

	}
}
