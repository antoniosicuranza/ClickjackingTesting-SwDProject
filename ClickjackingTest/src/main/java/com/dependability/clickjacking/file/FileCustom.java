package com.dependability.clickjacking.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileCustom{
	private File file;
	private InputStream iS;
	private Properties prop;
	public FileCustom(String inProjectPath) throws IOException {
		iS = this.getClass().getResourceAsStream("/config.properties");
		prop = new Properties();
		prop.load(iS);
		String pathProject = prop.getProperty("path_project");
		this.file = new File(pathProject + inProjectPath);
	}
	public File getFile(){
		return this.file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}