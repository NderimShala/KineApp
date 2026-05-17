package Properties;

import Contrats.InterfaceKineDAO;
import entity.Kine;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KineDAO implements InterfaceKineDAO {

    private Map<String, String> credentials; //stocker username -> password
    private Map<String, Kine> kines; //stocker les différents kines : username -> kine

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
    public Optional<Kine> findByUsername(String username){
        return Optional.ofNullable(kines.get(username));
    }
    @Override
    public boolean existsByUsername(String username){
        return kines.containsKey(username);
    }
    @Override
    public void save(String username, String password, Kine kine){
        if(username != null && !username.isBlank() && password != null && !password.isBlank() && kine != null){
            credentials.put(username, password);
            kines.put(username, kine);
        }
    }

    @Override
    public boolean checkPassword(String password, String username){
        if (!credentials.containsKey(username)) {
            return false;
        }
        return credentials.get(username).equals(password);
    }

    @Override
    public String getPassword(String username){
        return credentials.get(username);
    }
}
