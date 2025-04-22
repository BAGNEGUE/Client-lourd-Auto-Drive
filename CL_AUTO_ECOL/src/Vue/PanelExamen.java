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


import Controleur.Examen;
import Controleur.PPEAUTO;
import Controleur.Tableau;
import Controleur.Vehicule;
import Controleur.Candidat;
import Controleur.Controleur;

import javax.swing.JButton;

public class PanelExamen extends PanelPrincipal implements ActionListener
{
	private JPanel panelListe = new JPanel ();
	
	private JPanel panelForm=new JPanel ();
	private JTextField textDATE_ET_HEURE_D_EXAMEN = new JTextField ();
	private JTextField textVehicule = new JTextField ();
	private JTextField textTYPE_DE_PERMIS = new JTextField ();

	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbExamens = new JLabel();
	
	public PanelExamen() {
		super("Gestion des Examen"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(4,2));
		
		this.panelForm.add(new  JLabel("DATE_ET_HEURE_D_EXAMEN:"));
		this.panelForm.add(this.textDATE_ET_HEURE_D_EXAMEN);
		
		this.panelForm.add(new  JLabel(" Vehicule:"));
		this.panelForm.add(this.textVehicule);
		
		this.panelForm.add(new  JLabel("TYPE_DE_PERMIS :"));
		this.panelForm.add(this.textTYPE_DE_PERMIS );
		
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		
		//installation de la JTable 
		String entetes [] = {"IDEXAMEN  ","DATE_ET_HEURE_D_EXAMEN", "Vehicule", "TYPE_DE_PERMIS"};
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
					textDATE_ET_HEURE_D_EXAMEN.setText(unTableau.getValueAt(numLigne, 1).toString());
					textVehicule.setText(unTableau.getValueAt(numLigne,2).toString());
					textTYPE_DE_PERMIS.setText(unTableau.getValueAt(numLigne, 3).toString());
					 
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
				
			}
		});
		//installation du panel filtre
		this.panelFiltre.setBackground(PPEAUTO.getCouleur());
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 400, 20);
		this.panelFiltre.add(new JLabel("Filtrer les clients par : "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.add(this.panelFiltre);
		
		//installation du label nb candidats
		
		this.lbNbExamens.setBounds(450,440,400,20);
		this.lbNbExamens.setText("Nombre de candidats : " + this.unTableau.getRowCount());
		this.add(this.lbNbExamens);
				

}
		
	
	private Object[][] obtenirDonnees(String filtre) {
		
		//récuperer les examens de la base de données
		ArrayList<Examen> lesExamens ;
		if (filtre.equals("")) {
			lesExamens = Controleur.selectAllExamens();
			}else {
				lesExamens = Controleur.selectLikeExamens(filtre);
			}
		//création d'une matrice de données
		Object[][] matrice = new Object[lesExamens.size()][4];
		int i = 0;
		for (Examen unExemen : lesExamens) {
			matrice[i][0] = unExemen.getIdExamen();
			matrice[i][1] = unExemen.getDATE_ET_HEURE_D_EXAMEN();
			matrice[i][2] = unExemen.getVehicule();
			matrice[i][3] = unExemen.getType_De_Permis();
		 	 
			i++;
		}
		return matrice ;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textDATE_ET_HEURE_D_EXAMEN.setText("");
			this.textVehicule.setText("");
			this.textTYPE_DE_PERMIS.setText("");
	      }
		else if (e.getSource() ==this.btValider && this.btValider.getText().equals("Valider")) {
		// recuperer les champs saisis
		String DATE_ET_HEURE_D_EXAMEN= this.textDATE_ET_HEURE_D_EXAMEN.getText();
		String Vehicule = this.textVehicule.getText();
		String TYPE_DE_PERMIS= this.textTYPE_DE_PERMIS.getText();

		//instanciation de la classe Vehicule
		Examen unExamen =new Examen (DATE_ET_HEURE_D_EXAMEN,Vehicule,TYPE_DE_PERMIS);
		// inserer dans la base de donnees
		Controleur.insertExamen (unExamen );
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du vehicule");
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		//on vide les champs
		this.textDATE_ET_HEURE_D_EXAMEN.setText("");
		this.textVehicule.setText("");
		this.textTYPE_DE_PERMIS.setText("");
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	else if (e.getSource() == this.btSupprimer) {
		//on recupere l'id du vehicule a supprimer
		int numLigne , idExamen ;
		numLigne = this.uneTable.getSelectedRow();
		idExamen = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
		int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer l'Examen ?",
				"Suppression de l'Examen", JOptionPane.YES_NO_OPTION);
		
		if (retour ==0) {
					
			//on supprime de la base de données
					Controleur.deleteExamen(idExamen);
					//on actualise l'affichage
					this.unTableau.setDonnees(this.obtenirDonnees(""));
					JOptionPane.showMessageDialog(this, "Suppression réussie de l examen.");
					this.lbNbExamens.setText("Nombre de Examen : " + this.unTableau.getRowCount());
					
					//on vide les champs
					this.textDATE_ET_HEURE_D_EXAMEN.setText("");
					this.textVehicule.setText("");
					this.textTYPE_DE_PERMIS.setText("");
					
					btSupprimer.setVisible(false);
					btValider.setText("Valider");
		}
	}

		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on récupère les données y compris l'id
			int numLigne , idExamen ;
			numLigne = this.uneTable.getSelectedRow();
			idExamen = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			String DATE_ET_HEURE_D_EXAMEN = this.textDATE_ET_HEURE_D_EXAMEN.getText();
			String Vehicule = this.textVehicule.getText();
			String TYPE_DE_PERMIS = this.textTYPE_DE_PERMIS.getText();
			
		//on modifie dans la bdd
		Examen unExamen = new Examen (idExamen ,DATE_ET_HEURE_D_EXAMEN, Vehicule, TYPE_DE_PERMIS);
		Controleur.updateExamen(unExamen);

		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		JOptionPane.showMessageDialog(this, "Modification réussie de l examen reussi.");
		
		//on vide les champs
		this.textDATE_ET_HEURE_D_EXAMEN.setText("");
		this.textVehicule.setText("");
		this.textTYPE_DE_PERMIS.setText("");
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	else if (e.getSource() == this.btFiltrer) {
		//recuperer le filtre
		String filtre = this.txtFiltre.getText();
		
		//on actualise l'affichage du tableau avec les examens trouves
		this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		
	    }

	 }
	
}
