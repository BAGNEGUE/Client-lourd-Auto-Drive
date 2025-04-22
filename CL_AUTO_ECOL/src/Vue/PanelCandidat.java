package Vue;

import java.awt.Color;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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

import Controleur.Candidat;
import Controleur.Controleur;
import Controleur.PPEAUTO;
import Controleur.Tableau;
import Controleur.Vehicule;
import Controleur.Candidat;

import javax.swing.JButton;

public class PanelCandidat extends PanelPrincipal implements ActionListener
{
	
	private JPanel panelForm=new JPanel ();
	private JPanel panelListe = new JPanel ();
	private JTextField textNom = new JTextField ();
	private JTextField textPrenom = new JTextField ();
	private JTextField textAge = new JTextField ();
	private JTextField textEmail = new JTextField ();
	private JTextField textMdp = new JTextField ();
	private JTextField textType_User = new JTextField ();
	private JTextField textNumero_Telephone = new JTextField ();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbCandidats = new JLabel();
	
	public PanelCandidat() {
		super("Gestion des Candidat"); 
		//installation du formulaire40, 80, 300, 220
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(8,2));
	
		this.panelForm.add(new  JLabel("Nom Candidat:"));//Ajout de l affichage au formulaire 
	    this.panelForm.add(this.textNom); // ajout du texte a afficher 
		
		this.panelForm.add(new  JLabel("Prenom Candidat :"));
		this.panelForm.add(this.textPrenom);
	
		this.panelForm.add(new  JLabel("Age :"));
		this.panelForm.add(this.textAge );
		
        this.panelForm.add(new  JLabel("Email  :"));
		this.panelForm.add(this.textEmail);
	
		this.panelForm.add(new  JLabel("Mdp :"));
		this.panelForm.add(this.textMdp);

		this.panelForm.add(new  JLabel("Type_User :"));
		this.panelForm.add(this.textType_User);
		this.textType_User.setText("candidat");
		this.textType_User.setEditable(false);
	
		this.panelForm.add(new  JLabel("Numero_Telephone:"));
		this.panelForm.add(this.textNumero_Telephone);
	
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		

		//installation de la JTable 
		String entetes [] = {"IdCandidat","Nom", "Prenom", "Age","Email","Mdp", "Type_User ","Numero_Telephone"};
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
					textNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					textPrenom.setText(unTableau.getValueAt(numLigne,2).toString());
					textAge.setText(unTableau.getValueAt(numLigne, 3).toString());
					textEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
					//textMdp.setText(unTableau.getValueAt(numLigne,5).toString());
					textType_User.setText(unTableau.getValueAt(numLigne, 5).toString());
					textNumero_Telephone.setText(unTableau.getValueAt(numLigne, 6).toString());
					   
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
				
			}
		});
		//installation du panel filtre
				this.panelFiltre.setBackground(PPEAUTO.getCouleur());
				this.panelFiltre.setLayout(new GridLayout(1, 3));
				this.panelFiltre.setBounds(400, 50, 400, 20);
				this.panelFiltre.add(new JLabel("Filtrer les candidats par : "));
				this.panelFiltre.add(this.txtFiltre);
				this.panelFiltre.add(this.btFiltrer);
				this.btFiltrer.addActionListener(this);
				this.add(this.panelFiltre);
				
				//installation du label nb candidats
				
				this.lbNbCandidats.setBounds(450,440,400,20);
				this.lbNbCandidats.setText("Nombre de candidats : " + this.unTableau.getRowCount());
				this.add(this.lbNbCandidats);
						
		
	}

	public Object[][] obtenirDonnees(String filtre) {
		
		//récuperer les candidats de la base de données
		ArrayList<Candidat> lesCandidats ;
		if (filtre.equals("")) {
			lesCandidats = Controleur.selectAllCandidats();
			}else {
				lesCandidats = Controleur.selectLikeCandidats(filtre);
			}
		//création d'une matrice de données
		Object[][] matrice = new Object[lesCandidats.size()][7];
		int i = 0;
		for (Candidat unCandidat : lesCandidats) {
			matrice[i][0] = unCandidat.getIdCandidat();
			matrice[i][1] = unCandidat.getNom();
			matrice[i][2] = unCandidat.getPrenom();
			matrice[i][3] = unCandidat.getAge();
			matrice[i][4] = unCandidat.getEmail();
			//matrice[i][5] = unCandidat.getMdp();
			matrice[i][5] = unCandidat.getType_User();
			matrice[i][6] = unCandidat.getNumero_Telephone();
			i++;
		}
		return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textNom.setText("");
			this.textPrenom.setText("");
			this.textAge.setText("");
			this.textEmail.setText("");
			this.textMdp.setText("");
			this.textType_User.setText("");
			this.textNumero_Telephone.setText("");
			//on affiche un message d'echec d'ajout 
			JOptionPane.showMessageDialog(this,"confirmez l'annulation");
		}
		else if (e.getSource() ==this.btValider) {
		// recuperer les champs saisis
		String nom = this.textNom.getText();
		String prenom = this.textPrenom.getText();
		String Age = this.textAge.getText();
		String Email = this.textEmail.getText();
		String Mdp= this.textMdp.getText();
		String Type_User = this.textType_User.getText();
		String Numero_Telephone = this.textNumero_Telephone.getText();
		//instanciation de la classe Candidat
		Candidat unCandidat=new Candidat (nom,prenom,Age,Email,Mdp,Type_User,Numero_Telephone);
		// inserer dans la base de donnees
		Controleur.insertCandidat(unCandidat);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du candidat");
		
		
		
		//on vide les champs
		this.textNom.setText("");
		this.textPrenom.setText("");
		this.textAge.setText("");
		this.textEmail.setText("");
		this.textMdp.setText("");
		this.textType_User.setText("");
		this.textNumero_Telephone.setText("");
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}

		else if (e.getSource() == this.btSupprimer) {
			//on recupere l'id du Candidat a supprimer
			int numLigne , idCandidat ;
			numLigne = this.uneTable.getSelectedRow();
			idCandidat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le Candidat ?",
					"Suppression du candidat", JOptionPane.YES_NO_OPTION);
			
			
			if (retour ==0) {
				
				//on supprime de la base de données
						Controleur.deleteVehicule(idCandidat);
						//on actualise l'affichage
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du candidat.");
						this.lbNbCandidats.setText("Nombre de candidat : " + this.unTableau.getRowCount());
						
						//on vide les champs
						this.textNom.setText("");
						this.textPrenom.setText("");
						this.textAge.setText("");
						this.textEmail.setText("");
						this.textMdp.setText("");
						this.textType_User.setText("");
						this.textNumero_Telephone.setText("");
						
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on récupère les données y compris l'id
			int numLigne , idCandidat ;
			numLigne = this.uneTable.getSelectedRow();
			idCandidat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			String nom = this.textNom.getText();
			String prenom = this.textPrenom.getText();
			String Age = this.textAge.getText();
			String Email= this.textEmail.getText();
			String mdp = this.textMdp.getText();
			String Type_User= this.textType_User.getText();
			String Numero_Telephone = this.textNumero_Telephone.getText();
			
			
			//on modifie dans la bdd
			Candidat unCandidat = new Candidat(idCandidat, nom, prenom, Age,Email,mdp,Type_User,Numero_Telephone);
			Controleur.updateCandidat(unCandidat);
			
			//on actualise l'affichage du tableau
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du candidat.");
			
			
			
			//message de confirmation et on vide les champs
			this.textNom.setText("");
			this.textPrenom.setText("");
			this.textAge.setText("");
			this.textEmail.setText("");
			this.textMdp.setText("");
			this.textType_User.setText("");
			this.textNumero_Telephone.setText("");
			
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btFiltrer) {
			//recuperer le filtre
			String filtre = this.txtFiltre.getText();
			
			//on actualise l'affichage du tableau avec les clients trouves
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		    }
		}
			
		
		
	}

	

