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

import Controleur.Controleur;
import Controleur.Moniteur;
import Controleur.PPEAUTO;
import Controleur.Tableau;
import Controleur.Vehicule;
import Controleur.Moniteur;
import Controleur.Moniteur;
import Controleur.Moniteur;

import javax.swing.JButton;

public class PanelMoniteur extends PanelPrincipal implements ActionListener
{
	
	private JPanel panelListe = new JPanel ();
	
	private JPanel panelForm=new JPanel ();
	private JTextField textNOm = new JTextField ();
	private JTextField textPrenOm = new JTextField ();
    private JTextField textEmail = new JTextField ();
	private JTextField textMdp = new JTextField ();
	private JTextField textType_User = new JTextField ();
	private JTextField textNUMERO_TELEPHONE = new JTextField ();
	private JTextField textidResponsable = new JTextField ();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	private JButton btSupprimer = new JButton("Supprimer");
	 
	private JTable uneTable ;
	private Tableau unTableau ;
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbMoniteurs = new JLabel();
	
	public PanelMoniteur() {
		super("Gestion des Moniteur"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(8,2));
		
		this.panelForm.add(new  JLabel("Nom Moniteur:"));
		this.panelForm.add(this.textNOm);
		
		this.panelForm.add(new  JLabel("Prenom Moniteur :"));
		this.panelForm.add(this.textPrenOm);
		this.panelForm.add(new  JLabel("Email  :"));
		this.panelForm.add(this.textEmail);
		
		this.panelForm.add(new  JLabel("Mdp :"));
		this.panelForm.add(this.textMdp);
		
		this.panelForm.add(new  JLabel("Type_User :"));
		this.panelForm.add(this.textType_User);
		
		this.panelForm.add(new  JLabel("Numero_Telephone:"));
		this.panelForm.add(this.textNUMERO_TELEPHONE);
		
		this.panelForm.add(new  JLabel("idResponsable"));
		this.panelForm.add(this.textidResponsable);
		
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		
		//installation de la JTable
		String entetes [] = {"IdMoniteur","Nom", "Prénom", "Email","Type user","Téléphone","idresponsable"};
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
					textNOm.setText(unTableau.getValueAt(numLigne, 1).toString());
					textPrenOm.setText(unTableau.getValueAt(numLigne,2).toString());
					textEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
					//textMdp.setText(unTableau.getValueAt(numLigne, 4).toString());
					textType_User .setText(unTableau.getValueAt(numLigne, 4).toString());
					textNUMERO_TELEPHONE .setText(unTableau.getValueAt(numLigne, 5).toString());
					textidResponsable .setText(unTableau.getValueAt(numLigne, 6).toString());
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
		//installation du panel filtre
		this.panelFiltre.setBackground(PPEAUTO.getCouleur());
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 400, 20);
		this.panelFiltre.add(new JLabel("Filtrer les moniteurs par : "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		this.add(this.panelFiltre);
		
		//installation du label lbNbMoniteurs
		
		this.lbNbMoniteurs.setBounds(450,440,400,20);
		this.lbNbMoniteurs.setText("Nombre de moniteurs : " + this.unTableau.getColumnCount());
		this.add(this.lbNbMoniteurs);
				
	}
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les moniteurs de la base de données
		ArrayList<Moniteur> lesMoniteurs ;
		if (filtre.equals("")) {
			lesMoniteurs = Controleur.selectAllMoniteurs();
			}else {
				lesMoniteurs = Controleur.selectLikeMoniteurs(filtre);
			}
		//création d'une matrice de données
		Object[][] matrice = new Object[lesMoniteurs.size()][7];
		int i = 0;
		for (Moniteur unMoniteur : lesMoniteurs) {
			matrice[i][0] = unMoniteur.getIdMoniteur();
			matrice[i][1] = unMoniteur.getNOm();
			matrice[i][2] = unMoniteur.getPrenOm();
			//matrice[i][3] = unMoniteur.getMdp();
			matrice[i][3] = unMoniteur.getEmail();
			matrice[i][4] = unMoniteur.getType_User ();
			matrice[i][5] = unMoniteur.getNUMERO_TELEPHONE ();
			matrice[i][6] = unMoniteur.getIdresponsable ();
			i++;
		}
		return matrice ;
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textNOm.setText("");
			this.textPrenOm.setText("");
		    this.textEmail.setText("");
			this.textMdp.setText("");
			this.textType_User.setText("");
			this.textNUMERO_TELEPHONE.setText("");
			this.textidResponsable.setText("");
			
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}
		else if (e.getSource() ==this.btValider && this.btValider.getText().equals("Valider")) {
		// recuperer les champs saisis
		
		String nom = this.textNOm.getText();
		String prenom = this.textPrenOm.getText();
		String email = this.textEmail.getText();
		String Mdp = this.textMdp.getText();
		String Type_User = this.textType_User.getText();
		String NUMERO_TELEPHONE = this.textNUMERO_TELEPHONE.getText();
		int idResponsable = Integer.parseInt(this.textidResponsable.getText());
		//instanciation de la classe Moniteur
		Moniteur unMoniteur=new Moniteur(nom,prenom,email,Mdp, Type_User,NUMERO_TELEPHONE, idResponsable);
		// inserer dans la base de donnees
		Controleur.insertMoniteur(unMoniteur);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du Moniteur");
		
		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		
		//on vide les champs
		this.textNOm.setText("");
		this.textPrenOm.setText("");
		this.textEmail.setText("");
		this.textMdp.setText("");
		this.textType_User.setText("");
		this.textNUMERO_TELEPHONE.setText("");
		this.textidResponsable.setText("");
		
		btSupprimer.setVisible(false);
		btValider.setText("Valider");
	}
	else if (e.getSource() == this.btSupprimer) {
		//on recupere l'id du moniteur a supprimer
		int numLigne , idMoniteur ;
		numLigne = this.uneTable.getSelectedRow();
		idMoniteur = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
		int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le moniteur ?",
				"Suppression du moniteur", JOptionPane.YES_NO_OPTION);
		
		if (retour ==0) {
					
			//on supprime de la base de données
					Controleur.deleteMoniteur(idMoniteur);
					
					//on actualise l'affichage
					this.unTableau.setDonnees(this.obtenirDonnees(""));
					JOptionPane.showMessageDialog(this, "Suppression réussie du Moniteur.");
					this.lbNbMoniteurs.setText("Nombre de moniteur : " + this.unTableau.getRowCount());
					
					
					//on vide les champs
					this.textNOm.setText("");
					this.textPrenOm.setText("");
					this.textEmail.setText("");
					this.textMdp.setText("");
					this.textType_User.setText("");
					this.textNUMERO_TELEPHONE.setText("");
					this.textidResponsable.setText("");
					btSupprimer.setVisible(false);
					btValider.setText("Valider");
		}
	}
	else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
		//on récupère les données y compris l'id
		int numLigne , idMoniteur ;
		numLigne = this.uneTable.getSelectedRow();
		idMoniteur = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
		String nom = this.textNOm.getText();
		String prenom = this.textPrenOm.getText();
		String email = this.textEmail.getText();
		String mdp = this.textMdp.getText();
		String type_user = this.textType_User.getText();
		String numero_telephone = this.textNUMERO_TELEPHONE.getText();
		int idResponsable = Integer.parseInt(this.textidResponsable.getText());
		
		
		//on modifie dans la bdd
		Moniteur unMoniteur = new Moniteur(idMoniteur, nom, prenom, email, mdp,type_user, numero_telephone,idResponsable);
		Controleur.updateMoniteur(unMoniteur);
		
		//on actualise l'affichage du tableau
		this.unTableau.setDonnees(this.obtenirDonnees(""));
		JOptionPane.showMessageDialog(this, "Modification réussie du client.");
		
		
		//message de confirmation et on vide les champs
		this.textNOm.setText("");
		this.textPrenOm.setText("");
		this.textEmail.setText("");
		this.textMdp.setText("");
		this.textType_User.setText("");
		this.textNUMERO_TELEPHONE.setText("");
		this.textidResponsable.setText("");
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