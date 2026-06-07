package View;

import Contrats.InterfaceObjectif;
import javax.swing.*;

public class AddObjectifDialog implements InterfaceObjectif {
    @Override
    public String afficherEtRecupererNomObjectif() {
        String nomObjectif = JOptionPane.showInputDialog(null, "Entrez le nom de l'objectif :");
        return nomObjectif;
    }

    @Override
    public void afficherMessageObjectifVide(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onSupprObjectifClicked() throws Exception {
        // pas d'implémentation pour ce dialog
    }

    @Override
    public void onAjouterObjectifClicked() throws Exception {
        //pas d'implémentation pour ce dialog
    }
}
