package com.dependability.clickjacking.file;

import java.io.File;

public class FileCustom{
	String path;
	File f;
	public FileCustom(String inProjectPath) {
		f = new File("C:\\Users\\Valerio\\git\\"
				+ "ClickjackingTesting-SwDProject\\ClickjackingTest\\" + inProjectPath);
	}
	public File getFile() {
		return this.f;
	}
	public void setFile(File f) {
		this.f = f;
	}

}
