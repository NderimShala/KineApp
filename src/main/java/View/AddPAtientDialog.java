package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Contrats.InterfaceNouveauPatient;
import Contrats.InterfaceNouveauPatientListener;

public class AddPAtientDialog extends JDialog implements InterfaceNouveauPatient {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nomPatient;
    private JTextField prenomPatient;
    private JComboBox mutuellePatient;
    private JTextField pathoPatient;
    private JLabel nomPatientLabel;
    private JLabel prenomPatientLabel;
    private JLabel mutuellePatientLabel;
    private JLabel pathoPathientLabel;

    private InterfaceNouveauPatientListener listener;

    public AddPAtientDialog() {
        setTitle("Nouveau Patient");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nomPatientLabel = new JLabel("Nom");
        nomPatient = new JTextField(15);

        prenomPatientLabel = new JLabel("Prenom");
        prenomPatient = new JTextField(15);

        mutuellePatientLabel = new JLabel("Mutuelle");
        mutuellePatient = new JComboBox();
        mutuellePatient.addItem("Solidaris");
        mutuellePatient.addItem("Partenamut");
        mutuellePatient.addItem("Mutualité Chrétienne");
        mutuellePatient.addItem("Mutualité Socialiste");
        mutuellePatient.addItem("Mutualité Libérale");

        pathoPathientLabel = new JLabel("Pathologie");
        pathoPatient = new JTextField(15);

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(nomPatientLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(nomPatient, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(prenomPatientLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(prenomPatient, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(mutuellePatientLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(mutuellePatient, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(pathoPathientLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(pathoPatient, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPane.add(buttonPanel, gbc);

        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        pack();

        buttonOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { onOk();}
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }
    @Override
    public String getNom() {
        return nomPatient.getText();
    }

    @Override
    public String getPrenom() {
        return prenomPatient.getText();
    }

    @Override
    public String getMutuelle() {
        return mutuellePatient.getSelectedItem().toString();
    }

    @Override
    public String getNomPatho() {
        return pathoPatient.getText();
    }

    @Override
    public void setListener(InterfaceNouveauPatientListener listener) {
        this.listener = listener;
    }

    @Override
    public void fermer() {
        dispose();
    }

    private void onCancel(){
        if(listener != null) listener.onCancelPatientClicked();
        fermer();
    }

    private void onOk(){
        if(listener!= null){
            listener.onNouveauPatientClicked(getNom(), getPrenom(), getMutuelle(), getNomPatho());
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        AddPAtientDialog dialog = new AddPAtientDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
