package Vue;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Plannings;
import Controleur.Tableau;
import Controleur.Controleur;
import Controleur.Moniteur;
import Controleur.PPEAUTO;

import javax.swing.JButton;

public class PanelPlannings extends PanelPrincipal implements ActionListener
{
	
	private JPanel panelForm=new JPanel ();
	private JTextField textIdLecon = new JTextField ();
	private JTextField textIdCandidat = new JTextField ();
	private JTextField textDateheurdebut = new JTextField ();
	private JTextField textDateheurfin  = new JTextField ();
	private JTextField textidMoniteur  = new JTextField ();
    private JTextField textEtat = new JTextField ();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");

	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel ();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");
	
	private JLabel lbNbPlannings = new JLabel();
	
	
	
	public PanelPlannings() {
		super("Gestion des Plannings"); 
		//installation du formulaire
		this.panelForm.setBackground(PPEAUTO.getCouleur());
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout(7,2));
		
		this.panelForm.add(new  JLabel("IdLecon  :"));
		this.panelForm.add(this.textIdLecon );
		
		this.panelForm.add(new  JLabel("IdCandidat :"));
		this.panelForm.add(this.textIdCandidat);
		
		this.panelForm.add(new  JLabel("Dateheurdebut  Plannings:"));
		this.panelForm.add(this.textDateheurdebut);
		
		this.panelForm.add(new  JLabel("Dateheurfin Plannings :"));
		this.panelForm.add(this.textDateheurfin);
		
		
		this.panelForm.add(new  JLabel("idMoniteur Plannings :"));
		this.panelForm.add(this.textidMoniteur);
		
		this.panelForm.add(new  JLabel("EtAt  :"));
		this.panelForm.add(this.textEtat);
		
	
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
		//on ajoute le formulaire dans la vue
		this.add(this.panelForm);
		
		//rendre les bouttons clicables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable 
		
		String entetes [] = {"IdLecon","idCandidat", "date début", "date fin", "Id Moniteur", "Etat"};
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
					textIdLecon.setText(unTableau.getValueAt(numLigne, 1).toString());
					textIdCandidat.setText(unTableau.getValueAt(numLigne,2).toString());
					textDateheurdebut.setText(unTableau.getValueAt(numLigne, 3).toString());
					textDateheurfin.setText(unTableau.getValueAt(numLigne, 4).toString());
					textidMoniteur.setText(unTableau.getValueAt(numLigne,5).toString());
					textEtat.setText(unTableau.getValueAt(numLigne, 6).toString());
					
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
				
				//installation du label lbNbplannings
				
				this.lbNbPlannings.setBounds(450,440,400,20);
				this.lbNbPlannings.setText("Nombre de Plannings : " + this.unTableau.getColumnCount());
				this.add(this.lbNbPlannings);
						
			}
			public Object[][] obtenirDonnees (String filtre){
				//récuperer les moniteurs de la base de données
				ArrayList<Plannings> lesPlannings ;
				if (filtre.equals("")) {
					lesPlannings = Controleur.selectAllPlannings();
					}else {
						lesPlannings = Controleur.selectLikePlannings(filtre);
					}
				//création d'une matrice de données
				Object[][] matrice = new Object[lesPlannings.size()][6];
				int i = 0;
				for (Plannings unPlannings : lesPlannings) {
					matrice[i][0] = unPlannings.getIdLecon();
					matrice[i][1] = unPlannings.getIdCandidat();
					matrice[i][2] = unPlannings.getDATEHEURDEBUT();
					//matrice[i][3] = unPlannings.getMdp();
					matrice[i][3] = unPlannings.getDATEHEURFIN();
					matrice[i][4] = unPlannings.getIdMoniteur ();
					matrice[i][5] = unPlannings.getEtat ();
				
					i++;
				}
				return matrice ;
				
			}
	

	private Object[][] obtenirDonnees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.btAnnuler) {
			this.textIdLecon.setText("");
			this.textIdCandidat.setText("");
			this.textDateheurdebut.setText("");
			this.textDateheurfin.setText("");
			this.textidMoniteur.setText("");
		    this.textEtat.setText("");
		
		}
		else if (e.getSource() ==this.btValider) {
		// recuperer les champs saisis
		int IdLecon = Integer.parseInt(this.textIdLecon.getText());
		int IdCandidat = Integer.parseInt(this.textIdCandidat.getText());
		String Dateheurdebut = this.textDateheurdebut.getText();
		String Dateheurfin= this.textDateheurfin.getText();
		int idMoniteur= Integer.parseInt( this.textidMoniteur.getText());
		String Etat = this.textEtat.getText();
	
		//instanciation de la classe Plannings
		Plannings unPlannings=new Plannings(IdLecon,IdCandidat,Dateheurdebut,idMoniteur, Dateheurfin ,Etat);
		
		// inserer dans la base de donnees
		Controleur.insertPlannings(unPlannings);
		
		//on affiche un message d insertion 
		JOptionPane.showMessageDialog(this,"insertion reussi du Plannings");
		
		//on vide les champs
		this.textIdLecon.setText("");
		this.textIdCandidat.setText("");
		this.textDateheurdebut.setText("");
		this.textDateheurfin.setText("");
		this.textidMoniteur.setText("");
		this.textEtat.setText("");
		
	}
	else if (e.getSource() == this.btFiltrer) {
		//recuperer le filtre
		String filtre = this.txtFiltre.getText();
		
		//on actualise l'affichage du tableau avec les clients trouves
		this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		
	    }
}
}