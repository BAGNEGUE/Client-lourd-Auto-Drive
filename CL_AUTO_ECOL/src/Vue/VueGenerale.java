package Vue;

import java.awt.*;


import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Controleur.PPEAUTO;
import Vue.PanelProfil;


public class VueGenerale extends JFrame implements ActionListener
{
	private JButton btProfil = new JButton("Profil");
	private JButton btCandidat = new JButton("Candidat");
	private JButton btExamen = new JButton("Examen");
	private JButton btFormule = new JButton("Formule");
	

	private JButton btLecon = new JButton("Lecon");

	private JButton btMoniteur = new JButton("Moniteur");
	
	private JButton btPlannings = new JButton("Plannings");
	private JButton btResponsable= new JButton("Responsable");
	private JButton btVehicule = new JButton("Vehicule");
	private JButton btQuitter = new JButton("Quitter");
	
	private JPanel panelMenu = new JPanel (); 
	private static PanelProfil unPanelProfil = new PanelProfil();
	private static PanelCandidat unPanelCandidat = new PanelCandidat(); 
	private static PanelExamen unPanelExamen = new PanelExamen(); 
	private static PanelFormule unPanelFormule = new PanelFormule(); 
	


    private static PanelLecon unPanelLecon = new  PanelLecon (); 
    private static PanelMoniteur unPanelMoniteur = new PanelMoniteur(); 
	private static PanelPlannings unPanelPlannings = new PanelPlannings(); 
	private static PanelResponsable unPanelResponsable = new PanelResponsable();
	private static PanelVehicule unPanelVehicule = new PanelVehicule(); 
	
	
	
	public VueGenerale() {
		 this.setTitle("PROD AUTO ECOLE");
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 this.setResizable(true);
		 this.setBounds(100, 100, 1200, 700);
		 this.setLayout(null);
		 this.getContentPane().setBackground(PPEAUTO.getCouleur());
		 
		 //construction du panel Menu 
		 this.panelMenu.setBackground(PPEAUTO.getCouleur());
		 this.panelMenu.setBounds(50, 10, 900, 40);
		 this.panelMenu.setLayout(new GridLayout(1, 6));
		 this.panelMenu.add(this.btCandidat); 
		 this.panelMenu.add(this.btExamen); 
		 this.panelMenu.add(this.btFormule); 
		 this.panelMenu.add(this.btLecon);
		 this.panelMenu.add(this.btMoniteur);
		 this.panelMenu.add(this.btProfil); 
		 this.panelMenu.add(this.btPlannings); 
		 this.panelMenu.add(this.btResponsable);
		 
		 this.panelMenu.add(this.btVehicule);
		 this.panelMenu.add(this.btQuitter); 
		 this.add(this.panelMenu); 
		 
		 //rendre les boutons ecoutables 
		 this.btCandidat.addActionListener(this);
		 this.btExamen.addActionListener(this);
		 this.btFormule.addActionListener(this);
		 this.btLecon.addActionListener(this);
	     this.btMoniteur.addActionListener(this);
		 this.btPlannings.addActionListener(this);
		 this.btResponsable.addActionListener(this);
		 this.btProfil.addActionListener(this);
		 this.btVehicule.addActionListener(this);
		 this.btQuitter.addActionListener(this);
		 
		 //ajouts des panels à la fenetre 
		 this.add(this.unPanelCandidat); 
		 this.add(this.unPanelExamen);
		 this.add(this.unPanelFormule);
	     this.add(this.unPanelLecon);
		 this.add(this.unPanelMoniteur);
		 this.add(this.unPanelPlannings);
		 this.add(this.unPanelResponsable);
		 this.add(this.unPanelProfil);
		 this.add(this.unPanelVehicule);
		 this.setVisible(true);
	}
	private JButton JButton(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public void afficherPanel (int choix) {
		this.unPanelCandidat.setVisible(false);
		this.unPanelProfil.setVisible(false);
		this.unPanelExamen.setVisible(false);
		this.unPanelFormule.setVisible(false);
	    this.unPanelLecon.setVisible(false);
	    this.unPanelMoniteur.setVisible(false);
	    this.unPanelPlannings.setVisible(false);
		this.unPanelResponsable.setVisible(false);
		this.unPanelVehicule.setVisible(false);
		switch (choix) {
		case 0 : this.unPanelProfil.setVisible(true); break; 
		case 1 : this.unPanelCandidat.setVisible(true); break; 
		case 2 : this.unPanelExamen.setVisible(true); break;
		case 3: this.unPanelFormule.setVisible(true); break;
        case 4 : this.unPanelLecon.setVisible(true); break;
	    case 5 : this.unPanelMoniteur.setVisible(true); break;
		case 6 : this.unPanelPlannings.setVisible(true); break;
		case 7 : this.unPanelResponsable.setVisible(true); break;
		case 8 : this.unPanelVehicule.setVisible(true); break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btQuitter) {
			 int retour = JOptionPane.showConfirmDialog(this,
					 "Voulez-vous quitter l'application ?", 
					 "Quitter l'application", JOptionPane.YES_NO_OPTION); 
			 if (retour ==0) {
			 PPEAUTO.rendreVisible(true); //ouverture de nouveau de la connexion
			 PPEAUTO.creerVueGenerale(false); //fermeture de la vue genérale 
			 }
		 }
		 else if (e.getSource() == this.btCandidat) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btExamen) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btFormule) {
			 this.afficherPanel(3);
		 }

		 else if (e.getSource() == this.btLecon) {
			 this.afficherPanel(4);
		 }
	
		 else if (e.getSource() == this.btMoniteur) {
			 this.afficherPanel(5);
		 }
		
		 else if (e.getSource() == this.btPlannings) {
			 this.afficherPanel(6);
		 }
		 else if (e.getSource() == this.btResponsable) {
			 this.afficherPanel(7);
		 }
		 else if (e.getSource() == this.btVehicule) {
			 this.afficherPanel(8);
		 }
		 else if (e.getSource() == this.btProfil) {
			 this.afficherPanel(0);
		 }
	}

}











