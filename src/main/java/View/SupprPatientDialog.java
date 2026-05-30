package View;

import javax.swing.*;
import Contrats.InterfaceSupprPatient;

public class SupprPatientDialog extends JDialog implements InterfaceSupprPatient{
    private String nomPatient = null;
    public SupprPatientDialog(){
        setTitle("Supprimer Patient");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    public String afficherEtRecupererNomPatient() {
        String nom = JOptionPane.showInputDialog(this, "Entres le nom", "SupprimerPatient", JOptionPane.QUESTION_MESSAGE);
        return nom;
    }
}
