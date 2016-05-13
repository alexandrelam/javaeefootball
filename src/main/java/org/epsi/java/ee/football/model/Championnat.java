package org.epsi.java.ee.football.model;

public class Championnat {
	
	private long idChampionnat;
	private String nomChampionnat;
	private String logoChampionnat;
	private String paysChampionnat;
	private String nbEquipe;
	private String ptsVictoire;
	private String ptsNul;
	private String ptsDefaite;
	private String regleClassement;
	
	public Championnat(long idChampionnat, String nomChampionnat, String logoChampionnat, String paysChampionnat,
			String nbEquipe, String ptsVictoire, String ptsNul, String ptsDefaite, String regleClassement) {
		super();
		this.idChampionnat = idChampionnat;
		this.nomChampionnat = nomChampionnat;
		this.logoChampionnat = logoChampionnat;
		this.paysChampionnat = paysChampionnat;
		this.nbEquipe = nbEquipe;
		this.ptsVictoire = ptsVictoire;
		this.ptsNul = ptsNul;
		this.ptsDefaite = ptsDefaite;
		this.regleClassement = regleClassement;
	}

	public long getIdChampionnat() {
		return idChampionnat;
	}

	public void setIdChampionnat(long idChampionnat) {
		this.idChampionnat = idChampionnat;
	}

	public String getNomChampionnat() {
		return nomChampionnat;
	}

	public void setNomChampionnat(String nomChampionnat) {
		this.nomChampionnat = nomChampionnat;
	}

	public String getLogoChampionnat() {
		return logoChampionnat;
	}

	public void setLogoChampionnat(String logoChampionnat) {
		this.logoChampionnat = logoChampionnat;
	}

	public String getPaysChampionnat() {
		return paysChampionnat;
	}

	public void setPaysChampionnat(String paysChampionnat) {
		this.paysChampionnat = paysChampionnat;
	}

	public String getNbEquipe() {
		return nbEquipe;
	}

	public void setNbEquipe(String nbEquipe) {
		this.nbEquipe = nbEquipe;
	}

	public String getPtsVictoire() {
		return ptsVictoire;
	}

	public void setPtsVictoire(String ptsVictoire) {
		this.ptsVictoire = ptsVictoire;
	}

	public String getPtsNul() {
		return ptsNul;
	}

	public void setPtsNul(String ptsNul) {
		this.ptsNul = ptsNul;
	}

	public String getPtsDefaite() {
		return ptsDefaite;
	}

	public void setPtsDefaite(String ptsDefaite) {
		this.ptsDefaite = ptsDefaite;
	}

	public String getRegleClassement() {
		return regleClassement;
	}

	public void setRegleClassement(String regleClassement) {
		this.regleClassement = regleClassement;
	}

	
	
}
