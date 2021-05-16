package com.dependability.clickjacking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dependability.clickjacking.reference.References;
import com.dependability.clickjacking.testing.TestingClcikJacking;

/**
 * Servlet implementation class ReferenceClickjackingSites
 */
@WebServlet("/ReferenceClickjackingSites")
public class ReferenceClickjackingSites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String url;
    public boolean[] check= {false,false,false,false,false,false,false}; 
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//References ref = new References (url,1);
		//ref.selectAttack();
		//request.setAttribute("link", ref.getSrc());
		//request.getRequestDispatcher(ref.getPage()).forward(request, response);
		url=request.getParameter("url");
		System.out.println("url:"+url);
		for(int i = 0; i<7; i++) {
			check[i]=request.getParameter(""+i) != null;
		}
		
		TestingClcikJacking test = new TestingClcikJacking(url, check);
		test.creationEvnviroment();
		test.executionTest();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doGet(request,response);
	}
	

}