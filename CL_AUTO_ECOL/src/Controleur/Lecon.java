package Controleur;

public class Lecon {
	private int IdLecon;
	private String TYPE_DE_LECON;
	private String Description;
	private String Titre;
	public Lecon(int idLecon, String tYPE_DE_LECON, String description, String titre) {
		super();
		IdLecon = idLecon;
		TYPE_DE_LECON = tYPE_DE_LECON;
		Description = description;
		Titre = titre;
	}
	public Lecon( String tYPE_DE_LECON, String description, String titre) {
		super();
		IdLecon = 0;
		TYPE_DE_LECON = tYPE_DE_LECON;
		Description = description;
		Titre = titre;
	}
	public int getIdLecon() {
		return IdLecon;
	}
	public void setIdLecon(int idLecon) {
		IdLecon = idLecon;
	}
	public String getTYPE_DE_LECON() {
		return TYPE_DE_LECON;
	}
	public void setTYPE_DE_LECON(String tYPE_DE_LECON) {
		TYPE_DE_LECON = tYPE_DE_LECON;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}

	
	
}
