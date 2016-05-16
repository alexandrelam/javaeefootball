package org.epsi.java.ee.football.dao;

import org.epsi.java.ee.football.model.Equipe;

public interface EquipeDao {

	/**
	 * Insert a User in database
	 *
	 * @param newUser: The user to insert. Must not be null.
	 * @return The user inserted
	 */
	Equipe create(Equipe newUser);

	/**
	 * Retrieve a User from database
	 *
	 * @param id: The id of the user to retrieved
	 * @return The user with the id given in parameter, null otherwise.
	 */
	Equipe read(long id);

	/**
	 * Update user information in database based on user id.
	 *
	 * @param user: The user to update. Must not be null.
	 */
	void update(Equipe equipe);

	/**
	 * Remove a user from database.
	 *
	 * @param id: The id of the user to remove.
	 */
	void delete(long id);
}