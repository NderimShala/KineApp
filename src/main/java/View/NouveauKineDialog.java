package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.StringCharacterIterator;
import java.io.File;

import Contrats.InterfaceNouveauKine;
import Contrats.InterfaceNouveauKineListener;

public class NouveauKineDialog extends JDialog implements InterfaceNouveauKine {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nomKine;
    private JTextField prenomKine;
    private JTextField specialisation;
    private JTextField inami;
    private JButton photoButton;
    private JLabel nomLabel;
    private JLabel prnomLabel;
    private JLabel specialisationLabel;
    private JLabel inamiLabel;
    private JLabel photoLabel;

    private String usernameEnAttente;
    private String passwordEnAttente;
    private String photoSelectionnee = "/default.jpg";

    //listener pour communiquer avec le controleur
    private InterfaceNouveauKineListener listener;

    public NouveauKineDialog() {
        setTitle("Nouveau Kine");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nomLabel = new JLabel("Nom");
        nomKine = new JTextField(15);

        prnomLabel = new JLabel("Prénom");
        prenomKine = new JTextField(15);

        specialisationLabel = new JLabel("Spécialisation");
        specialisation = new JTextField(15);

        inamiLabel = new JLabel("Numéro Inami");
        inami = new JTextField(15);

        photoLabel = new JLabel("Photo");
        photoButton = new JButton("Choisir une photo");

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(nomLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(nomKine, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(prnomLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(prenomKine, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(specialisationLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(specialisation, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(inamiLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(inami, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(photoLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(photoButton, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        gbc.gridx= 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        contentPane.add(buttonPanel, gbc);

        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        pack();

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        photoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ouvrirSelecteurPhoto();
            }
        });

         addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 onCancel();
             }
         });

    }

    public void setLoginCredentials(String username, String password){
        this.usernameEnAttente = username;
        this.passwordEnAttente = password;
    }

    public void ouvrirSelecteurPhoto(){
        JFileChooser fileChooser = new JFileChooser();

        //pointer vers le dossier ressources
        String resourcePath = System.getProperty("user.dir") +
                            File.separator + "src" +
                            File.separator + "main" +
                            File.separator + "resources";
        fileChooser.setCurrentDirectory(new File(resourcePath));

        int result = fileChooser.showOpenDialog(this);
        java.io.File selectedFile = fileChooser.getSelectedFile();
        this.photoSelectionnee = "/" + selectedFile.getName();
        photoButton.setText("Photo : " + selectedFile.getName() );
    }

    //méthode de l'interface NouveauKine
    @Override
    public String getNom(){
        return nomKine.getText();
    }

    @Override
    public String getPrenom(){
        return prenomKine.getText();
    }

    @Override
    public String getSpecialisation(){
        return specialisation.getText();
    }

    @Override
    public String getInami(){
        return inami.getText();
    }

    @Override
    public String getPhoto(){
        return photoSelectionnee;
    }

    @Override
    public void setListener(InterfaceNouveauKineListener listener){
        this.listener = listener;
    }

    @Override
    public void fermer(){
        dispose();
    }

    @Override
    public void afficherMessageSuccesKine(){
        JOptionPane.showMessageDialog(this, "Kine créé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void afficherMessageErreurKine(String message){
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }


    private void onOK(){
        try{
            if(listener != null){
                listener.onNouveauKineClicked(usernameEnAttente, passwordEnAttente,getNom(), getPrenom(), getSpecialisation(), getInami(), getPhoto());
            }
        }catch (Exception e){afficherMessageErreurKine("Erreur : " + e.getMessage());}
    }


    private void onCancel(){
        if(listener != null) listener.onCancelClicked();
        fermer();
    }

    public static void main(String[] args) {
        NouveauKineDialog dialog = new NouveauKineDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
