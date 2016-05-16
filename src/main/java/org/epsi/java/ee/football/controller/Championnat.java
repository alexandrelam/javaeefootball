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

public class Championnat extends HttpServlet {

	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public class Championnnat {
		private String id;
		private String nom;
		private String logo;
		private String journee;
		private String bp;
		private String bc;
		private String diff;
		private String points;

		// other fields need to be declared as well as their setter methods

		public void setId(String id) {
			this.id = id;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public void setJournee(String journee) {
			this.journee = journee;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public void setBp(String bp) {
			this.bp = bp;
		}

		public void setBc(String bc) {
			this.bc = bc;
		}

		public void setDiff(String diff) {
			this.diff = diff;
		}

		public void setPoints(String points) {
			this.points = points;
		}

		public String getNom() {
			return nom;
		}

		public String getLogo() {
			return logo;
		}

		public String getJournee() {
			return journee;
		}

		public String getBp() {
			return bp;
		}

		public String getBc() {
			return bc;
		}

		public String getDiff() {
			return diff;
		}

		public String getPoints() {
			return points;
		}

		public String foo() {

			String prix = "5";
			// Do something here
			return prix;
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

			ArrayList<String> arrList = new ArrayList<String>();
			List<Championnnat> championnatList = new ArrayList<Championnnat>();

			if (request.getParameter("id") != null) {
				
				// on récupère l'id dans l'url
				String id = request.getParameter("id");
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery(
						"SELECT e.id,logo_equipe,surnom_equipe,sum(case when e.id = m.id_dom and m.buts_dom > m.buts_ext "
								+ "        then 3 " + "             when e.id = m.id_dom and m.buts_dom = m.buts_ext "
								+ "        then 1 " + "             when e.id = m.id_dom and m.buts_dom < m.buts_ext "
								+ "        then 0 " + "        when e.id = m.id_ext and m.buts_dom < m.buts_ext "
								+ "        then 3 " + "             when e.id = m.id_ext and m.buts_dom = m.buts_ext "
								+ "        then 1 " + "             when e.id = m.id_ext and m.buts_dom > m.buts_ext "
								+ "        then 0 " + "        end) as Points , " + "        max(m.id_Journee) as J, "
								+ "        sum(case when e.id = m.id_dom then m.buts_dom "
								+ "             when e.id = m.id_ext then m.buts_ext " + "        end) as Bp, "
								+ "        sum(case when e.id = m.id_dom then m.buts_ext "
								+ "                 when e.id = m.id_ext then m.buts_dom " + "        end) as Bc, "
								+ "        ((sum(case when e.id = m.id_dom then m.buts_dom "
								+ "             when e.id = m.id_ext then m.buts_ext " + "        end)) - "
								+ "        (sum(case when e.id = m.id_dom then m.buts_ext "
								+ "                 when e.id = m.id_ext then m.buts_dom " + "        end))) as Diff "
								+ "          " + "         from equipe e, `match`m " + "        where m.id_ext = e.id "
								+ "        or m.id_dom = e.id " + "        and e.id_championnat = " + id + " "
								+ "        and e.id_championnat = m.id_championnat " + "        group by e.id "
								+ "        order by Points DESC,Diff DESC, Bp DESC");
				while (rs.next()) {

					arrList.add(rs.getString("Points"));

					String nom = rs.getString("surnom_equipe");
					String logo = rs.getString("logo_equipe");
					String journee = rs.getString("J");
					String bp = rs.getString("Bp");
					String bc = rs.getString("Bc");
					String diff = rs.getString("Diff");
					String points = rs.getString("Points");

					Championnnat championnat = new Championnnat();
					championnat.setNom(nom);
					championnat.setLogo(logo);
					championnat.setJournee(journee);
					championnat.setBp(bp);
					championnat.setBc(bc);
					championnat.setDiff(diff);
					championnat.setPoints(points);

					championnatList.add(championnat);
				}

				if (id.equalsIgnoreCase("3")) {
					String message = "Transmission de variables : OK !";
					request.setAttribute("id", message);
				} else {
					request.setAttribute("id", id);
				}

				request.setAttribute("test", championnatList);

				request.getRequestDispatcher("/championnatId.jsp").forward(request, response);
			} else {
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM championnat");
				while (rs.next()) {

					arrList.add(rs.getString("nom"));

					String nom = rs.getString("nom");
					String logo = rs.getString("logo");

					Championnnat championnat = new Championnnat();
					championnat.setNom(nom);
					championnat.setLogo(logo);

					championnatList.add(championnat);

					request.setAttribute("test", championnatList);

					request.getRequestDispatcher("/championnat.jsp").forward(request, response);

				}
			}

			System.out.println(championnatList);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

}
