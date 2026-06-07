package Properties;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractAuthenticationService {
    protected Map<String, String> credentials = new HashMap<>();
    private static final String PROPERTIES_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator + "users.properties";

    //methode template pour charger les credentials depuis un fichier de propriétés
    public boolean authentication(String username, String password){
        loadCredentials();
        return verifyCredentials(username, password);
    }

    protected void loadCredentials(){
        Properties props = new Properties();
        try(FileReader reader = new FileReader(PROPERTIES_PATH)){
            props.load(reader);
            credentials.putAll((Map) props);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des credentials : " + e.getMessage());
        }
    }
    protected abstract boolean verifyCredentials(String username, String password);
}

