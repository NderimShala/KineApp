package Properties;

import Contrats.InterfaceEntityService;
import entity.Patient;
import entity.Pathologie;

import java.nio.file.Path;

public class PatientsService implements InterfaceEntityService<Patient> {

    @Override
    public Patient creer(String... parametres) {
        String nom = parametres[0];
        String prenom = parametres[1];
        String mutuelle = parametres[2];
        String nomPatho = parametres[3];
        Pathologie pathologie = new Pathologie(nomPatho);
        pathologie.setName(nomPatho);
        Patient patient = new Patient(nom, prenom, mutuelle, pathologie);
        System.out.println("patient créé par PatientService");
        return patient;
    }
}
