package com.dependability.clickjacking.clickInterface;

import java.io.IOException;

public interface ClickJacking {
	public String src = "";
	public void creation()  throws IOException ;
	public void run()  throws IOException ;
	public void getSrc();
	public void setSrc();
}
