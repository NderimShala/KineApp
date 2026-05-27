package Properties;

import Contrats.InterfaceEntityService;
import entity.Objectif;
import entity.Patient;

public class ObjectifService implements InterfaceEntityService<Objectif>{

    public void ajouterObjectifPatient(Patient patient, Objectif objectif){
        Objectif[] objectifsExistants = patient.getMaladie().getObjectif();
        Objectif[] nouveauxObjectifs = new Objectif[objectifsExistants.length + 1];
        System.arraycopy(objectifsExistants, 0, nouveauxObjectifs, 0, objectifsExistants.length);
        nouveauxObjectifs[objectifsExistants.length] = objectif;
        patient.getMaladie().setObjectif(nouveauxObjectifs);
    }

    @Override
    public Objectif creer(String... parametres) {
        String nomObjectif = parametres[0];
        if (nomObjectif == null || nomObjectif.isBlank()) {
            System.out.println("Erreur : nom vide");
            return null;
        }
        Objectif objectif = new Objectif(nomObjectif);
        System.out.println("Objectif créé par ObjectifService :" + nomObjectif);
        return objectif;
    }
}
