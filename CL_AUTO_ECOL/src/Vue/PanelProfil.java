package Vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controleur.PPEAUTO;
import Controleur.Responsable;

public class PanelProfil extends PanelPrincipal  {
	private Responsable respConnecte;
	private JTextArea TextInfos = new JTextArea();
	private JButton btModifier = new JButton("Modifier");
	private JPanel panelForm= new JPanel();

	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtType_User = new JTextField();
	private JTextField txtNumero_Telephone = new JTextField();
	private JPasswordField txtMdp1 = new JPasswordField(); 
	private JPasswordField txtMdp2 = new JPasswordField(); 
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider= new JButton("Valider");
	
	private JTextField textNom = new JTextField();
	public PanelProfil() {
		
		super("Gestion du profil");
	
		this.respConnecte =PPEAUTO.getRespConnecte();
		this.TextInfos.setText(
		"\n__________Infos Profil__________\n\n"
		+ "  Nom Responsable : " + this.respConnecte.getNom()+ "\n\n"
		+ "  Prenom Responsable : " + this.respConnecte.getPrenom()+ "\n\n"
		+ "  Email : " + this.respConnecte.getEmail()+ "\n\n"
		+ "   Type_User Responsable : " + this.respConnecte.getType_User()+ "\n\n"
		+ "  Numero_Telephone Reponsable : " + this.respConnecte.getNumero_Telephone ()+ "\n\n"
		 +"__________Infos Profil __________\n"
		);
		this.TextInfos.setBackground(Color.cyan);
		this.TextInfos.setBounds(50,100,300,250);
		this.TextInfos.setEditable(false);
		this.add(this.TextInfos);
		
		this.btModifier.setBounds(150,380,120,300);
		this.add(this.btModifier);
		 
		// instalation du panel form 
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(400,100,400,300);
		this.panelForm.setLayout(new GridLayout(8,2));
		
		this.panelForm.add(new JLabel("Nom du responsable :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("prenom du responsable:"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Email responsable :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("type_User du responsable :"));
		
		this.panelForm.add(new JLabel("Numero_Telephone du responsable :"));
		
		this.panelForm.add(new JLabel("Mdp1 :"));
		this.panelForm.add(this.txtMdp1);
		this.panelForm.add(new JLabel("Mdp2 :"));
		this.panelForm.add(this.txtMdp2);
		this.panelForm.add(this.btModifier);
		this.panelForm.add(btAnnuler);
		this.panelForm.add(btValider);
		
		this.panelForm.setVisible(false);
		
		
		
		}
}