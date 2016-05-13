package org.epsi.java.ee.football.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.epsi.java.ee.football.dao.ChampionnatDao;
import org.epsi.java.ee.football.exception.GalleryException;
import org.epsi.java.ee.football.model.Championnat;

import com.mysql.jdbc.Driver;

public class ChampionnatDaoImpl implements ChampionnatDao {
	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public ChampionnatDaoImpl() throws SQLException {
		DriverManager.registerDriver(new Driver());
	}

	@Override
	public Championnat create(Championnat newChampionnat) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "INSERT INTO championnat (nom_championnat, logo_championnat, pays_championnat, nb_equipe, pts_victoire, pts_nul, pts_defaite, regle_classement) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newChampionnat.getNomChampionnat());
			stmt.setString(2, newChampionnat.getLogoChampionnat());
			stmt.setString(3, newChampionnat.getPaysChampionnat());
			stmt.setString(4, newChampionnat.getNbEquipe());
			stmt.setString(5, newChampionnat.getPtsVictoire());
			stmt.setString(6, newChampionnat.getPtsNul());
			stmt.setString(7, newChampionnat.getPtsDefaite());
			stmt.setString(8, newChampionnat.getRegleClassement());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				newChampionnat.setIdChampionnat(rs.getLong(1));
				return newChampionnat;
			}

			// cannot be here
			throw new GalleryException("Championnat was not inserted in database due to an unknown error");

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	@Override
	public Championnat read(long id) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "SELECT * FROM championnat where id_championnat= ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet results = stmt.executeQuery();

			if (results.next()) {
				Championnat championnat = new Championnat();
				championnat.setIdChampionnat(results.getLong("id_championnat"));
				championnat.setNomChampionnat(results.getString("nom_championnat"));
				championnat.setLogoChampionnat(results.getString("logo_championnat"));
				championnat.setPaysChampionnat(results.getString("pays_championnat"));
				championnat.setNomChampionnat(results.getString("nb_equipe"));
				championnat.setLogoChampionnat(results.getString("pts_victoire"));
				championnat.setPaysChampionnat(results.getString("pts_nul");
				championnat.setLogoChampionnat(results.getString("pts_defaite"));
				championnat.setPaysChampionnat(results.getString("regle_classement");
				return championnat;
			}

			return null;

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	@Override
	public void update(User user) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "UPDATE user SET pseudonym=?, email=?, password=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getPseudonym());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setLong(4, user.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	@Override
	public void delete(long id) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "DELETE FROM user WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DATABASE, MYSQL_USER, MYSQL_PWD);
	}

	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Error while closing the connection with the database");
			}
		}
	}
}
