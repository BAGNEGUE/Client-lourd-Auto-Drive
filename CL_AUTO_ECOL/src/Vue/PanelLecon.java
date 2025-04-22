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

import Controleur.Lecon;
import Controleur.PPEAUTO;
import Controleur.Tableau;
import Controleur.Lecon;
import Controleur.Controleur;
import Controleur.Formule;

import javax.swing.JButton;

public class PanelLecon extends PanelPrincipal implements ActionListener
{
	
	private JPanel panelListe = new JPanel ();
	
	private JPanel panelForm=new JPanel ();
	private JTextField textTYPE_DE_LECON= new JTextField ();
	private JTextField textDescription= new JTextField ();
	private JTextField textTitre= new JTextField ();

	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");

	 
	
	private JTable uneTable ;
	private Tableau unTableau ;
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbLecons = new JLabel();
	
	public PanelLecon() {
		super("Gestion des lecons"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(6,2));
		
		this.panelForm.add(new  JLabel("TYPE_DE_LECON Lecon:"));
		this.panelForm.add(this.textTYPE_DE_LECON);
		
		this.panelForm.add(new  JLabel("description :"));
		this.panelForm.add(this.textDescription);
		
		this.panelForm.add(new  JLabel("titre :"));
		this.panelForm.add(this.textTitre );
		
		
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		String entetes [] = {"IdLecon","TYPE_DE_LECON", "description", "titre"};
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
					textTYPE_DE_LECON.setText(unTableau.getValueAt(numLigne, 1).toString());
					textDescription.setText(unTableau.getValueAt(numLigne,2).toString());
					textTitre.setText(unTableau.getValueAt(numLigne, 3).toString());
					 
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
				
			}
		});
		//installation du panel filtre
				this.panelFiltre.setBackground(PPEAUTO.getCouleur());
				this.panelFiltre.setLayout(new GridLayout(1, 3));
				this.panelFiltre.setBounds(400, 50, 400, 20);
				this.panelFiltre.add(new JLabel("Filtrer les lecons par : "));
				this.panelFiltre.add(this.txtFiltre);
				this.panelFiltre.add(this.btFiltrer);
				this.btFiltrer.addActionListener(this);
				this.add(this.panelFiltre);
				
				//installation du label nb lecons
				
				this.lbNbLecons.setBounds(450,440,400,20);
				this.lbNbLecons.setText("Nombre de lecons : " + this.unTableau.getColumnCount());
				this.add(this.lbNbLecons);
						
			}
	

	
	private Object[][] obtenirDonnees(String filtre) {
		//récuperer les clients de la base de données
				ArrayList<Lecon> lesLecons ;
				if (filtre.equals("")) {
					lesLecons = Controleur.selectAllLecons();
					}else {
						lesLecons = Controleur.selectLikeLecons(filtre);
					}
				//création d'une matrice de données
				Object[][] matrice = new Object[lesLecons.size()][4];
				int i = 0;
				for (Lecon unLecon : lesLecons) {
					matrice[i][0] = unLecon.getIdLecon();
					matrice[i][1] = unLecon.getTYPE_DE_LECON();
					matrice[i][2] = unLecon.getDescription();
					matrice[i][3] = unLecon.getTitre();
					i++;
					
				}
				return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textTYPE_DE_LECON.setText("");
			this.textDescription.setText("");
			this.textTitre.setText("");
			
			
		}
		else if (e.getSource() ==this.btValider) {
		// recuperer les champs saisis
		String TYPE_DE_LECON= this.textTYPE_DE_LECON.getText();
		String Description = this.textDescription.getText();
		String Titre = this.textTitre.getText();
		
		//instanciation de la classe lecon
		Lecon unLecon=new Lecon (TYPE_DE_LECON,Description,Titre);
		// inserer dans la base de donnees
		Controleur.insertLecon(unLecon);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du candidat");
		
		
		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		//on vide les champs
		this.textTYPE_DE_LECON.setText("");
		this.textDescription.setText("");
		this.textTitre.setText("");
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
		
	
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on récupère les données y compris l'id
			int numLigne , IdLecon ;
			numLigne = this.uneTable.getSelectedRow();
			IdLecon = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			String type_de_lecon = this.textTYPE_DE_LECON.getText();
			String Description = this.textDescription.getText();
			String titre = this.textTitre.getText();
			
			
			//on modifie dans la bdd
			Lecon uneLecon = new Lecon(IdLecon,type_de_lecon, Description,titre);
			Controleur.updateLecon(uneLecon);
			
			//on actualise l'affichage du tableau
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie de lecon.");
			
			
			//message de confirmation et on vide les champs
			this.textTYPE_DE_LECON.setText("");
			this.textDescription.setText("");
			this.textTitre.setText("");
		
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}	
		
		
		
		
		
	else if (e.getSource() == this.btFiltrer) {
		//recuperer le filtre
		String filtre = this.txtFiltre.getText();
		
		//on actualise l'affichage du tableau avec les lecons trouves
		this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		
	    }
		
	}
	
}
