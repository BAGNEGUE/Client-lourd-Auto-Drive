package Controleur;

public class Responsable {
	private int IdResponsable;
	private String  Nom;
	private String Prenom;
	private String Email;
	private String Mdp;
	private String Type_User;
	private String Numero_Telephone;
	private String Poste;
	public Responsable(int idResponsable, String nom, String prenom, String email, String mdp, String type_User,
			String numero_Telephone,String Poste) {
		super();
		IdResponsable = idResponsable;
		Nom = nom;
		Prenom = prenom;
		Email = email;
		Mdp = mdp;
		Type_User = type_User;
		Numero_Telephone = numero_Telephone;
		Poste = Poste;
	}
	public Responsable(String nom, String prenom, String email, String mdp, String type_User,
			String numero_Telephone,String poste) {
		super();
		IdResponsable = 0;
		Nom = nom;
		Prenom = prenom;
		Email = email;
		Mdp = mdp;
		Type_User = type_User;
		Numero_Telephone = numero_Telephone;
		Poste = Poste;
	}
	public int getIdResponsable() {
		return IdResponsable;
	}
	public void setIdResponsable(int idResponsable) {
		IdResponsable = idResponsable;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMdp() {
		return Mdp;
	}
	public void setMdp(String mdp) {
		Mdp = mdp;
	}
	public String getType_User() {
		return Type_User;
	}
	public void setType_User(String type_User) {
		Type_User = type_User;
	}
	public String getNumero_Telephone() {
		return Numero_Telephone;
	}
	public void setNumero_Telephone(String numero_Telephone) {
		Numero_Telephone = numero_Telephone;
	}
	public String getPoste() {
		return Poste;
	}
	public void setPoste(String Poste) {
		Poste = Poste;
	}
	

}
