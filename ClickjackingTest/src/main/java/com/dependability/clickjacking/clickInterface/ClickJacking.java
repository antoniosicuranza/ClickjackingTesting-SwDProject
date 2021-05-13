package com.dependability.clickjacking.clickInterface;

import java.io.File;
import java.io.IOException;

public interface ClickJacking {
	String src = "";
	File f = null;
	public void creation()  throws IOException ;
	public void run()  throws IOException ;
	public String getSrc();
	public void setSrc(String src);
	public File getHtmlFile();
	public void setHtmlFile(File f);
}
