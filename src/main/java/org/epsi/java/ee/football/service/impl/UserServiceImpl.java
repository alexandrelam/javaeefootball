package org.epsi.java.ee.football.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.epsi.java.ee.football.dao.UserDao;
import org.epsi.java.ee.football.exception.GalleryException;
import org.epsi.java.ee.football.model.User;
import org.epsi.java.ee.football.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	private Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	@Inject
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User create(String pseudonym, String email, String password, boolean isCguValidated) {
		if (pseudonym == null || pseudonym.length() < 1 || pseudonym.length() > 10) {
			throw new GalleryException("Pseudonyme invalide");
		}

		if (email == null) {
			throw new GalleryException("L'email doit être remplit");
		}

		Matcher matcher = emailPattern.matcher(email);

		if (!matcher.matches()) {
			throw new GalleryException("Le format de l'email est invalide");
		}

		if (password.length() < 8 || password.length() > 30) {
			throw new GalleryException("Le mot de passe doit contenir entre 8 et 30 caractères");
		}

		if (!isCguValidated) {
			throw new GalleryException("Les CGU doivent être validé");
		}

		return userDao.create(new User(pseudonym, email, password));
	}

}
