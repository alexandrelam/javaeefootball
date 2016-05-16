package org.epsi.java.ee.football.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.epsi.java.ee.football.exception.GalleryException;
import org.epsi.java.ee.football.model.User;
import org.epsi.java.ee.football.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = -2135238259758756344L;
	
	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "gallery";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";
	
	@Inject
	public UserService userService;
	public UserService userAllService;
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE, MYSQL_USER, MYSQL_PWD);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		
		try {
			conn = getConnection();
			
			ArrayList<String> arrList = new ArrayList<String>(); 
           
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * FROM user");
            while ( rs.next() ) {
            	
            	arrList.add(rs.getString("pseudonym"));
             
                String email = rs.getString("email");
              
                System.out.println(email);
            	
            	String message = "Transmission de variables : OK !";
            	request.setAttribute( "test", arrList );
                
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
		
		request.getRequestDispatcher("/signUp.jsp")
			.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudonym = request.getParameter("pseudonym");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean isCguValidated = "on".equals(request.getParameter("cgu"));
		
		try {
			User user = userService.create(pseudonym, email, password, isCguValidated);

			HttpSession session = request.getSession();
			
			session.setAttribute("user", user);
			session.setAttribute("userAll", "coucou");
			
			request.getRequestDispatcher("/profile.jsp")
				.forward(request, response);
		} catch (GalleryException galleryException) {
			galleryException.printStackTrace();
			request.setAttribute("error", galleryException.getMessage());
			request.getRequestDispatcher("/signUp.jsp")
			.forward(request, response);
		}
	}
}
