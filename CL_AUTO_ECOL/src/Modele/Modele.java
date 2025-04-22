
package Modele;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.Candidat;
import Controleur.Examen;
import Controleur.Formule;

import Controleur.Lecon;

import Controleur.Moniteur;

import Controleur.Plannings;
import Controleur.Responsable;
import Controleur.VCandidat;
import Controleur.Vehicule;
import Controleur.VCandidat;

public class Modele {
	private static Connexion uneConnexion=new Connexion("localhost"," auto_ecole_251","root","");
	
	public Modele(String string, int i) {
		// TODO Auto-generated constructor stub
	}




	//requete sur les view
		public static ArrayList<VCandidat> selectAllVCandidats(){
			String requete = "select * from listeCandidats;"; 
			 ArrayList<VCandidat> lesVCandidats =new ArrayList<VCandidat>();
			try {
				uneConnexion.seConnecter();
				Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
				ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
				//parcours des resultats et extraction un Candidat 
				while (lesResultats.next()) {
					//instanciation d'un Candidat 
					VCandidat unVCandidat= new  VCandidat (
							lesResultats.getString("Nom"),  
							lesResultats.getString("Prenom"), lesResultats.getString("Age"),
							lesResultats.getString("Email"),
							lesResultats.getString("Type_User"),lesResultats.getString("Numero_Telephone")
							); 
					 lesVCandidats.add(unVCandidat);
				}
				unStat.close();
				uneConnexion.seDeConnecter();
			}
			catch (SQLException exp) {
				System.out.println("Erreur d'execution de la requete : " + requete);
			}
			return lesVCandidats ;
		}
		

		
	
	/**************************** Gestion des candidats ********************/
	public static void insertCandidat (Candidat unCandidat) {
		String requete ="insert into Candidat values (null,'" + unCandidat.getNom ()
		+ "','" + unCandidat.getPrenom() + "','"+ unCandidat.getAge() +"','"+ unCandidat.getEmail() 
		+  "','" + unCandidat.getMdp() + "','" + unCandidat.getType_User() 
		+ "','" + unCandidat.getNumero_Telephone() + "');";
		executerRequete (requete); 
	}
	public static void deleteCandidat(int IdCandidat) {
		String requete ="delete from candidat where IdCandidat = " + IdCandidat +";";
		executerRequete (requete); 
	}
	public static void updateCandidat (Candidat unCandidat) {
		String requete ="update candidat set nom = '" + unCandidat.getNom() 
		+"', prenom = '" + unCandidat.getPrenom() + "', Age = '" + unCandidat.getAge() 
		+ "', Email = '" + unCandidat.getEmail() + "', Mdp = '" + unCandidat.getMdp() 
		+"',Type_User='" +unCandidat.getType_User()+"',Numero_Telephone='" +unCandidat.getNumero_Telephone()
		+" where idcandidat = " +unCandidat.getIdCandidat()+";";
		executerRequete (requete); 
	}
	
	public static Candidat selectWhereCandidat(int IdCandidat) {
		String requete = "select * from Candidat where IdCandidat = " + IdCandidat +";"; 
		Candidat unCandidat = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un candidat 
			if (lesResultats.next()) {
				//instanciation d'un candidat 
				 unCandidat  = new Candidat (
						lesResultats.getInt("IdCandidat"), lesResultats.getString("Nom"), 
						lesResultats.getString("Prenom"), lesResultats.getString("Age"),
						lesResultats.getString("Email"),lesResultats.getString("Mdp"),
						lesResultats.getString("Type_User"),lesResultats.getString("Numero_Telephone")
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unCandidat ;
	}
	
	public static ArrayList<Candidat> selectAllCandidats (){
		String requete = "select * from candidat ;"; 
		ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les candidat 
			while (lesResultats.next()) {
				//instanciation d'un Candidat 
				 Candidat unCandidat = new Candidat (
						 lesResultats.getInt("idCandidat"), 
					    lesResultats.getString("Nom"), 
						lesResultats.getString("Prenom"), lesResultats.getString("Age"),
						lesResultats.getString("Email"),lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone") 
						
						); 
				// ajout du candidat dans lesCandidats 
				 lesCandidats.add(unCandidat);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesCandidats ;
	}
	
	public static ArrayList<Candidat> selectLikeCandidats (String filtre){
		String requete = "select * from candidat where Nom like '%" + filtre +"%' or "
				+ " Prenom like '%" + filtre +"%' or "
				+ " Age like '%" + filtre +"%' or "
				+ " Email like '%" + filtre +"%' or "
				+ " Type_User like '%" + filtre +"%' ; ";
		
		ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les client 
			while (lesResultats.next()) {
				//instanciation d'un Candidat 
				 Candidat unCandidat = new Candidat (
						 lesResultats.getInt("idCandidat"), 
					    lesResultats.getString("Nom"), 
						lesResultats.getString("Prenom"), lesResultats.getString("Age"),
						lesResultats.getString("Email"),lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone") 
						
						); 
				// ajout du candidat dans lesCandidats 
				 lesCandidats.add(unCandidat);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesCandidats ;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**************************** Gestion des Plannings********************/
	public static void insertPlanning (Plannings unPlannings) {
		String requete ="insert into Planning values (null, '" + unPlannings.getIdPlannings()
		+"','" + unPlannings.getIdLecon() + "','" + unPlannings.getIdCandidat() 
		+ "','" + unPlannings.getDATEHEURDEBUT()
		+ "','" + unPlannings.getIdMoniteur()
		+ "','" + unPlannings.getDATEHEURFIN() 
		+ "','" + unPlannings.getEtat() + "');";
		executerRequete (requete); 
	}
	public static void deletePlanning(int idPlannings) {
		String requete ="delete from Planning where idPlannings = " + idPlannings +";";
		executerRequete (requete); 
	}
	
	public static void updatePlanning (Plannings unPlannings) {
		String requete ="update Plannings set IdLecon= '" + unPlannings.getIdLecon()
		+"', IdCandidat = '" + unPlannings.getIdCandidat() 
		+"', Dateheurdebut= '" + unPlannings.getDATEHEURDEBUT()
		+"', IdMoniteur =  '" + unPlannings.getIdMoniteur()
		+"', Dateheurfin = '" + unPlannings.getDATEHEURFIN()
		+"', Etat = '" + unPlannings.getEtat()
        + " where IdPlanning = " + unPlannings.getIdPlannings()+";";
		executerRequete (requete); 
	}
	
	public static Plannings selectPlanning(int IdPlannings) {
		String requete = "select * from Planning where idPlanning = " + IdPlannings +";"; 
		Plannings unPlannings = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction d un Planning 
			if (lesResultats.next()) {
				//instanciation d'un Planning 
				 unPlannings = new Plannings (
						lesResultats.getInt("idPlanning"), lesResultats.getInt("IdLecon"), 
						lesResultats.getInt("IdCandidat"),lesResultats.getString("Dateheurdebut"),
						lesResultats.getInt("IdMoniteur"),
						lesResultats.getString("Dateheurfin"),lesResultats.getString("Etat")
					); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unPlannings ;
	}
	
	
	public static ArrayList<Plannings> selectAllPlannings (){
		String requete = "select * from Plannings ;"; 
		ArrayList<Plannings> lesPlannings= new ArrayList<Plannings>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les plannings 
			while (lesResultats.next()) {
				//instanciation d'un Planning 
				Plannings unPlannings = new Plannings (
						lesResultats.getInt("idPlanning"), lesResultats.getInt("IdLecon"), 
						lesResultats.getInt("IdCandidat"), lesResultats.getString("Dateheurdebut"),
						lesResultats.getInt("IdMoniteur"), lesResultats.getString("Dateheurfin"),
						lesResultats.getString("Etat")
						); 
				System.out.println(unPlannings.getEtat());
				// ajout du Planning dans lesPlanning 
				 lesPlannings.add(unPlannings);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesPlannings ;
	}
	
	public static ArrayList<Plannings> selectLikePlanning (String filtre){
		String requete = "select * from Plannings where Dateheurdebut '%" + filtre +"%' or "
	                  + " Dateheurfin'%" + filtre +"%' ; ";
		
		ArrayList<Plannings> lesPlannings = new ArrayList<Plannings>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Plannings 
			while (lesResultats.next()) {
				//instanciation d'une Planning 
				Plannings unPlannings = new Plannings (
						lesResultats.getInt("idPlanning"), lesResultats.getInt("IdLecon"), 
						lesResultats.getInt("IdCandidat"), lesResultats.getString("Dateheurdebut"),
						lesResultats.getInt("IdMoniteur"), lesResultats.getString("Dateheurfin"),
						lesResultats.getString("Etat")
						); 
				// ajout d une  dans lesPlannings 
				 lesPlannings.add(unPlannings);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesPlannings ;
	}
	
	

	/**************************** Gestion des Lecons********************/
	public static void insertLecon (Lecon uneLecon) {
		String requete ="insert into Lecon values (null, '" +uneLecon.getTYPE_DE_LECON() + "','" + uneLecon.getDescription() 
		+ "','" + uneLecon.getTitre() + "');";
		executerRequete (requete); 
	}
	public static void deleteLecon(int idLecon) {
		String requete ="delete from Lecon where idLecon = " + idLecon +";";
		executerRequete (requete); 
	}
	
	public static void updateLecon (Lecon uneLecon) {
		String requete ="update Lecon set TYPE_DE_LECON = '" + uneLecon.getTYPE_DE_LECON()
		+"', Description = '" + uneLecon.getDescription() + "', Titre = '" + uneLecon.getTitre() 
		+ " where IdLecon = " + uneLecon.getIdLecon()+";";
		executerRequete (requete); 
	}
	
	public static Lecon selectLecon(int IdLecon) {
		String requete = "select * from Lecon where idLecon = " + IdLecon +";"; 
		Lecon uneLecon = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction une lecon
			if (lesResultats.next()) {
				//instanciation d'un lecon 
				 uneLecon = new Lecon (
						lesResultats.getInt("idLecon"), lesResultats.getString("Type_De_Lecon"), 
						lesResultats.getString("Description"),lesResultats.getString("Titre")
					); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneLecon ;
	}
	
	
	public static ArrayList<Lecon> selectAllLecon (){
		String requete = "select * from Lecon ;"; 
		ArrayList<Lecon> lesLecons= new ArrayList<Lecon>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Responsables 
			while (lesResultats.next()) {
				//instanciation d'un Responsable 
				Lecon uneLecon = new Lecon (
						lesResultats.getInt("idLecon"), lesResultats.getString("Type_De_Lecon"), 
						lesResultats.getString("Description"), lesResultats.getString("TItre")
						); 
				// ajout du Responsable dans lesResponsable 
				 lesLecons.add(uneLecon);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesLecons ;
	}
	
	public static ArrayList<Lecon> selectLikeLecon (String filtre){
		String requete = "select * from Lecon where Type_De_Lecon like '%" + filtre +"%' or "
	                  + " Titre '%" + filtre +"%' ; ";
		
		ArrayList<Lecon> lesLecons = new ArrayList<Lecon>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Lecons 
			while (lesResultats.next()) {
				//instanciation d'une Lecon 
				Lecon uneLecon = new Lecon (
						lesResultats.getInt("idLecon"), lesResultats.getString("Type_De_Lecon"), 
						lesResultats.getString("Description"),lesResultats.getString("Titre")
						); 
				// ajout d une lecon dans lesLecons 
				 lesLecons.add(uneLecon);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesLecons ;
	}
	
	
	
	
	
	/**************************** Gestion des Vehicules********************/
	public static void insertVehicule (Vehicule unVehicule) {
		String requete ="insert into Vehicule values (null,'" + unVehicule.getMarque() + "','" + unVehicule.getModele() 
		+ "','" + unVehicule.getImmatriculation() + "');";
		executerRequete (requete); 
	}
	public static void deleteVehicule(int idVehicule) {
		String requete ="delete from Vehicule where idVehicule = " + idVehicule +";";
		executerRequete (requete); 
	}
	
	public static void updateVehicule (Vehicule unVehicule) {
		String requete ="update Vehicule set Marque = '" + unVehicule.getMarque()
		+"', Modele = '" + unVehicule.getModele() + "', IMMATRICULATION = '" + unVehicule.getImmatriculation() 
		+ " where IdVehicule = " + unVehicule.getIdVehicule()+";";
		executerRequete (requete); 
	}
	
	public static Vehicule selectWhereVehicule(int IdVehicule) {
		String requete = "select * from Vehicule where idVehiclue = " + IdVehicule +";"; 
		Vehicule unVehicule = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Vehicule 
			if (lesResultats.next()) {
				//instanciation d'un Vehicule 
				 unVehicule = new Vehicule (
						lesResultats.getInt("idVehicule"), lesResultats.getString("Marque"), 
						lesResultats.getString("Modele"),lesResultats.getString("Immatriculation")
					); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unVehicule ;
	}
	
	
	public static ArrayList<Vehicule> selectAllVehicule (){
		String requete = "select * from vehicule ;"; 
		ArrayList<Vehicule> lesVehicules= new ArrayList<Vehicule>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Responsables 
			while (lesResultats.next()) {
				//instanciation d'un Responsable 
				Vehicule unVehicule = new Vehicule (
						lesResultats.getInt("idVehicule"), lesResultats.getString("Marque"), 
						lesResultats.getString("Modele"), lesResultats.getString("Immatriculation")
						); 
				// ajout du Responsable dans lesResponsable 
				 lesVehicules.add(unVehicule);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesVehicules ;
	}
	
	public static ArrayList<Vehicule> selectLikeVehicule (String filtre){
		String requete = "select * from Vehicule where Marque like '%" + filtre +"%' or "
	                  + " Modele '%" + filtre +"%' ; ";
		
		ArrayList<Vehicule> lesVehicules = new ArrayList<Vehicule>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Vehicules 
			while (lesResultats.next()) {
				//instanciation d'un Vehicule 
				Vehicule unVehicule = new Vehicule (
						lesResultats.getInt("idVehicule"), lesResultats.getString("Marque"), 
						lesResultats.getString("Modele"),  lesResultats.getString("Immatriculation")
						); 
				// ajout du Vehicule dans lesVehicules 
				 lesVehicules.add(unVehicule);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesVehicules ;
	}
	
	
	
	/**************************** Gestion des Responsables********************/
	public static void insertResponsable (Responsable unResponsable) {
		String requete ="insert into Responsable values (null, '" + unResponsable.getNom()
		+"','" + unResponsable.getPrenom() + "','" + unResponsable.getEmail() 
		+ "','" + unResponsable.getMdp() + "','" + unResponsable.getType_User()
		+ "','" + unResponsable.getNumero_Telephone() +"');";
		executerRequete (requete); 
	}
	public static void deleteResponsable(int idResponsable) {
		String requete ="delete from Responsable where idResponsable = " + idResponsable +";";
		executerRequete (requete); 
	}
	
	public static void updateResponsable (Responsable unResponsable) {
		String requete ="update Responsable set nom = '" + unResponsable.getNom()
		+"', prenom = '" + unResponsable.getPrenom() + "', Email = '" + unResponsable.getEmail() 
		+ "', Mdp = '" + unResponsable.getMdp() + "', Type_User ='" + unResponsable.getType_User()
		+ "', Numero_Telephone = '" + unResponsable.getNumero_Telephone ()
		+ " where IdResponsable = " + unResponsable.getIdResponsable()+";";
		executerRequete (requete); 
	}
	
	public static Responsable selectWhereResponsable(int IdResponsable) {
		String requete = "select * from Responsable where idResponsable = " + IdResponsable +";"; 
		Responsable unResponsable = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Responsable 
			if (lesResultats.next()) {
				//instanciation d'un Responsable 
				 unResponsable = new Responsable (
						lesResultats.getInt("idResponsable"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone"),lesResultats.getString("Poste")
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unResponsable ;
	}
	
	public static Responsable selectWhereResponsable(String email, String mdp) {
		String requete = "select * from Responsable where email ='"+email+"' and mdp='"+mdp+"' ;"; 
		Responsable unResponsable = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Responsable 
			if (lesResultats.next()) {
				//instanciation d'un Responsable 
				 unResponsable = new Responsable (
						lesResultats.getInt("idResponsable"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone"),lesResultats.getString("Poste")
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unResponsable ;
	}
	
	public static ArrayList<Responsable> selectAllResponsables (){
		String requete = "select * from Responsable ;"; 
		ArrayList<Responsable> lesResponsables = new ArrayList<Responsable>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Responsables 
			while (lesResultats.next()) {
				//instanciation d'un Responsable 
				Responsable unResponsable = new Responsable (
						lesResultats.getInt("idresponsable"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("email"),
						lesResultats.getString("mdp"),lesResultats.getString("Type_user"), 
						lesResultats.getString("NUMERO_TELEPHONE"),	lesResultats.getString("Poste")
						); 
				// ajout du Responsable dans lesResponsable 
				 lesResponsables.add(unResponsable);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesResponsables ;
	}
	
	public static ArrayList<Responsable> selectLikeResponsables (String filtre){
		String requete = "select * from Responsable where nom like '%" + filtre +"%' or "
				+ " prenom like '%" + filtre +"%' or "
				+ " Email like '%" + filtre +"%' or "
				+ " Mdp like '%" + filtre +"%' or "
				+ " Type_User like '%" + filtre +"%' ; ";
		
		ArrayList<Responsable> lesResponsables = new ArrayList<Responsable>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Responsable 
			while (lesResultats.next()) {
				//instanciation d'un Responsable 
				Responsable unResponsable = new Responsable (
						lesResultats.getInt("idResponsable"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"), 
						lesResultats.getString("Numero_Telephone"),lesResultats.getString("Poste")
						); 
				// ajout du Responsablet dans lesResponsables 
				 lesResponsables.add(unResponsable);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesResponsables ;
	}
	
	

	

	
	
	/**************************** Gestion des formules ********************/
	public static void insertFormule (Formule uneFormule) {
		String requete ="insert into Formule values (null, '" +  uneFormule.getDescription() + "');";
		executerRequete (requete); 
	}
	public static void deleteFormule(int Num_Formule) {
		String requete ="delete from Formule where Num_Formule= " + Num_Formule +";";
		executerRequete (requete); 
	}
	public static void updateFormule (Formule uneFormule) {
		String requete ="update Formule set Description = '" + uneFormule.getDescription()
		 + " where Num_Formule = " +uneFormule.getNum_formule()+";";
		executerRequete (requete); 
	}
	
	public static Formule selectWhereFormule(int Num_Formule) {
		String requete = "select * from formule where Num_Formule = " + Num_Formule +";"; 
		Formule uneFormule = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction une Formule 
			if (lesResultats.next()) {
				//instanciation d'un Formule 
				 uneFormule = new Formule (
						lesResultats.getInt("Num_Formule"), lesResultats.getString("Description")
	                     ); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneFormule ;
	}
	
	public static ArrayList<Formule> selectAllFormules (){
		String requete = "select * from Formule ;"; 
		ArrayList<Formule> lesFormules = new ArrayList<Formule>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Formule 
			while (lesResultats.next()) {
				//instanciation d'un Formule 
				 Formule unFormule = new Formule (
						lesResultats.getInt("Num_Formule"), lesResultats.getString("Description") 
						); 
				// ajout du Formule dans lesFormules 
				 lesFormules.add(unFormule);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesFormules ;
	}
	
	public static ArrayList<Formule> selectLikeFormules (String filtre){
		String requete = "select * from description where Description like '%" + filtre +"%' ; ";
		
		ArrayList<Formule> lesFormules = new ArrayList<Formule>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Formule 
			while (lesResultats.next()) {
				//instanciation d'un Formule 
				 Formule uneFormule = new Formule (
						lesResultats.getInt("Num_Formule"), lesResultats.getString("Description") 
						); 
				// ajout du Formule dans lesFormules 
				 lesFormules.add(uneFormule);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesFormules ;
	}
	
	/**************************** Gestion du prix de la formule et nombre de cours ********************/

	public class Main {
	    private String nomFormule;
	    private double prixFormule;
	    private int nbCours;

	    public Main(String nomFormule, double prixFormule, int nbCours) {
	        this.nomFormule = nomFormule;
	        this.prixFormule = prixFormule;
	        this.nbCours = nbCours;
	    }

	    public String getNomFormule() {
	        return nomFormule;
	    }

	    public void setNomFormule(String nomFormule) {
	        this.nomFormule = nomFormule;
	    }

	    public double getPrixFormule() {
	        return prixFormule;
	    }

	    public void setPrixFormule(double prixFormule) {
	        this.prixFormule = prixFormule;
	    }

	    public int getNbCours() {
	        return nbCours;
	    }

	    public void setNbCours(int nbCours) {
	        this.nbCours = nbCours;
	    }

	    @Override
	    public String toString() {
	        return String.format("Formule : %s, Prix : %.2f €, Nombre de cours : %d",
	                nomFormule, prixFormule, nbCours);
	    }
	}


	
	

	
	
	
	
	
	

	/**************************** Gestion des EXAMENS********************/
	public static void insertExamen (Examen unExamen) {
		String requete ="insert into Examen values (null, '"+ unExamen.getDATE_ET_HEURE_D_EXAMEN () + "','" + unExamen.getVehicule() 
		+ "','" + unExamen.getType_De_Permis() + "');";
		executerRequete (requete); 
	}
	public static void deleteExamen(int idExamen) {
		String requete ="delete from Responsable where idExamen = " + idExamen +";";
		executerRequete (requete); 
	}
	
	public static void updateExamen (Examen unExamen) {
		String requete ="update Examen set DATE_ET_HEURE_D_EXAMEN  = '" + unExamen.getDATE_ET_HEURE_D_EXAMEN ()
		+"', Vehicule = '" + unExamen.getVehicule() + "', TYPE_DE_PERMIS = '" + unExamen.getType_De_Permis() 
		+ " where IdExamen = " + unExamen.getIdExamen()+";";
		executerRequete (requete); 
	}
	
	public static Examen selectWhereExamen(int IdExamen) {
		String requete = "select * from Examen where idExamen = " + IdExamen +";"; 
		Examen unExamen = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Examen 
			if (lesResultats.next()) {
				//instanciation d'un examen 
				 unExamen = new Examen (
						lesResultats.getInt("idExamen"), lesResultats.getString("DATE_ET_HEURE_D_EXAMEN "), 
						lesResultats.getString("Vehicule"), lesResultats.getString("TYPE_DE_PERMIS")
					
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unExamen ;
	}
	
	
	public static ArrayList<Examen> selectAllExamens (){
		String requete = "select * from Examen ;"; 
		ArrayList<Examen> lesExamens = new ArrayList<Examen>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Examens 
			while (lesResultats.next()) {
				//instanciation d'un Examen 
				Examen unExamen = new Examen (
						lesResultats.getInt("idExamen"), lesResultats.getString("DATE_ET_HEURE_D_EXAMEN"), 
						lesResultats.getString("Vehicule"), lesResultats.getString("TYPE_DE_PERMIS")
						); 
				// ajout de l examen dans lesExamen 
				 lesExamens.add(unExamen);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesExamens ;
	}
	public static ArrayList<Examen> selectLikeExamens (String filtre){
		String requete = "select * from Examen  where Vehicule like '%" + filtre + "%' or  TYPE_DE_PERMIS like '%"+filtre+"%' ;"; 
		ArrayList<Examen> lesExamens = new ArrayList<Examen>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Examens 
			while (lesResultats.next()) {
				//instanciation d'un Examen 
				Examen unExamen = new Examen (
						lesResultats.getInt("idExamen"), lesResultats.getString("DATE_ET_HEURE_D_EXAMEN"), 
						lesResultats.getString("Vehicule"), lesResultats.getString("TYPE_DE_PERMIS")
						); 
				// ajout de l examen dans lesExamen 
				 lesExamens.add(unExamen);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesExamens ;
	}

	
	/***********************GESTION DES MONITEURS****************************/
	
	public static void insertMoniteur (Moniteur unMoniteur) {
		String requete ="insert into Moniteur values (null, '" + unMoniteur.getNOm()
		+"','" + unMoniteur.getPrenOm() + "','" + unMoniteur.getEmail() 
		+ "','" + unMoniteur.getMdp() + "','" + unMoniteur.getType_User()
		+ "','" + unMoniteur.getNUMERO_TELEPHONE() +"');";
		executerRequete (requete); 
	}
	public static void deleteMoniteur(int idMoniteur) {
		String requete ="delete from Moniteur where idMoniteur = " + idMoniteur +";";
		executerRequete (requete); 
	}
	
	public static void updateMoniteur (Moniteur unMoniteur) {
		String requete ="update Moniteur set nom = '" + unMoniteur.getNOm()
		+"', prenom = '" + unMoniteur.getPrenOm() + "', Email = '" + unMoniteur.getEmail() 
		+ "', Mdp = '" + unMoniteur.getMdp() + "', Type_User ='" + unMoniteur.getType_User()
		+ "', Numero_Telephone = '" + unMoniteur.getNUMERO_TELEPHONE ()
		+ " where IdMoniteur = " + unMoniteur.getIdMoniteur()+";";
		executerRequete (requete); 
	}
	
	public static Moniteur selectWhereMoniteur(int IdMoniteur) {
		String requete = "select * from Moniteur where idMoniteur = " + IdMoniteur +";"; 
		Moniteur unMoniteur = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Moniteur 
			if (lesResultats.next()) {
				//instanciation d'un Moniteur 
				 unMoniteur = new Moniteur (
						lesResultats.getInt("idMoniteur"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone"),
						lesResultats.getInt("IdResponsable")
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unMoniteur ;
	}
	
	public static Moniteur selectWhereMoniteur(String email, String mdp) {
		String requete = "select * from Moniteur where email ='"+email+"' and mdp='"+mdp+"' ;"; 
		Moniteur unMoniteur = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch 
			//parcours des resultats et extraction un Moniteur 
			if (lesResultats.next()) {
				//instanciation d'unMoniteur 
				 unMoniteur = new Moniteur(
						lesResultats.getInt("idMoniteur"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"),
						lesResultats.getString("Numero_Telephone"),
						lesResultats.getInt("IdResponsable")
						
						); 
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unMoniteur ;
	}
	
	public static ArrayList<Moniteur> selectAllMoniteurs (){
		String requete = "select * from Moniteur ;"; 
		ArrayList<Moniteur> lesMoniteurs = new ArrayList<Moniteur>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Moniteurs 
			while (lesResultats.next()) {
				//instanciation d'un Moniteur 
				Moniteur unMoniteur = new Moniteur (
						lesResultats.getInt("idMoniteur"), lesResultats.getString("Nom"), 
						lesResultats.getString("Prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"), 
						lesResultats.getString("Numero_Telephone"),
						lesResultats.getInt("IdResponsable")
						); 
				// ajout du Moniteur dans les Moniteurs 
				 lesMoniteurs.add(unMoniteur);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesMoniteurs ;
	}
	
	public static ArrayList<Moniteur> selectLikeMoniteurs (String filtre){
		String requete = "select * from Moniteur where nom like '%" + filtre +"%' or "
				+ " prenom like '%" + filtre +"%' or "
				+ " Email like '%" + filtre +"%' or "
				+ " Mdp like '%" + filtre +"%' or "
				+ " Type_User like '%" + filtre +"%' ; ";
		
		ArrayList<Moniteur> lesMoniteurs = new ArrayList<Moniteur>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll 
			//parcours des resultats et extraction les Moniteur 
			while (lesResultats.next()) {
				//instanciation d'un Moniteur 
				Moniteur unMoniteur = new Moniteur (
						lesResultats.getInt("idMoniteur"), lesResultats.getString("nom"), 
						lesResultats.getString("prenom"), lesResultats.getString("Email"),
						lesResultats.getString("Mdp"),lesResultats.getString("Type_User"), 
						lesResultats.getString("Numero_Telephone"),
						lesResultats.getInt("IdResponsable")
						); 
				// ajout du Moniteur dans lesMoniteurs 
				 lesMoniteurs.add(unMoniteur);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesMoniteurs ;
	}
	/******************** Autres méthodes ***************************/
	public static void executerRequete(String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		
	}
		
		
		
		
		public static int count(String table) {
			String requete = "select count(*) as nb from "+table+";";
			int nb=0;
			try {
				uneConnexion.seConnecter();
				Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
				ResultSet unResultat = unStat.executeQuery(requete); //fetch 
				//parcours des resultats et extraction un Candidat 
				if (unResultat.next()) {
					//instanciation d'un Candidat 
					 nb = unResultat.getInt("nb"); 
						 
					 }
						unStat.close()	;
				
				unStat.close();
				uneConnexion.seDeConnecter();
			}
			catch (SQLException exp) {
				System.out.println("Erreur d'execution de la requete : " + requete);
			}
			return nb ;
			
	}

}















