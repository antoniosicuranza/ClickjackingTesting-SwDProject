package com.dependability.clickjacking.clickInterface;

import java.io.IOException;

public interface ClickJacking {
	String src = "";
	public void creation()  throws IOException ;
	public void run()  throws IOException ;
	public String getSrc();
	public void setSrc(String src);
}
