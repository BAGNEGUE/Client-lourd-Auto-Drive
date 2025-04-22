package Vue;

import java.awt.Color;



import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.PPEAUTO;

public class PanelPrincipal extends JPanel 
{
	public PanelPrincipal(String titre) {
		this.setBounds(20,80,960, 470 );
		this.setLayout(null);
		this.setBackground(PPEAUTO.getCouleur());
		
		JLabel lbTitre = new JLabel(titre); 
		lbTitre.setBounds(350, 20, 200,20);
		this.add(lbTitre);
		
		this.setVisible(false);
	}
}
