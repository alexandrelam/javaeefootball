package org.epsi.java.ee.gallery.model;

public class User {
	private long id;
	private String pseudonym;
	private String email;
	private String password;

	public User() {
	}

	public User(String pseudonym, String email, String password) {
		this.pseudonym = pseudonym;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [pseudonym=" + pseudonym + ", email=" + email + ", password=" + password + "]";
	}
}
