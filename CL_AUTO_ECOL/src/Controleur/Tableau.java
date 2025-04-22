package Controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private Object [][] donnees ; //matrice des données du tableau d'affichage 
	private String [] entetes ;   // les noms des entetes colonnes 
	
	public Tableau(Object[][] donnees, String[] entetes) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length; //nombre de lignes 
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length; //nombre de colonnes 
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.donnees[rowIndex][columnIndex]; //retourne une valeur dans la matrice
	}
	
	public void setDonnees (Object [][] matrice) {
		this.donnees =  matrice ; 
		this.fireTableDataChanged();//actualiser l'affichage.
	}

	@Override
	public String getColumnName(int column) {
		 
		return this.entetes[column];
	}
	
}






