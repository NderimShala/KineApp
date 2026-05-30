package Properties;

import entity.Patient;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private static final String FilePath = System.getProperty("user.dir") + File.separator + "src"
                                        + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "patients.bin";
    private List<Patient> patients;

    public PatientDAO(){
        this.patients = new ArrayList<>();
        creerDossierSiNecessaire();
        load();
    }

    private void creerDossierSiNecessaire(){
        File dataPatients = new File(System.getProperty("user.dir") + File.separator
                                            + "src" + File.separator + "main" + File.separator
                                            + "ressources" + File.separator + "data");
        if (!dataPatients.exists()){
            dataPatients.mkdirs();
            System.out.println("Dossier créé : " + dataPatients.getAbsolutePath());
        }
    }

    public void save(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FilePath))){
            oos.writeObject(patients);
        } catch(IOException e){
            System.err.println("Erreur de sauvegarde des patients : " + e.getMessage());
        }

    }

    public void load(){
        File dataPatients = new File(FilePath);
        if(!dataPatients.exists()){
            this.patients = new ArrayList<>();
            return;
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FilePath))){
            this.patients = (List<Patient>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Erreur de chargement des patients : " + e.getMessage());
            this.patients = new ArrayList<>();
        }
    }

    public void addPatient(Patient patient){
        patients.add(patient);
        save();
    }
    public List<Patient> getAllPatients(){
        return new ArrayList<>(patients);
    }
    public Patient getPatient(int index){
        if(index >= 0 && index < patients.size()){
            return patients.get(index);
        }
        return null;
    }
    public void updatePatient(int index, Patient patient){
        if(index >= 0 && index < patients.size()){
            patients.set(index, patient);
            save();
        }
    }
    public void deletePatient(int index){
        if(index >= 0 && index < patients.size()){
            patients.remove(index);
            save();
        }
    }

    public Patient findByName(String nom){
        for(Patient patient : patients){
            if(patient.getName().equalsIgnoreCase(nom)){
                return patient;
            }
        }
        return null;
    }

    public void deleteByName(String nom){
        for(int i =0; i< patients.size(); i++){
            if(patients.get(i).getName().equalsIgnoreCase(nom)){
                patients.remove(i);
                save();
                System.out.println("patient supprimé avec succès : ");
                return;
            }
        }
        System.out.println("patient non trouvé");
    }
}
