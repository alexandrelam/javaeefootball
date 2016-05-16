package org.epsi.java.ee.football.service;

import org.epsi.java.ee.football.model.User;

public interface UserAllService {

	/**
	 * Validate data given in parameter, create a user with, hash the password and store the user.
	 *
	 * @param pseudonym: The user's pseudonym
	 * @param email: The user's email
	 * @param password: The user password not hashed
	 * @param isCguValidated: true if the cgu are validated, false otherwise
	 * @return The user created
	 */
	User readAll();

}
