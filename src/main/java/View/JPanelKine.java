package View;

import entity.Kine;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Contrats.InterfaceKineModel;

public class JPanelKine extends JPanel implements InterfaceKineModel {
    private JPanel panelKine;
    private JLabel photoField;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;

    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel specialisationLabel;
    private JLabel inamiLabel;

    public JPanelKine() {
        // Initialiser les composants

        this.setLayout(new GridBagLayout());
        this.setBorder(new EmptyBorder(15,15,15,15));
        GridBagConstraints gbc = new GridBagConstraints();

        photoField = new JLabel();
        nomLabel = new JLabel();
        prenomLabel = new JLabel();
        specialisationLabel = new JLabel();
        inamiLabel = new JLabel();

        Font gras = new Font("Arial",Font.BOLD, 14);
        nomLabel.setFont(gras);
        prenomLabel.setFont(gras);
        specialisationLabel.setFont(gras);
        inamiLabel.setFont(gras);

        //configuer photo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; //aligne au sommet
        gbc.insets = new Insets(0,0,0,20); //marge de 20px sur la droite de la photo

        //Panneel de gauche
        JPanel leftPanel = new JPanel(new BorderLayout());
        photoField.setPreferredSize(new Dimension(150, 150));
        leftPanel.add(photoField, BorderLayout.CENTER);
        this.add(leftPanel, gbc);


        //Panel de droite
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(nomLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(prenomLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(specialisationLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(inamiLabel);

        gbc.gridx = 1;
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 1.0; //prend toute la largeur restante
        gbc.weighty = 1.0; //pousse tout vers le haut
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(rightPanel, gbc);
    }

    public void afficherKine(Kine kine) {
        nomLabel.setText("Nom : " + kine.getName());
        prenomLabel.setText("Prénom : " + kine.getFirstname());
        specialisationLabel.setText("Spécialisation : " + kine.getSpecialisation());
        inamiLabel.setText("INAMI : " + kine.getInami());
        photoField.setIcon(new ImageIcon(getClass().getResource(kine.getKinePhoto())));
    }

}