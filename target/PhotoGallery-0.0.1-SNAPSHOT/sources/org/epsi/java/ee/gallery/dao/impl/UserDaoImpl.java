package org.epsi.java.ee.gallery.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.epsi.java.ee.gallery.dao.UserDao;
import org.epsi.java.ee.gallery.exception.GalleryException;
import org.epsi.java.ee.gallery.model.User;

import com.mysql.jdbc.Driver;

public class UserDaoImpl implements UserDao {
	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "gallery";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public UserDaoImpl() throws SQLException {
		DriverManager.registerDriver(new Driver());
	}

	@Override
	public User create(User newUser) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "INSERT INTO user (pseudonym, email, password) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newUser.getPseudonym());
			stmt.setString(2, newUser.getEmail());
			stmt.setString(3, newUser.getPassword());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				newUser.setId(rs.getLong(1));
				return newUser;
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
	public User read(long id) {
		Connection conn = null;

		try {
			conn = getConnection();
			String query = "SELECT * FROM user where id= ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet results = stmt.executeQuery();

			if (results.next()) {
				User user = new User();
				user.setId(results.getLong("id"));
				user.setPseudonym(results.getString("pseudonym"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
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
