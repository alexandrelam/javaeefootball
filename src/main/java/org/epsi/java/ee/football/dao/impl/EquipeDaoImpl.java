package org.epsi.java.ee.football.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.epsi.java.ee.football.dao.EquipeDao;
import org.epsi.java.ee.football.exception.GalleryException;
import org.epsi.java.ee.football.model.Equipe;

import com.mysql.jdbc.Driver;

public class EquipeDaoImpl implements EquipeDao {
	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "francefoot";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public EquipeDaoImpl() throws SQLException {
		DriverManager.registerDriver(new Driver());
	}

	@Override
	public Equipe create(Equipe newEquipe) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "INSERT INTO user (pseudonym, email, password) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				newEquipe.setIdEquipe(rs.getLong(1));
				return newEquipe;
			}

			// cannot be here
			throw new GalleryException("User was not inserted in database due to an unknown error");

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	@Override
	public Equipe read(long id) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "SELECT * FROM user where id= ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet results = stmt.executeQuery();

			if (results.next()) {
				Equipe user = new Equipe();
				user.setIdEquipe(results.getLong("id"));
				user.setNomEquipe(results.getString("nom_equipe"));
				return user;
			}

			return null;

		} catch (SQLException e) {
			throw new GalleryException("Something went wrong when calling database:" + e.getMessage(), e);
		} finally {
			closeConnection(conn);
		}
	}

	@Override
	public void update(Equipe equipe) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "UPDATE user SET pseudonym=?, email=?, password=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setLong(4, equipe.getIdEquipe());
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
