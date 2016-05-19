package org.epsi.java.ee.football.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.epsi.java.ee.football.model.Blogg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Blog extends HttpServlet {

	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

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

			List<Blogg> EquipeArray = new ArrayList<Blogg>();

			if (request.getParameter("id") != null) {

				// on récupère l'id dans l'url
				String id = request.getParameter("id");
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM equipe WHERE id = " + id);
				while (rs.next()) {

					String titre = rs.getString("titre");
					String contenu = rs.getString("contenu");
					String image = rs.getString("image");
					String date = rs.getString("date");
					
					Blogg blog = new Blogg();
					blog.setTitre(titre);
					blog.setContenu(contenu);
					blog.setImage(image);
					blog.setDate(date);
				

					EquipeArray.add(blog);
				}

				if (id.equalsIgnoreCase("3")) {
					String message = "Transmission de variables : OK !";
					request.setAttribute("id", message);
				} else {
					request.setAttribute("id", id);
				}

				request.setAttribute("test", EquipeArray);

				request.getRequestDispatcher("/equipeId.jsp").forward(request, response);
			} else {
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM blog");
				while (rs.next()) {

					String titre = rs.getString("titre");
					String contenu = rs.getString("contenu");
					String image = rs.getString("image");
					String date = rs.getString("date");
					
					Blogg blog = new Blogg();
					blog.setTitre(titre);
					blog.setContenu(contenu);
					blog.setImage(image);
					blog.setDate(date);
				

					EquipeArray.add(blog);

					request.setAttribute("test", EquipeArray);
				}

				request.getRequestDispatcher("/blog.jsp").forward(request, response);
			}

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

}
