package com.dependability.clickjacking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dependability.clickjacking.reference.References;
import com.dependability.clickjacking.testing.TestingClickJacking;

/**
 * Servlet implementation class ReferenceClickjackingSites
 */
@WebServlet("/ReferenceClickjackingSites")
public class ReferenceClickjackingSites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String url;
	public boolean[] check= {false,false,false,false,false,false,false}; 
    public String results; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReferenceClickjackingSites() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		url=request.getParameter("url");
		System.out.println("url:"+url);
		for(int i = 0; i<7; i++) {
			check[i]=request.getParameter(""+i) != null;
			}
		
		TestingClickJacking test = new TestingClickJacking(url, check);
		test.creationEvnviroment();
		results=test.executionTest();

		request.setAttribute("results", results);
		request.setAttribute("csv", test.writeCSV());
		request.setAttribute("executed", ""+(check[0]&&check[1]? "1":"0")+(check[2]? "1":"0")+(check[3]&&check[4]? "1":"0")+(check[5]? "1":"0")+(check[6]? "1":"0"));
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request,response);
	}
	

}