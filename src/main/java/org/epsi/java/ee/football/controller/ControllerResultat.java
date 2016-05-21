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
import org.epsi.java.ee.football.model.Championnat;
import org.epsi.java.ee.football.model.Equipe;
import org.epsi.java.ee.football.model.Resultat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerResultat extends HttpServlet {

	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";
	
	public String getNomequipe(int id) throws SQLException{
		DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE,
				MYSQL_USER, MYSQL_PWD);
		Connection conn = getConnection();
		
		String query = "SELECT * FROM equipe WHERE id_equipe = " + id;
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		
		results.next();
		String test = results.getString("abr_equipe");
			
		return test;
	}


	private static final long serialVersionUID = 1L;

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE,
				MYSQL_USER, MYSQL_PWD);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		try {
			conn = getConnection();

			ArrayList<String> arrList = new ArrayList<String>();
			List<Resultat> resultattList = new ArrayList<Resultat>();

			if (request.getParameter("id") != null) {
				
				request.setAttribute("championnat", resultattList);

				request.getRequestDispatcher("/championnatId.jsp").forward(request, response);
			} else {
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM `match` WHERE `id_journee` = 1");
				while (rs.next()) {				
					
					
					String date_match = rs.getString("date_match");
					String buts_dom = rs.getString("buts_dom");
					String buts_ext = rs.getString("buts_ext");
					int id_match = rs.getInt("id_match");
					int id_dom = rs.getInt("id_dom");
					int id_ext = rs.getInt("id_ext");
					String id_journee = rs.getString("id_journee");
					String id_championnat = rs.getString("id_championnat");

					Resultat resultat = new Resultat();
					
					resultat.setId_match(id_match);
					 // resultat.setDate_match(date_match);
					resultat.setId_dom(this.getNomequipe(id_dom));
					resultat.setId_ext(this.getNomequipe(id_ext));
					resultat.setButs_dom(buts_dom);
					resultat.setButs_ext(buts_ext);
					resultat.setDate_match(date_match);
					resultattList.add(resultat);

					request.setAttribute("resultat", resultattList);
				}

				request.getRequestDispatcher("/resultat.jsp").forward(request, response);
			}

			System.out.println(resultattList);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

}
