package Vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.Controleur;
import Controleur.PPEAUTO;
import Controleur.Responsable;
import Controleur.Tableau;

public class PanelResponsable extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField textNom = new JTextField();
    private JTextField textPrenom = new JTextField();
    // Remplacer JTextField textPoste par JComboBox
    private JComboBox<String> comboPoste;
    private JTextField textEmail = new JTextField();
    private JTextField textMdp = new JTextField();
    private JTextField textType_User = new JTextField();
    private JTextField textnumero_Telephone = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable uneTable;
    private Tableau unTableau;
    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    private JLabel lbNbResponsables = new JLabel();

    public PanelResponsable() {
        super("Gestion des Responsable");
        //installation du formulaire
        this.panelForm.setBackground(PPEAUTO.getCouleur());
        this.panelForm.setBounds(40, 80, 300, 220);
        this.panelForm.setLayout(new GridLayout(10, 2));

        this.panelForm.add(new JLabel("Nom Responsable:"));
        this.panelForm.add(this.textNom);

        this.panelForm.add(new JLabel("Prenom Responsable :"));
        this.panelForm.add(this.textPrenom);

        this.panelForm.add(new JLabel("Email  :"));
        this.panelForm.add(this.textEmail);

        this.panelForm.add(new JLabel("Mdp :"));
        this.panelForm.add(this.textMdp);

        this.panelForm.add(new JLabel("Type_User :"));
        this.panelForm.add(this.textType_User);

        this.panelForm.add(new JLabel("Numero_Telephone:"));
        this.panelForm.add(this.textnumero_Telephone);

        // Ajout du JComboBox pour le poste
        this.panelForm.add(new JLabel("Poste:"));
        String[] postes = {"Administrateur", "Directeur", "Admin"};
        this.comboPoste = new JComboBox<>(postes);
        this.panelForm.add(this.comboPoste);
        //this.panelForm.add(this.textPoste); // Supprimer l'ancien JTextField

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.panelForm.add(this.btSupprimer);

        //on ajoute le formulaire dans la vue
        this.add(this.panelForm);

        //rendre les bouttons clicables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.btSupprimer.addActionListener(this);

        //installation de la JTable
        String entetes[] = {"idResponsable", "Nom", "Prenom", "Email", "Mdp", "Type_User ", "Numero_Telephone", "Poste"};
        this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
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
                int numLigne = 0;

                if (e.getClickCount() >= 1) {
                    numLigne = uneTable.getSelectedRow();
                    textNom.setText(unTableau.getValueAt(numLigne, 1).toString());
                    textPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
                    textEmail.setText(unTableau.getValueAt(numLigne, 3).toString());
                    textMdp.setText(unTableau.getValueAt(numLigne, 4).toString()); // Correction : indice 4 pour le MDP
                    textType_User.setText(unTableau.getValueAt(numLigne, 5).toString());
                    textnumero_Telephone.setText(unTableau.getValueAt(numLigne, 6).toString());
                    comboPoste.setSelectedItem(unTableau.getValueAt(numLigne, 7).toString()); // Utilisation du JComboBox
                    // textPoste.setText(unTableau.getValueAt(numLigne, 6).toString()); // Ancien JTextField

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

        this.lbNbResponsables.setBounds(450, 440, 400, 20);
        this.lbNbResponsables.setText("Nombre de responsables : " + this.unTableau.getRowCount());
        this.add(this.lbNbResponsables);


    }

    private Object[][] obtenirDonnees(String filtre) {

        //récuperer les responsables  de la base de données
        ArrayList<Responsable> lesResponsables;
        if (filtre.equals("")) {
            lesResponsables = Controleur.selectAllResponsables();
        } else {
            lesResponsables = Controleur.selectLikeResponsable(filtre);
        }
        //création d'une matrice de données
        Object[][] matrice = new Object[lesResponsables.size()][8];
        int i = 0;
        for (Responsable unResponsable : lesResponsables) {
            matrice[i][0] = unResponsable.getIdResponsable();
            matrice[i][1] = unResponsable.getNom();
            matrice[i][2] = unResponsable.getPrenom();
            matrice[i][3] = unResponsable.getEmail();
            matrice[i][4] = unResponsable.getMdp();
            //matrice[i][5] = unCandidat.getMdp();
            matrice[i][5] = unResponsable.getType_User();
            matrice[i][6] = unResponsable.getNumero_Telephone();
            matrice[i][7] = unResponsable.getPoste();
            i++;
        }
        return matrice;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btAnnuler) {
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textEmail.setText("");
            this.textMdp.setText("");
            this.textType_User.setText("");
            this.textnumero_Telephone.setText("");
            this.comboPoste.setSelectedIndex(0); // Réinitialiser le JComboBox
            //this.textPoste.setText(""); // Ancien JTextField
        } else if (e.getSource() == this.btValider) {
            // recuperer les champs saisis
            String nom = this.textNom.getText();
            String prenom = this.textPrenom.getText();
            String email = this.textEmail.getText();
            String Mdp = this.textMdp.getText();
            String Type_User = this.textType_User.getText();
            String numero_telephone = this.textnumero_Telephone.getText();
            String poste = (String) this.comboPoste.getSelectedItem(); // Récupérer la valeur du JComboBox
            //String poste = this.textPoste.getText(); // Ancien JTextField
            //instanciation de la classe Responsable
            Responsable unResponsable = new Responsable(nom, prenom, email, Mdp, Type_User, numero_telephone, poste);
            // inserer dans la base de donnees
            Controleur.insertResponsable(unResponsable);

            //on affiche un message d insertion
            JOptionPane.showMessageDialog(this, "insertion reussi du Responsable");

            //on actualise l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees(""));

            //on vide les champs
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textEmail.setText("");
            this.textMdp.setText("");
            this.textType_User.setText("");
            this.textnumero_Telephone.setText("");
            this.comboPoste.setSelectedIndex(0); // Réinitialiser le JComboBox
            //this.textPoste.setText(""); // Ancien JTextField

            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }


        else if (e.getSource() == this.btSupprimer) {
            //on recupere l'id du vehicule a supprimer
            int numLigne, idResponsable;
            numLigne = this.uneTable.getSelectedRow();
            idResponsable = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le Vehicule ?",
                    "Suppression du vehicule", JOptionPane.YES_NO_OPTION);

            if (retour == 0) {

                //on supprime de la base de données
                Controleur.deleteResponsable(idResponsable);
                //on actualise l'affichage
                this.unTableau.setDonnees(this.obtenirDonnees(""));
                JOptionPane.showMessageDialog(this, "Suppression réussie du Responsable.");
                this.lbNbResponsables.setText("Nombre de responsable : " + this.unTableau.getRowCount());

                //on vide les champs
                this.textNom.setText("");
                this.textPrenom.setText("");
                this.textEmail.setText("");
                this.textMdp.setText("");
                this.textType_User.setText("");
                this.textnumero_Telephone.setText("");
                this.comboPoste.setSelectedIndex(0); // Réinitialiser le JComboBox
                //this.textPoste.setText(""); // Ancien JTextField
                btSupprimer.setVisible(true);
                btValider.setText("Valider");
            }
        } else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            //on récupère les données y compris l'id
            int numLigne, idResponsable;
            numLigne = this.uneTable.getSelectedRow();
            idResponsable = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
            String nom = this.textNom.getText();
            String prenom = this.textPrenom.getText();
            String Email = this.textEmail.getText();
            String mdp = this.textMdp.getText();
            String Type_User = this.textType_User.getText();
            String Numero_Telephone = this.textnumero_Telephone.getText();
            String Poste = (String) this.comboPoste.getSelectedItem(); // Récupérer la valeur du JComboBox
            //String Poste = this.textPoste.getText(); // Ancien JTextField

            //on modifie dans la bdd
            Responsable unResponsable = new Responsable(idResponsable, nom, prenom, Email, mdp, Type_User, Numero_Telephone, Poste);
            Controleur.updateResponsable(unResponsable);

            //on actualise l'affichage du tableau
            this.unTableau.setDonnees(this.obtenirDonnees(""));
            JOptionPane.showMessageDialog(this, "Modification réussie du responsable.");

            //message de confirmation et on vide les champs
            this.textNom.setText("");
            this.textPrenom.setText("");
            this.textEmail.setText("");
            this.textMdp.setText("");
            this.textType_User.setText("");
            this.textnumero_Telephone.setText("");
            this.comboPoste.setSelectedIndex(0); // Réinitialiser le JComboBox
            //this.textPoste.setText(""); // Ancien JTextField

            btSupprimer.setVisible(false);
            btValider.setText("Valider");
        }


        else if (e.getSource() == this.btFiltrer) {
            //recuperer le filtre
            String filtre = this.txtFiltre.getText();

            //on actualise l'affichage du tableau avec les responsable trouves
            this.unTableau.setDonnees(this.obtenirDonnees(filtre));

        }
    }

}