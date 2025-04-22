package Controleur;

public class Vehicule {
	private int IdVehicule;
	private String Marque ;
	private String Modele ;
	private String Immatriculation;
	public Vehicule(int idVehicule, String marque, String modele, String immatriculation) {
		super();
		IdVehicule = idVehicule;
		Modele = marque;
		Marque = modele;
		Immatriculation = immatriculation;
	}
	
	public Vehicule( String marque, String modele, String immatriculation) {
		super();
		IdVehicule = 0;
		Marque = marque;
		Modele = modele;
		Immatriculation = immatriculation;
	}

	public int getIdVehicule() {
		return IdVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		IdVehicule = idVehicule;
	}


	public String getMarque() {
		return Marque;
	}

	public void setMarque(String marque) {
		Marque = marque;
	}


	public String getModele() {
		return Modele;
	}

	public void setModele(String modele) {
		Modele = modele;
	}
	public String getImmatriculation() {
		return Immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		Immatriculation = immatriculation;
	}

}
