package com.dependability.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dependability.exception.ErrorPage;

public class TestPageOk {
	
	private String url;
	private int tentative;
	HttpURLConnection c;
	
	public TestPageOk(String url, int tentative) {
		this.url = url;
		this.tentative = tentative;
		c = null;
	}

	public HttpURLConnection openConnection() {
		URL link = null;
		HttpURLConnection c = null;
		try {
			link = new URL(url);
			c = (HttpURLConnection) link.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public void connect() {
		try {
			c.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		c.disconnect();
	}
	public void setConnection(HttpURLConnection c) {
		this.c = c;
	}
	public HttpURLConnection getConnection() {
		return this.c;
	}
	public boolean testPageExist() throws ErrorPage {
		boolean exist = false;
		String message = "";
		for (int i = 0; i < tentative; i++) {
			setConnection(openConnection());
			connect();
			try {
				System.out.println(c.getResponseCode());
				message = ""+c.getResponseCode();
				if (c.getResponseCode() == HttpURLConnection.HTTP_OK) {
					exist = true;
					break;
				} else {
					Thread.sleep(3000);
					disconnect();
				}
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!exist) {
			throw new ErrorPage(
					"There were " + tentative + " to find the " + "page at location: " + url + " without any success error: "+message);
		}

		return exist;
	}
}
