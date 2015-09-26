package com.sqlinjection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SQL_Injection_Servlet
 */
@WebServlet("/SQL_Injection_Servlet")
public class SQL_Injection_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SQL_Injection_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OracleConnection connObj = new OracleConnection();
		String buffer = null;
		StringBuilder sb = new StringBuilder();
		int col = 0;
		List<String> strList = new ArrayList<String>();
		
		System.out.println(request.getParameterMap().toString());
		System.out.println(request.getParameter("submit1"));
		System.out.println(request.getParameter("submit2"));
		
		if(request.getParameter("submit1") != null) {
			Connection conn = connObj.getConnection();
			if (conn == null) {
				System.out.println("No connection");
				return;
			}
			sb = new StringBuilder();
			String sql = "select * from chi.emp where ename = ?";
	        try {
		        //creating PreparedStatement object to execute query
		        PreparedStatement preStatement = conn.prepareStatement(sql);
		        preStatement.setString(1, request.getParameter("nametextbox1").toUpperCase());
		        ResultSet result = preStatement.executeQuery();
	        	
		        ResultSetMetaData rsmd = result.getMetaData();
		        col = rsmd.getColumnCount(); 
		        for (int i = 1; i <= col; i++) {
		        	buffer = rsmd.getColumnName(i);
					sb.append(String.format("%-21s", buffer + ' '));
					strList.add(buffer);
				}
				sb.append('\n');
		        while(result.next()) {
		        	for (int i = 1; i <= col; i++) {
		        		buffer = result.getString(i);
						sb.append(String.format("%-21s", buffer + ' '));
						strList.add(buffer);
					}
					sb.append('\n');
		        }
		        System.out.println(sb);
	        }
	        catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		else if (request.getParameter("submit2") != null) {
			Connection conn = connObj.getConnection();
			if (conn == null) {
				System.out.println("No connection");
				return;
			}
			
			
	        try {
		        //creating PreparedStatement object to execute query
	        	Statement st = conn.createStatement();
	        	String sql = "select * from chi.emp where ename = '" + request.getParameter("nametextbox2").toUpperCase() + "'";
		        ResultSet result = st.executeQuery(sql);		        
		        ResultSetMetaData rsmd = result.getMetaData();
		       		        System.out.println(sql);
		        col = rsmd.getColumnCount();
		        for (int i = 1; i <= col; i++) {
		        	buffer = rsmd.getColumnName(i);
					sb.append(String.format("%-21s", buffer) + ' ');
					strList.add(buffer);
				}
				sb.append('\n');
		        while(result.next()) {
		        	for (int i = 1; i <= col; i++) {
		        		buffer = result.getString(i);
						sb.append(String.format("%-21s", buffer) + ' ');
						strList.add(buffer);
					}
					sb.append('\n');
		        }
		        System.out.println(sb);
		        
		        
	        }
	        catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		
		
		request.setAttribute("col", col);
        request.setAttribute("strList", strList);
        
		// Set response content type
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// New location to be redirected
		
		
		request.getRequestDispatcher("/SQL_Injection.jsp").forward(request, response);
		
		// request.getContextPath() - return root path of your application,
		System.out.println(request.getContextPath());
		// response.sendRedirect(request.getContextPath() + "/SQL_Injection.jsp");
		
	}
	


}
