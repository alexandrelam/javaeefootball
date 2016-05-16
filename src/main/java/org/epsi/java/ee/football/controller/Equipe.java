package org.epsi.java.ee.football.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.epsi.java.ee.football.controller.Championnat.Championnnat;
import org.epsi.java.ee.football.exception.GalleryException;

import org.epsi.java.ee.football.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Equipe extends HttpServlet {

	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public class Equipee {

		private String nomequipe;

		public void setNomequipe(String nomequipe) {
			this.nomequipe = nomequipe;
		}

		public String getNomequipe() {
			return nomequipe;
		}

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


			List<Equipee> EquipeArray = new ArrayList<Equipee>();

			if (request.getParameter("id") != null) {

				// on récupère l'id dans l'url
				String id = request.getParameter("id");
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM equipe");
				while (rs.next()) {


					String nom = rs.getString("surnom_equipe");

					Equipee equipe = new Equipee();
					equipe.setNomequipe(nom);

					EquipeArray.add(equipe);
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

				rs = stmt.executeQuery("SELECT * FROM equipe");
				while (rs.next()) {



					String nom = rs.getString("surnom_equipe");

					Equipee equipe = new Equipee();
					equipe.setNomequipe(nom);

					EquipeArray.add(equipe);

					request.setAttribute("test", EquipeArray);

					request.getRequestDispatcher("/equipeId.jsp").forward(request, response);

				}
			}

			System.out.println(EquipeArray);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

}
