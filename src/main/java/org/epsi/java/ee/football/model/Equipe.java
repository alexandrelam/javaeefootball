package org.epsi.java.ee.football.model;

public class Equipe {
	
	private long idEquipe;
	private String nomEquipe;
	private String surnomEquipe;
	private String abrEquipe;
	private String logoEquipe;
	private String maillotDom;
	private String maillotExt;
	private String maillotTrois;
	private String nomStade;
	private String capaciteStade;
	private String idChampionnat;
	
	public Equipe(long idEquipe, String nomEquipe, String surnomEquipe, String abrEquipe, String logoEquipe,
			String maillotDom, String maillotExt, String maillotTrois, String nomStade, String capaciteStade,
			String idChampionnat) {
		super();
		this.idEquipe = idEquipe;
		this.nomEquipe = nomEquipe;
		this.surnomEquipe = surnomEquipe;
		this.abrEquipe = abrEquipe;
		this.logoEquipe = logoEquipe;
		this.maillotDom = maillotDom;
		this.maillotExt = maillotExt;
		this.maillotTrois = maillotTrois;
		this.nomStade = nomStade;
		this.capaciteStade = capaciteStade;
		this.idChampionnat = idChampionnat;
	}

	public long getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String getSurnomEquipe() {
		return surnomEquipe;
	}

	public void setSurnomEquipe(String surnomEquipe) {
		this.surnomEquipe = surnomEquipe;
	}

	public String getAbrEquipe() {
		return abrEquipe;
	}

	public void setAbrEquipe(String abrEquipe) {
		this.abrEquipe = abrEquipe;
	}

	public String getLogoEquipe() {
		return logoEquipe;
	}

	public void setLogoEquipe(String logoEquipe) {
		this.logoEquipe = logoEquipe;
	}

	public String getMaillotDom() {
		return maillotDom;
	}

	public void setMaillotDom(String maillotDom) {
		this.maillotDom = maillotDom;
	}

	public String getMaillotExt() {
		return maillotExt;
	}

	public void setMaillotExt(String maillotExt) {
		this.maillotExt = maillotExt;
	}

	public String getMaillotTrois() {
		return maillotTrois;
	}

	public void setMaillotTrois(String maillotTrois) {
		this.maillotTrois = maillotTrois;
	}

	public String getNomStade() {
		return nomStade;
	}

	public void setNomStade(String nomStade) {
		this.nomStade = nomStade;
	}

	public String getCapaciteStade() {
		return capaciteStade;
	}

	public void setCapaciteStade(String capaciteStade) {
		this.capaciteStade = capaciteStade;
	}

	public String getIdChampionnat() {
		return idChampionnat;
	}

	public void setIdChampionnat(String idChampionnat) {
		this.idChampionnat = idChampionnat;
	}

	

}
