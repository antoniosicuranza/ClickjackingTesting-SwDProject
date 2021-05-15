package com.dependability.clickjacking.cration.doubleFraming;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;

public class Fictitious2 implements ClickJacking {
	String src;
	private File f;

	public Fictitious2(String src){
		this.src = src;
		f = new File("src\\main\\webapp\\html_generated\\fictitious2.html");
	}
	@Override
	public void creation() throws IOException{
		Fictitious1 fictitious = new Fictitious1(src);
		fictitious.creation();
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html><body>");
	    bw.write("<iframe src =\"" + fictitious.getHtmlFile().getName() + "\" class=\"malicious\"></iframe>");
	    bw.write("</body></html>");
	    bw.close();
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
	public void run() throws IOException {
		Desktop.getDesktop().browse(f.toURI());
		
	}
	
}
