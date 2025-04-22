package Controleur;

import java.util.Date;

public class Plannings {
	private int idPlannings;
	private int IdLecon ;
	private int IdCandidat;
	private String DATEHEURDEBUT;
	private int idMoniteur;
	private String  DATEHEURFIN ;
	private  String Etat;
	public Plannings(int idPlannings, int idLecon, int idCandidat, String dATEHEURDEBUT, int idMoniteur, String dATEHEURFIN,
			String etat) {
		super();
		this.idPlannings = idPlannings;
		IdLecon = idLecon;
		IdCandidat = idCandidat;
		DATEHEURDEBUT = dATEHEURDEBUT;
		this.idMoniteur = idMoniteur;
		DATEHEURFIN = dATEHEURFIN;
		Etat = etat;
	}
	

	public Plannings( int idLecon, int idCandidat, String dATEHEURDEBUT, int idMoniteur, String dATEHEURFIN,
			String etat) {
		super();
		this.idPlannings = 0;
		IdLecon = idLecon;
		IdCandidat = idCandidat;
		DATEHEURDEBUT = dATEHEURDEBUT;
		this.idMoniteur = idMoniteur;
		DATEHEURFIN = dATEHEURFIN;
		Etat = etat;
	}


	public int getIdPlannings() {
		return idPlannings;
	}


	public void setIdPlannings(int idPlannings) {
		this.idPlannings = idPlannings;
	}


	public int getIdLecon() {
		return IdLecon;
	}


	public void setIdLecon(int idLecon) {
		IdLecon = idLecon;
	}


	public int getIdCandidat() {
		return IdCandidat;
	}


	public void setIdCandidat(int idCandidat) {
		IdCandidat = idCandidat;
	}


	public String getDATEHEURDEBUT() {
		return DATEHEURDEBUT;
	}


	public void setDATEHEURDEBUT(String dATEHEURDEBUT) {
		DATEHEURDEBUT = dATEHEURDEBUT;
	}


	public int getIdMoniteur() {
		return idMoniteur;
	}


	public void setIdMoniteur(int idMoniteur) {
		this.idMoniteur = idMoniteur;
	}


	public String getDATEHEURFIN() {
		return DATEHEURFIN;
	}


	public void setDATEHEURFIN(String dATEHEURFIN) {
		DATEHEURFIN = dATEHEURFIN;
	}


	public String getEtat() {
		return Etat;
	}


	public void setEtat(String etat) {
		Etat = etat;
	}
	

}
