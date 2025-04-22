package Controleur;

public class Moniteur {
private int IdMoniteur;
private String NOm;
private String PrenOm;
private String Email;
private String Mdp;
private String Type_User;
private String NUMERO_TELEPHONE ;
private int Idresponsable;
public Moniteur(int idMoniteur, String nOm, String prenOm, String email, String mdp, String type_User,
		String nUMERO_TELEPHONE, int idresponsable) {
	super();
	IdMoniteur = idMoniteur;
	NOm = nOm;
	PrenOm = prenOm;
	Email = email;
	Mdp = mdp;
	Type_User = type_User;
	NUMERO_TELEPHONE = nUMERO_TELEPHONE;
	Idresponsable = idresponsable;
}

public Moniteur(String nOm, String prenOm, String email, String mdp, String type_User,
		String nUMERO_TELEPHONE, int idresponsable) {
	super();
	IdMoniteur = 0;
	NOm = nOm;
	PrenOm = prenOm;
	Email = email;
	Mdp = mdp;
	Type_User = type_User;
	NUMERO_TELEPHONE = nUMERO_TELEPHONE;
	Idresponsable = idresponsable;
}

public int getIdMoniteur() {
	return IdMoniteur;
}

public void setIdMoniteur(int idMoniteur) {
	IdMoniteur = idMoniteur;
}

public String getNOm() {
	return NOm;
}

public void setNOm(String nOm) {
	NOm = nOm;
}

public String getPrenOm() {
	return PrenOm;
}

public void setPrenOm(String prenOm) {
	PrenOm = prenOm;
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

public String getNUMERO_TELEPHONE() {
	return NUMERO_TELEPHONE;
}

public void setNUMERO_TELEPHONE(String nUMERO_TELEPHONE) {
	NUMERO_TELEPHONE = nUMERO_TELEPHONE;
}

public int getIdresponsable() {
	return Idresponsable;
}

public void setIdresponsable(int idresponsable) {
	Idresponsable = idresponsable;
}


}
