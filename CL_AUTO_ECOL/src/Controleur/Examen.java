package Controleur;

import java.util.Date;

public class Examen {
	
	private int IdExamen;
	private String DATE_ET_HEURE_D_EXAMEN;
	private String Vehicule;
	private String Type_De_Permis;
	public Examen(int idExamen, String dATE_ET_HEURE_D_EXAMEN, String vehicule, String type_De_Permis) {
		super();
		IdExamen = idExamen;
		DATE_ET_HEURE_D_EXAMEN = dATE_ET_HEURE_D_EXAMEN;
		Vehicule = vehicule;
		Type_De_Permis = type_De_Permis;
	}
	public Examen( String dATE_ET_HEURE_D_EXAMEN, String vehicule, String type_De_Permis) {
		super();
		IdExamen = 0;
		DATE_ET_HEURE_D_EXAMEN = dATE_ET_HEURE_D_EXAMEN;
		Vehicule = vehicule;
		Type_De_Permis = type_De_Permis;
	}
	public int getIdExamen() {
		return IdExamen;
	}
	public void setIdExamen(int idExamen) {
		IdExamen = idExamen;
	}
	public String getDATE_ET_HEURE_D_EXAMEN() {
		return DATE_ET_HEURE_D_EXAMEN;
	}
	public void setDATE_ET_HEURE_D_EXAMEN(String dATE_ET_HEURE_D_EXAMEN) {
		DATE_ET_HEURE_D_EXAMEN = dATE_ET_HEURE_D_EXAMEN;
	}
	public String getVehicule() {
		return Vehicule;
	}
	public void setVehicule(String vehicule) {
		Vehicule = vehicule;
	}
	public String getType_De_Permis() {
		return Type_De_Permis;
	}
	public void setType_De_Permis(String type_De_Permis) {
		Type_De_Permis = type_De_Permis;
	}
	

}
