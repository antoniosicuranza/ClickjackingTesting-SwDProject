package com.dependability.clickjacking.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.dependability.clickjacking.properties.ManageProperties;

public class FileCustom{
	private File file;
	public FileCustom(String inProjectPath) throws IOException {
		ManageProperties mP = new ManageProperties();
		String pathProject = mP.retrievingProperty("path_project");
		this.file = new File(pathProject + inProjectPath);
	}
	public File getFile(){
		return this.file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}