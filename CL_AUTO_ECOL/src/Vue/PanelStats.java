package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
import Controleur.Controleur;
import Controleur.Tableau;
import Controleur.VCandidat;


public class PanelStats extends PanelPrincipal {
	private static Tableau unTableau;
	private static JTable uneTable;
	
	private JPanel panelCount = new JPanel();

	public PanelStats() {
		super ("Gestion des statistiques");
		String entetes []= {"Nom","Prenom","Age","Email","Type_User","Numero_Telephone"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(20,80,400,350);
		this.add(uneScroll);
		this.add(uneScroll);
		
		this.panelCount.setBackground(Color.cyan);
		this.panelCount.setLayout(new GridLayout(2,2));
		this.panelCount.setBounds(400,80,300,200);
		int nbCandidats = Controleur.count("Candidat");
		int nbExamens = Controleur.count("Examens");
		int nbFormules = Controleur.count("Formule");
		int nbLecon = Controleur.count("Lecon");
		int nbMoniteurs = Controleur.count("Moniteurs");
		int nbPlannings = Controleur.count("PlanningS");
		int nbResponsables = Controleur.count("Responsable");
		int nbVehicule = Controleur.count("Vehicule");
		
		
		this.panelCount.add(new JLabel("nombre de Candidats : " + nbCandidats));
		this.panelCount.add(new JLabel("nombre de Examens : " + nbExamens));
		this.panelCount.add(new JLabel("nombre de formules : " + nbFormules));
		this.panelCount.add(new JLabel("nombre de lecon : " + nbLecon));

		this.panelCount.add(new JLabel("nombre de Moniteurs : " + nbMoniteurs));
		this.panelCount.add(new JLabel("nombre de Plannings: " + nbPlannings));
		this.panelCount.add(new JLabel("nombre de Responsables : " + nbResponsables));
		this.panelCount.add(new JLabel("nombre de Vehicules : " + nbVehicule));
		
		this.add(this.panelCount);
		
		}
		public Object[][] obtenirDonnees (){
			//récuperer les Candidats de la base de données
			ArrayList<VCandidat> lesVCandidats ;
			lesVCandidats = Controleur.selectAllVCandidats();
			
			//installation du label nb clients
			
			
			//création d'une matrice de données
			Object[][] matrice = new Object[lesVCandidats.size()][5];
			int i = 0;
			for (VCandidat unVCandidat : lesVCandidats) {
				matrice[i][0] = unVCandidat.getNom();
				matrice[i][1] = unVCandidat.getPrenom();
				matrice[i][2] = unVCandidat.getAge();
				matrice[i][3] = unVCandidat.getEmail();
				matrice[i][4] = unVCandidat.getType_user();
				matrice[i][4] = unVCandidat.getNumero_telephone();
				
				i++;
			}
			return matrice ;
		}
		
	
}
