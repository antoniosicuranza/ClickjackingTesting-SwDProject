package com.dependability.clickjacking.cration.doubleFraming;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.file.FileCustom;

public class Fictitious1 implements ClickJacking {
	private String src;
	private File f;
	
	public Fictitious1(String src){
		this.src = src;
	    f = (new FileCustom("src\\main\\webapp\\html_generated\\fictitious.html")).getFile();
	    
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
	public void creation() throws IOException{
        BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html><body>");
	    bw.write("<iframe src=\"" + src + "\" class=\"malicious\"></iframe>");
	    bw.write("</body></html>");
	    bw.close();
		
	}
	
	@Override
	public void run() throws IOException{
		Desktop.getDesktop().browse(f.toURI());

	}
	

}
