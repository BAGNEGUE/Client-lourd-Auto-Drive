package Controleur;

import java.awt.Color;

import Modele.Modele;
import Vue.VueConnexion;
import Vue.VueGenerale;

public class PPEAUTO {

	
	
	
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale ; 
	private static Responsable respConnecte;
	private static Color couleur = new Color(200, 220, 255);
	
	public static Color getCouleur() {
		return couleur;
	}

	public static void setCouleur(Color couleur) {
		PPEAUTO.couleur = couleur;
	}

	public static Responsable getRespConnecte() {
		return respConnecte;
	}

	public static void setRespConnecte(Responsable respConnecte) {
		PPEAUTO.respConnecte = respConnecte;
	}

	public static void main(String[] args) {
		
		uneVueConnexion = new VueConnexion(); 

	}

	public static void creerVueGenerale (boolean action) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}
	public static void rendreVisible (boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
	