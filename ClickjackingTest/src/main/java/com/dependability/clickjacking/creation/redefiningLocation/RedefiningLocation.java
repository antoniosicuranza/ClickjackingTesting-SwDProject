package com.dependability.clickjacking.creation.redefiningLocation;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;

public class RedefiningLocation implements ClickJacking{

	private String src;
	private File f;
	
	public RedefiningLocation(String src){
		this.src = src;
		f = new File("src\\main\\webapp\\redefiningLocation.html");
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
        bw.write("<html><body>");
        bw.write("<script>\r\n"
        		+ "	    var location = \"dummy\";\r\n"
        		+ "	</script>\r\n");
        bw.write("	<iframe src="+src+"></iframe>");
        bw.write("</body></html>");
        bw.close();		
	}
	
	@Override
	public void run() throws IOException {
		Desktop.getDesktop().browse(f.toURI());
	}
}


