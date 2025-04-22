package Controleur;

public class Formule {
	private int Num_formule;
	private String Description;
	public Formule(int num_formule, String description) {
		super();
		Num_formule = num_formule;
		Description = description;
	}
	
	public Formule(String description) {
		super();
		Num_formule = 0;
		Description = description;
	}

	public int getNum_formule() {
		return Num_formule;
	}

	public void setNum_formule(int num_formule) {
		Num_formule = num_formule;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
