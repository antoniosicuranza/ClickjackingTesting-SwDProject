package com.dependability.clickjacking.properties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ManageProperties {
	private Properties prop;
	private InputStream iS;
	
	public ManageProperties() throws IOException {
		iS = this.getClass().getResourceAsStream("/config.properties");
		prop = new Properties();
		prop.load(iS);
	}
	public String retrievingProperty(String property){
		return prop.getProperty(property);
	}

}
