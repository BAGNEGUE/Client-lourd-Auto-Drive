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

import Controleur.Formule;
import Controleur.PPEAUTO;
import Controleur.Tableau;
import Controleur.Formule;
import Controleur.Controleur;

import javax.swing.JButton;

public class PanelFormule extends PanelPrincipal implements ActionListener
{
	private JPanel panelListe = new JPanel ();
	private JPanel panelForm=new JPanel ();
	private JTextField textDescription = new JTextField ();


	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btsupprimer = new JButton("supprimer");
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbFormules = new JLabel();
	
	public PanelFormule() {
		super("Gestion des Formule"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(6,2));
		
		this.panelForm.add(new  JLabel("Description:"));
		this.panelForm.add(this.textDescription);
		
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		

		//installation de la JTable 
		String entetes [] = {"Num Formule" , "Description"};
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
					
					textDescription.setText(unTableau.getValueAt(numLigne,1).toString());
					
					 
					btsupprimer.setVisible(true);
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
		
		//installation du label nb clients
		
		this.lbNbFormules.setBounds(450,440,400,20);
		this.lbNbFormules.setText("Nombre de Formules : " + this.unTableau.getColumnCount());
		this.add(this.lbNbFormules);
		
		//rendre les boutons 
	//	this.txtFiltre.keyListener();
				
	}
	public Object[][] obtenirDonnees (String filtre){
		//récuperer les formules de la base de données
		ArrayList<Formule> lesFormules ;
		if (filtre.equals("")) {
			lesFormules = Controleur.selectAllFormules();
			}else {
				lesFormules = Controleur.selectLikeFormules(filtre);
			}
		//création d'une matrice de données
		Object[][] matrice = new Object[lesFormules.size()][2];
		int i = 0;
		for (Formule unFormule : lesFormules) {
			matrice[i][0] = unFormule.getNum_formule();
			matrice[i][1] = unFormule.getDescription();
			
			i++;
		}
		return matrice ;
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.textDescription.setText("");
			
			btsupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//recuperer les champs saisis
			String Description = this.textDescription.getText();
			
			
			//instancier la classe formule
			Formule unFormule = new Formule(Description );
			
			//inserer la formule dans la BDD
			Controleur.insertFormule(unFormule);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie de la formule.");
			
			//on actualise l'affichage du tableau
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.textDescription.setText("");
			
			
			btsupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btsupprimer) {
			//on recupere l'id du formule a supprimer
			int numLigne , Num_formule ;
			numLigne = this.uneTable.getSelectedRow();
			Num_formule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer la formule ?",
					"Suppression du formule", JOptionPane.YES_NO_OPTION);
			
			if (retour ==0) {
						
				//on supprime de la base de données
						Controleur.deleteFormule(Num_formule);
						//on actualise l'affichage
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du formule.");
						this.lbNbFormules.setText("Nombre de formules : " + this.unTableau.getRowCount());
						
						//on vide les champs
						this.textDescription.setText("");
						
						btsupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on récupère les données y compris l'id
			int numLigne , Num_formule ;
			numLigne = this.uneTable.getSelectedRow();
			Num_formule = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			String Description = this.textDescription.getText();
			
			
			//on modifie dans la bdd
			Formule unFormule = new Formule(Num_formule, Description);
			Controleur.updateFormule(unFormule);
			
			//on actualise l'affichage du tableau
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie de formule.");
			
			
			//message de confirmation et on vide les champs
			this.textDescription.setText("");
		
			
			btsupprimer.setVisible(false);
			btValider.setText("Valider");
		}
		else if (e.getSource() == this.btFiltrer) {
			//recuperer le filtre
			String filtre = this.txtFiltre.getText();
			
			//on actualise l'affichage du tableau avec les formules trouves
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
		    }
		}
}
