package Vue;

import java.awt.Color;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Vehicule;
import Controleur.Vehicule;
import Controleur.Tableau;
import Controleur.Controleur;
import Controleur.PPEAUTO;

import javax.swing.JButton;

public class PanelVehicule extends PanelPrincipal implements ActionListener
{
	private JPanel panelListe = new JPanel ();
	private JPanel panelForm=new JPanel ();
	private JTextField textMarque = new JTextField ();
	private JTextField textModele= new JTextField ();
	private JTextField textImmatriculation = new JTextField ();

	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	

	private JTable uneTable ;
	private Tableau unTableau ;
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbVehicules = new JLabel();
	
	
	public PanelVehicule() {
		super("Gestion des Vehicule"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(6,2));
		
		this.panelForm.add(new  JLabel("Marque Vehicule:"));
		this.panelForm.add(this.textMarque);
		
		this.panelForm.add(new  JLabel("Modele Vehicule :"));
		this.panelForm.add(this.textModele);
		
		this.panelForm.add(new  JLabel("Immatriculation :"));
		this.panelForm.add(this.textImmatriculation );
		
	
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.panelForm.add(this.btSupprimer);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		this.btSupprimer.addActionListener(this);
		
		//installation de la JTable
		String entetes [] = {"IdVehicule","Marque", "Modele", "Immatriculation"};
		this.unTableau = new Tableau (this.obtenirDonnees(""), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(400, 80, 500, 340);
		
		
		this.add(uneScroll);
		
		//implementation du click sur une ligne de la table
		this.uneTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0 ;
				
				if (e.getClickCount() >= 1) {
					numLigne = uneTable.getSelectedRow();
					textMarque.setText(unTableau.getValueAt(numLigne, 1).toString());
					textModele.setText(unTableau.getValueAt(numLigne,2).toString());
					textImmatriculation.setText(unTableau.getValueAt(numLigne, 3).toString());
				
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		//installation du panel filtre
		this.panelFiltre.setBackground(PPEAUTO.getCouleur());
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 400, 20);
		this.panelFiltre.add(new JLabel("Filtrer les Véhicules par : "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.add(this.panelFiltre);
		
		//installation du label nbvehicule
		
		this.lbNbVehicules.setBounds(450,440,400,20);
		this.lbNbVehicules.setText("Nombre de Vehicules : " + this.unTableau.getColumnCount());
		this.add(this.lbNbVehicules);
				
	}
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les vehicule de la base de données
		ArrayList<Vehicule> lesVehicules ;
		if (filtre.equals("")) {
			lesVehicules = Controleur.selectAllVehicules();
			}else {
				lesVehicules = Controleur.selectLikeVehicules(filtre);
			}
		//création d'une matrice de données
		Object[][] matrice = new Object[lesVehicules.size()][4];
		int i = 0;
		for (Vehicule unVehicule : lesVehicules) {
			matrice[i][0] = unVehicule.getIdVehicule();
			matrice[i][1] = unVehicule.getMarque();
			matrice[i][2] = unVehicule.getModele();
			matrice[i][3] = unVehicule.getImmatriculation();
			i++;
			
		}
		return matrice ;
	}
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textMarque.setText("");
			this.textModele.setText("");
			this.textImmatriculation.setText("");
	      }
		else if (e.getSource() ==this.btValider) {
		// recuperer les champs saisis
		String Marque = this.textMarque.getText();
		String Modele = this.textModele.getText();
		String Immatriculation = this.textImmatriculation.getText();

		//instanciation de la classe Vehicule
		Vehicule unVehicule=new Vehicule(Marque,Modele,Immatriculation);
		// inserer dans la base de donnees
		Controleur.insertVehicule(unVehicule);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du vehicule");
		
		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		
		//on vide les champs
		this.textMarque.setText("");
		this.textModele.setText("");
		this.textImmatriculation.setText("");
		
		btSupprimer.setVisible(true);
		btValider.setText("Valider");
	}
		
	else if (e.getSource() == this.btSupprimer) {
		//on recupere l'id du vehicule a supprimer
		int numLigne , idVehicule ;
		numLigne = this.uneTable.getSelectedRow();
		idVehicule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
		int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le Vehicule ?",
				"Suppression du vehicule", JOptionPane.YES_NO_OPTION);
		
		if (retour ==0) {
					
			//on supprime de la base de données
					Controleur.deleteVehicule(idVehicule);
					//on actualise l'affichage
					this.unTableau.setDonnees(this.obtenirDonnees(""));
					JOptionPane.showMessageDialog(this, "Suppression réussie du vehicule.");
					this.lbNbVehicules.setText("Nombre de vehicules : " + this.unTableau.getRowCount());
					
					//on vide les champs
					this.textMarque.setText("");
					this.textModele.setText("");
					this.textImmatriculation.setText("");
					
					btSupprimer.setVisible(true);
					btValider.setText("Valider");
		}
	}
	else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
		//on récupère les données y compris l'id
		int numLigne , idVehicule ;
		numLigne = this.uneTable.getSelectedRow();
		idVehicule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
		String marque = this.textMarque.getText();
		String modele = this.textModele.getText();
		String immatriculation = this.textImmatriculation.getText();
		
		
		//on modifie dans la bdd
		Vehicule unVehicule = new Vehicule(idVehicule, marque, modele, immatriculation);
		Controleur.updateVehicule(unVehicule);
		
		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		JOptionPane.showMessageDialog(this, "Modification réussie du vehicule.");
		
		
		
		//message de confirmation et on vide les champs
		this.textMarque.setText("");
		this.textModele.setText("");
		this.textImmatriculation.setText("");
		
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	else if (e.getSource() == this.btFiltrer) {
		//recuperer le filtre
		String filtre = this.txtFiltre.getText();
		
		//on actualise l'affichage du tableau avec les vehicules trouves
		this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		
	    }
	}
		
	
	
}
