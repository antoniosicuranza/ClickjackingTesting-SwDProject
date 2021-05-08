package com.dependability.clickjacking.reference;

public class References {
	private String url;
	private int idAttack; 
	private String src;
	private String page;
	public References(String url, int idAttack) {
		this.idAttack = idAttack;
		this.url = url;
		src = "";
		page = "";
	}
	
	public int getIdAttack() {
		return idAttack;
	}
	
	public void setIdAttack(int idAttack) {
		this.idAttack = idAttack;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPage() {
		return this.page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public String getSrc() {
		return this.src;
	}
	
	public void setSrc(String src) {
		this.src = src;
	}
	
	public String selectAttack() {
		switch (idAttack)
		{
		    case 0://disablingJavascriptTest
		        src = url;
		        page = "disablingJavascriptTest.jsp";
		        break;
		        
		    case 1://disablingJavascriptTest2
		    	src = url;
		    	page = "disablingJavascriptTest2.jsp";
		        break;
		        
		    case 2://fictitious
		    	src = url;
		    	page = "fictitious.jsp";
		        break;
		        
		    case 3://fictitious2 Da decidere
		    	break;
		    	
		    case 4://filter_xss_chrome
		        src = url+"?param=if(top+!%3D+self)+%7B+top.location%3Dself.location%3B+%7D";
		        page = "filter_xss_chrome.jsp";
		        break;
		        
		    case 5://filter_xss_IE8
		    	src = url+"?param=<script>if";
		    	page = "filter_xss_IE8.jsp";
		        break;
		        
		    case 6://onBeforeUnloadEventTest
		    	src = url;
		    	page = "onBeforeUnloadEventTest.jsp";
		        break;
		        
		    case 7://redefiningLocationIE
		    	src = url;
		    	page = "redefiningLocationIE.jsp";
		        break;
		}
		return url;
		
	}
}