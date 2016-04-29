package org.epsi.java.ee.gallery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.epsi.java.ee.gallery.exception.GalleryException;
import org.epsi.java.ee.gallery.model.User;
import org.epsi.java.ee.gallery.service.UserService;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = -2135238259758756344L;

	@Inject
	public UserService userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/signUp.jsp")
			.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudonym = request.getParameter("pseudonym");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean isCguValidated = "on".equals(request.getParameter("cgu"));

		try {
			User user = userService.create(pseudonym, email, password, isCguValidated);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			request.getRequestDispatcher("/profile.jsp")
				.forward(request, response);
		} catch (GalleryException galleryException) {
			galleryException.printStackTrace();
			request.setAttribute("error", galleryException.getMessage());
			request.getRequestDispatcher("/signUp.jsp")
			.forward(request, response);
		}
	}
}
