package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ReviewAPI
 */
@WebServlet("/ReviewAPI")
public class ReviewAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Review reviewObj = new Review();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

 // Convert request parameters to a Map
    
    private static Map getParasMap(HttpServletRequest request)
    {
     Map<String, String> map = new HashMap<String, String>();
    try
     {
	     Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	     String queryString = scanner.hasNext() ?
	     scanner.useDelimiter("\\A").next() : "";
	     scanner.close();
	     String[] params = queryString.split("&");
     for (String param : params)
     { 
    
	    String[] p = param.split("=");
	     map.put(p[0], p[1]);
	 }
     }
    catch (Exception e)
     {
     }
    return map;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String output = reviewObj.insertReview(
				request.getParameter("reviewType"),
				request.getParameter("reviewDesc"),
				request.getParameter("reviewValue"));
				response.getWriter().write(output); 
				doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = reviewObj.updateReview(
		paras.get("reviewID").toString(),
		paras.get("reviewType").toString(),
		paras.get("reviewDesc").toString(),
		paras.get("reviewValue").toString());
		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = reviewObj.deleteReview(paras.get("reviewID").toString());
		response.getWriter().write(output); 
	}

}
