package View;

import javax.swing.*;
import Contrats.InterfaceOuvrirPatient;

public class OuvrirPatientDialog extends JDialog implements InterfaceOuvrirPatient{
    private String nomPatient = null;
    public OuvrirPatientDialog(){
        setTitle("Ouvrir Patient");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    public String afficherEtRecupererNomPatient() {
        String nom = JOptionPane.showInputDialog(this, "Entrez le nom", "OuvrirPatient", JOptionPane.QUESTION_MESSAGE);
        return nom;
    }
}
