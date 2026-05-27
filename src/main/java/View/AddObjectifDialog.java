package View;

import Contrats.InterfaceAddObjectif;
import javax.swing.*;

public class AddObjectifDialog implements InterfaceAddObjectif {
    @Override
    public String afficherEtRecupererNomObjectif() {
        String nomObjectif = JOptionPane.showInputDialog(null, "Entrez le nom de l'objectif :");
        return nomObjectif;
    }

}
