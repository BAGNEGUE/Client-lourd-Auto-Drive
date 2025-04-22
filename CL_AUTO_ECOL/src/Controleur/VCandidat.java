package Controleur;

public class VCandidat {
private String nom,prenom,age,email,type_user,numero_telephone;

public VCandidat(String nom, String prenom, String age, String email, String type_user, String numero_telephone) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.age = age;
	this.email = email;
	this.type_user = type_user;
	this.numero_telephone = numero_telephone;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getType_user() {
	return type_user;
}

public void setType_user(String type_user) {
	this.type_user = type_user;
}

public String getNumero_telephone() {
	return numero_telephone;
}

public void setNumero_telephone(String numero_telephone) {
	this.numero_telephone = numero_telephone;
}



	
	
}
