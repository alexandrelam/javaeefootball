package org.epsi.java.ee.gallery.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.epsi.java.ee.gallery.dao.impl.UserDaoImpl;
import org.epsi.java.ee.gallery.model.User;
import org.junit.After;
import org.junit.Test;

public class UserDaoTest {
	private static final String MYSQL_HOST = "localhost";
	private static final String MYSQL_PORT = "3306";
	private static final String MYSQL_DATABASE = "gallery";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "";

	public static final String DEFAULT_PSEUDONYM = "Robert";
	public static final String DEFAULT_EMAIL = "robert@mail.fr";
	public static final String DEFAULT_PASSWORD = "AZERTYUI";

	UserDaoImpl dao;

	public UserDaoTest() throws SQLException {
		dao = new UserDaoImpl();
	}

	@After
	public void tearDown() throws SQLException {
		Connection connection = null;
		try {
			connection = dao.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("TRUNCATE TABLE user");

		} finally {
			dao.closeConnection(connection);
		}
	}

	@Test
	public void createShouldInsertANewUserInDatabase() {
		User actualUser = dao.create(new User(DEFAULT_PSEUDONYM, DEFAULT_EMAIL, DEFAULT_PASSWORD));
		List<User> results = null;
		try {
			results = getUserContent();

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Get an error from database: " + e.getMessage());
		}

		assertEquals(1, results.size());

		User user = results.get(0);
		assertEquals(DEFAULT_PSEUDONYM, user.getPseudonym());
		assertEquals(DEFAULT_EMAIL, user.getEmail());
		assertEquals(DEFAULT_PASSWORD, user.getPassword());
		assertEquals(user.getId(), actualUser.getId());
	}

	@Test
	public void createShouldEscapeData() {
		User actualUser = dao.create(new User(DEFAULT_PSEUDONYM, "','')-- ", DEFAULT_PASSWORD));
		List<User> results = null;
		try {
			results = getUserContent();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Get an error from database: " + e.getMessage());
		}
		assertEquals(1, results.size());
		User user = results.get(0);
		assertEquals(DEFAULT_PSEUDONYM, user.getPseudonym());
		assertEquals("','')-- ", user.getEmail());
		assertEquals(DEFAULT_PASSWORD, user.getPassword());
		assertEquals(user.getId(), actualUser.getId());
	}

	@Test
	public void readShouldReturnAUserFromDatabase() throws SQLException {
		insertDefaultUser();

		User user = dao.read(1);

		assertEquals(1, user.getId());
		assertEquals(DEFAULT_PSEUDONYM, user.getPseudonym());
		assertEquals(DEFAULT_EMAIL, user.getEmail());
		assertEquals(DEFAULT_PASSWORD, user.getPassword());
	}

	@Test
	public void readShouldReturnNullIfNoUserFound() throws SQLException {
		User user = dao.read(1);

		assertNull(user);
	}

	@Test
	public void updateShouldUpdateAUserInDatabase() throws SQLException {
		insertDefaultUser();

		User expectedUser = new User();
		expectedUser.setId(1);
		expectedUser.setPseudonym("Gerard");
		expectedUser.setEmail("gerard@mail.fr");
		expectedUser.setPassword("12345678");

		dao.update(expectedUser);

		List<User> results = null;
		try {
			results = getUserContent();

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Get an error from database: " + e.getMessage());
		}

		assertEquals(1, results.size());

		User user = results.get(0);
		assertEquals(1, user.getId());
		assertEquals("Gerard", user.getPseudonym());
		assertEquals("gerard@mail.fr", user.getEmail());
		assertEquals("12345678", user.getPassword());
	}

	@Test
	public void deleteShouldDeleteAUserFromDatabase() throws SQLException {
		insertDefaultUser();

		dao.delete(1);

		List<User> results = null;
		try {
			results = getUserContent();

		} catch (SQLException e) {
			e.printStackTrace();
			fail("Get an error from database: " + e.getMessage());
		}

		assertEquals(0, results.size());
	}

	private void insertDefaultUser() throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		statement.execute("INSERT INTO user (id, pseudonym, email, password) VALUES (1,'" + DEFAULT_PSEUDONYM + "','" + DEFAULT_EMAIL + "','"
				+ DEFAULT_PASSWORD + "')");
	}

	private List<User> getUserContent() throws SQLException {
		List<User> users = new ArrayList<>();

		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select * from user");

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong("id"));
				user.setPseudonym(resultSet.getString("pseudonym"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));

				users.add(user);
			}

			return users;

		} finally {
			closeConnection(connection);
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
