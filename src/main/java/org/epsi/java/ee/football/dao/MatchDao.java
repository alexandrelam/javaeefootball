package org.epsi.java.ee.football.dao;

import org.epsi.java.ee.football.model.Match;

public interface MatchDao {

	/**
	 * Insert a User in database
	 *
	 * @param newUser: The user to insert. Must not be null.
	 * @return The user inserted
	 */
	Match create(Match newUser);

	/**
	 * Retrieve a User from database
	 *
	 * @param id: The id of the user to retrieved
	 * @return The user with the id given in parameter, null otherwise.
	 */
	Match read(long id);

	/**
	 * Update user information in database based on user id.
	 *
	 * @param user: The user to update. Must not be null.
	 */
	void update(Match match);

	/**
	 * Remove a user from database.
	 *
	 * @param id: The id of the user to remove.
	 */
	void delete(long id);
}