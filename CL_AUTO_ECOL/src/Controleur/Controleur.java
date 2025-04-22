package Controleur;

import java.util.ArrayList;


import Modele.Modele;
import Controleur.VCandidat;

public class Controleur {
	/************************* Gestion des candidat ******************/
	public static void insertCandidat (Candidat unCandidat) {
		Modele.insertCandidat(unCandidat);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	
	public static void deleteCandidat(int idCandidat) {
		Modele.deleteCandidat(idCandidat);
	}
	
	public static void updateCandidat(Candidat unCandidat) {
		Modele.updateCandidat(unCandidat);
	}
	
	public static Candidat selectWhereCandidat(int idCandidat) {
		return Modele.selectWhereCandidat(idCandidat);
	}
	
	public static ArrayList<Candidat> selectAllCandidats(){
		return Modele.selectAllCandidats(); 
	}
	public static ArrayList<Candidat> selectLikeCandidats(String filtre){
		return Modele.selectLikeCandidats(filtre); 
	}
	
	public static ArrayList<VCandidat> selectAllVCandidats(){
		return Modele.selectAllVCandidats();
	}
	/************************* Gestion des Responsable ******************/
	public static void insertResponsable (Responsable unResponsable) {
		//on va controler les données avant insertion 
		
		//on apelle le modele pour insertion 
		Modele.insertResponsable(unResponsable);
	}
	
	public static void deleteResponsable(int idResponsable) {
		Modele.deleteResponsable(idResponsable);
	}
	
	public static void updateResponsable(Responsable unResponsable) {
		Modele.updateResponsable(unResponsable);
	}
	
	public static Responsable selectWhereResponsable(int idResponsable) {
		return Modele.selectWhereResponsable(idResponsable);
	}
	
	public static Responsable selectWhereResponsable(String email, String mdp) {
		return Modele.selectWhereResponsable(email, mdp);
	}
	
	public static ArrayList<Responsable> selectAllResponsables(){
		return Modele.selectAllResponsables(); 
	}
	public static ArrayList<Responsable> selectLikeResponsable(String filtre){
		return Modele.selectLikeResponsables(filtre); 
	}
	
	/************************* Gestion des Moniteur ******************/
	public static void insertMoniteur (Moniteur unMoniteur) {
		//on va controler les données avant insertion 
		
		//on apelle le modele pour insertion 
		Modele.insertMoniteur(unMoniteur);
	}
	
	public static void deleteMoniteur(int idMoniteur) {
		Modele.deleteMoniteur(idMoniteur);
	}
	
	public static void updateMoniteur(Moniteur unMoniteur) {
		Modele.updateMoniteur(unMoniteur);
	}
	
	public static Moniteur selectWhereMoniteur(int idMoniteur) {
		return Modele.selectWhereMoniteur(idMoniteur);
	}
	
	public static ArrayList<Moniteur> selectAllMoniteurs(){
		return Modele.selectAllMoniteurs(); 
	}
	public static ArrayList<Moniteur> selectLikeMoniteurs(String filtre){
		return Modele.selectLikeMoniteurs(filtre); 
	}
	
	
	/************************* Gestion des Lecons ******************/
	public static void insertLecon (Lecon unLecon) {
		//on va controler les données avant insertion 
		
		//on apelle le modele pour insertion 
		Modele.insertLecon(unLecon);
	}
	
	public static void deleteLecon(int idLecon) {
		Modele.deleteResponsable(idLecon);
	}
	
	public static void updateLecon(Lecon unLecon) {
		Modele.updateLecon(unLecon);
	}
	
	
	
	public static ArrayList<Lecon> selectAllLecons(){
		return Modele.selectAllLecon(); 
	}
	public static ArrayList<Lecon> selectLikeLecons(String filtre){
		return Modele.selectLikeLecon(filtre); 
	}
	
	/************************* Gestion des EXAMEN ******************/
	public static void insertExamen (Examen unExamen) {
		Modele.insertExamen(unExamen);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	
	public static void deleteExamen(int idExamen) {
		Modele.deleteExamen(idExamen);
	}
	
	public static void updateExamen(Examen unExamen) {
		Modele.updateExamen(unExamen);
	}
	
	public static Examen selectWhereExamen(int idExamen) {
		return Modele.selectWhereExamen(idExamen);
	}
	
	public static ArrayList<Examen> selectAllExamens(){
		return Modele.selectAllExamens(); 
	}
	/************************* Gestion des candidat ******************/
	public static void insertVehicule (Vehicule unVehicule) {
		Modele.insertVehicule(unVehicule);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	
	public static void deleteVehicule(int idVehicule) {
		Modele.deleteVehicule(idVehicule);
	}
	
	public static void updateVehicule(Vehicule unVehicule) {
		Modele.updateVehicule(unVehicule);
	}
	
	public static Vehicule selectWhereVehicule(int idVehicule) {
		return Modele.selectWhereVehicule(idVehicule);
	}
	
	public static ArrayList<Vehicule> selectAllVehicules(){
		return Modele.selectAllVehicule(); 
	}
	public static ArrayList<Vehicule> selectLikeVehicules(String filtre){
		return Modele.selectLikeVehicule(filtre);
				
	}
	/************************* Gestion des plannings ******************/
	public static void insertPlannings (Plannings unPlannings) {
		Modele.insertPlanning(unPlannings);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	
	public static void deletePlannings(int idPlannings) {
		Modele.deletePlanning(idPlannings);
	}
	
	public static void updatePlannings(Plannings unPlannings) {
		Modele.updatePlanning(unPlannings);
	}
	
	
	public static ArrayList<Plannings> selectAllPlannings(){
		return Modele.selectAllPlannings(); 
	}
	public static ArrayList<Plannings> selectLikePlannings(String filtre){
		return Modele.selectLikePlanning(filtre); 
	}
	/************************* Gestion des formule ******************/
	public static void insertFormule (Formule unFormule) {
		Modele.insertFormule(unFormule);
		//on va controler les données avant insertion 
		//on apelle le modele pour insertion 
			}
	
	public static void deleteFormule(int idFormule) {
		Modele.deleteFormule(idFormule);
	}
	
	public static void updateFormule(Formule unFormule) {
		Modele.updateFormule(unFormule);
	}
	
	public static Formule selectWhereFormule(int idFormule) {
		return Modele.selectWhereFormule(idFormule);
	}
	
	public static ArrayList<Formule> selectAllFormules(){
		return Modele.selectAllFormules(); 
	}
	public static ArrayList<Formule> selectLikeFormules(String filtre){
		return Modele.selectLikeFormules(filtre); 
	}
	
	/************************* Gestion des vehicules ******************/
	public static void insertvehicule (Vehicule unVehicule) {
		//on va controler les données avant insertion 
		
		//on apelle le modele pour insertion 
		Modele.insertVehicule(unVehicule);
	}
	
	public static void deletevehicule(int idVehicule) {
		Modele.deleteVehicule(idVehicule);
	}
	
	public static void updatevehicule(Vehicule unVehicule) {
		Modele.updateVehicule(unVehicule);
	}
	
	
	
	public static ArrayList<Vehicule> selectAllLVehicule(){
		return Modele.selectAllVehicule(); 
	}
	public static ArrayList<Vehicule> selectLikeVehicule(String filtre){
		return Modele.selectLikeVehicule(filtre); 
	}

	public static ArrayList<Examen> selectLikeExamens(String filtre) {
		return Modele.selectLikeExamens(filtre);
	}
	public static int count(String table) {
		return Modele.count(table);
	}
	
}
