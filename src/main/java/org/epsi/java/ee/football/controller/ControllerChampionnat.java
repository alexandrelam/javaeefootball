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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerChampionnat extends HttpServlet {

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

			ArrayList<String> arrList = new ArrayList<String>();
			List<Championnat> championnatList = new ArrayList<Championnat>();

			if (request.getParameter("id") != null) {

				// on r�cup�re l'id dans l'url
				String id = request.getParameter("id");
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery(
						"select id_equipe, surnom_equipe,logo_equipe,sum(case when e.id_equipe = m.id_dom and m.buts_dom > m.buts_ext "
+ "    then 3 "
+ "    when e.id_equipe = m.id_dom and m.buts_dom = m.buts_ext "
+ "    then 1 "
+ "    when e.id_equipe = m.id_dom and m.buts_dom < m.buts_ext "
+ "    then 0 "
+ "	when e.id_equipe = m.id_ext and m.buts_dom < m.buts_ext "
+ "    then 3 "
+ "         when e.id_equipe = m.id_ext and m.buts_dom = m.buts_ext "
+ "    then 1 "
+ "         when e.id_equipe = m.id_ext and m.buts_dom > m.buts_ext "
+ "    then 0 "
+ "end) as Points , "
+ "max(m.id_Journee) as J, "
+ "sum(case when e.id_equipe = m.id_dom then m.buts_dom "
+ "     when e.id_equipe = m.id_ext then m.buts_ext "
+ "end) as Bp, "
+ "sum(case when e.id_equipe = m.id_dom then m.buts_ext "
+ "         when e.id_equipe = m.id_ext then m.buts_dom "
+ "end) as Bc, "
+ "((sum(case when e.id_equipe = m.id_dom then m.buts_dom "
+ "     when e.id_equipe = m.id_ext then m.buts_ext "
+ "end)) - "
+ "(sum(case when e.id_equipe = m.id_dom then m.buts_ext "
+ "         when e.id_equipe = m.id_ext then m.buts_dom "
+ "end))) as Diff "
+ "from equipe e "
+ "join `match`m on m.id_ext = e.id_equipe "
+ "or m.id_dom = e.id_equipe "
+ "Where e.id_championnat = " + id + " "
+ "and e.id_championnat = m.id_championnat "
+ "group by id_equipe "
+ "order by Points DESC,Diff DESC, Bp DESC");
				while (rs.next()) {

					arrList.add(rs.getString("Points"));
					
					String idC = rs.getString("id_equipe");
					String nom = rs.getString("surnom_equipe");
					String logo = rs.getString("logo_equipe");
					String journee = rs.getString("J");
					String bp = rs.getString("Bp");
					String bc = rs.getString("Bc");
					String diff = rs.getString("Diff");
					String points = rs.getString("Points");

					Championnat championnat = new Championnat();
					championnat.setId(idC);
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

				request.setAttribute("championnat", championnatList);

				request.getRequestDispatcher("/championnatId.jsp").forward(request, response);
			} else {
				// connexion SQL
				Statement stmt = conn.createStatement();
				ResultSet rs;

				rs = stmt.executeQuery("SELECT * FROM championnat");
				while (rs.next()) {

					arrList.add(rs.getString("nom_championnat"));
					
					String idC = rs.getString("id_championnat");
					String nom = rs.getString("nom_championnat");
					String logo = rs.getString("logo_championnat");

					Championnat championnat = new Championnat();
					championnat.setId(idC);
					championnat.setNom(nom);
					championnat.setLogo(logo);

					championnatList.add(championnat);

					request.setAttribute("championnat", championnatList);
				}

				request.getRequestDispatcher("/championnat.jsp").forward(request, response);
			}

			System.out.println(championnatList);

			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

}
