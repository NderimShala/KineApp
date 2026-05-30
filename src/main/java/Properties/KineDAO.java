package Properties;

import Contrats.InterfaceKineDAO;
import Contrats.InterfaceUtilisateur;
import entity.Kine;

import javax.sound.sampled.DataLine;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.io.*;

public class KineDAO implements InterfaceKineDAO {
    private static final String CredentialsFilePath =
                    System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator + "data" +
                    File.separator + "credentials.bin";

    private static final String KineFilePath =
                    System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator + "data" +
                    File.separator + "kines.bin";

    private Map<String, String> credentials; //stocker username -> password
    private Map<String, InterfaceUtilisateur> kines; //stocker les différents kines : username -> kine

    public KineDAO(){
        this.credentials = new HashMap<>();
        this.kines = new HashMap<>();

        creerDossierSiNecessaire();
        loadKines();

        if(kines.isEmpty()){
            initializeDefaultUsers();
            saveKines();
        }
    }

    private void creerDossierSiNecessaire(){
        File dataKines = new File(System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" +
                        File.separator + "data");
        if(!dataKines.exists()){
            dataKines.mkdirs();
            System.out.println("Dossier créé avec succès : " + dataKines.getAbsolutePath());
        }
    }

    private void saveKines(){
        try (ObjectOutputStream oosCredentials = new ObjectOutputStream(new FileOutputStream(CredentialsFilePath))){
            oosCredentials.writeObject(credentials);
        }catch(IOException e){
            System.out.println("Erreur de sauvegarde des credentials : " + e.getMessage());
        }
        try (ObjectOutputStream oosKines = new ObjectOutputStream(new FileOutputStream(KineFilePath))){
            oosKines.writeObject(kines);
            System.out.println("kine souvegardés avec succès");
        }catch(IOException e) {
            System.out.println("Erreur de sauvegarde des kines : " + e.getMessage());
        }
    }

    private void loadKines(){
        File credFile = new File(CredentialsFilePath);
        File kineFile = new File(KineFilePath);

        if(credFile.exists()){
            try (ObjectInputStream oisCredentials = new ObjectInputStream(new FileInputStream(CredentialsFilePath))){
                this.credentials = (Map<String, String>) oisCredentials.readObject();
                System.out.println("credentials chargés avec succès");
            }catch( IOException | ClassNotFoundException e){
                System.out.println("erreur du load des credentials : " + e.getMessage());
                this.credentials = new HashMap<>();
            }
        }
        if(kineFile.exists()){
            try(ObjectInputStream oisKines = new ObjectInputStream(new FileInputStream(KineFilePath))){
                this.kines = (Map<String, InterfaceUtilisateur>) oisKines.readObject();
                System.out.println("kines chargés avec succès");
            }catch(IOException | ClassNotFoundException e){
                System.err.println("erreurs de chargement des kines : " + e.getMessage());
                this.kines = new HashMap<>();
            }
        }
    }

    private void initializeDefaultUsers(){
        Kine admin = new Kine("Admin", "Kine", "Généraliste", "12345", "/default.jpg");
        save("admin", "admin", admin);

        Kine testKine = new Kine("Test", "Test", "Test", "54321", "Nderim.jpg");
        save("test", "test", testKine);
    }

    @Override
    public Optional<InterfaceUtilisateur> findByUsername(String username){
        System.out.println("recherche utilisateur" + username);
        System.out.println("utilisateurs dispo" + kines.keySet());
        return Optional.ofNullable(kines.get(username));
    }
    @Override
    public boolean existsByUsername(String username){
        return kines.containsKey(username);
    }
    @Override
    public void save(String username, String password, InterfaceUtilisateur kine){
        if(username != null && !username.isBlank() && password != null && !password.isBlank() && kine != null){
            credentials.put(username, password);
            kines.put(username, kine);
            System.out.println("kine sauvegardé" + username);
            System.out.println("total utilisateurs"+ kines.size());
            saveKines();
        }
    }

    @Override
    public boolean checkPassword(String username, String password){
        System.out.println("verif : username :" + username + ", password : "+password);
        if (!credentials.containsKey(username)) {
            System.out.println("username non trouvé");
            return false;
        }
        String storedPassword = credentials.get(username);
        System.out.println("password stocké : " + storedPassword);
        System.out.println("password fourni : " + password);
        return storedPassword.equals(password);
    }

    @Override
    public String getPassword(String username){
        return credentials.get(username);
    }
}
