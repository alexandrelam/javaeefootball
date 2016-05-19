package org.epsi.java.ee.football.model;

public class Championnnat {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getJournee() {
		return journee;
	}
	public void setJournee(String journee) {
		this.journee = journee;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	public String getBc() {
		return bc;
	}
	public void setBc(String bc) {
		this.bc = bc;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	private String id;
	private String nom;
	private String logo;
	private String journee;
	private String bp;
	private String bc;
	private String diff;
	private String points;
	
	public String foo() {

		String prix = "5";
		// Do something here
		return prix;
	}
}
