package Properties;

import Contrats.InterfaceKineDAO;
import Contrats.InterfaceUtilisateur;
import entity.Kine;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KineDAO implements InterfaceKineDAO {

    private Map<String, String> credentials; //stocker username -> password
    private Map<String, InterfaceUtilisateur> kines; //stocker les différents kines : username -> kine

    public KineDAO(){
        this.credentials = new HashMap<>();
        this.kines = new HashMap<>();

        initializeDefaultUsers();
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
