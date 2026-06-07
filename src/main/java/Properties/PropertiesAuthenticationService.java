package Properties;

import java.io.*;
import java.util.Properties;

public class PropertiesAuthenticationService extends AbstractAuthenticationService{
    private static final String PROPERTIES_PATH =
            System.getProperty("user.dir") + File.separator + "src" + File.separator +
                    "main" + File.separator + "resources" + File.separator + "users.properties";

    @Override
    protected boolean verifyCredentials(String username, String password) {
        String storedPassword = credentials.get(username);
        if(storedPassword != null && storedPassword.equals(password)) return true;
        else return false;
    }

    public void addCredentials(String username, String password){
        Properties props = new Properties();
        try(FileReader reader = new FileReader(PROPERTIES_PATH)){
            props.load(reader);
        }catch(IOException e) {System.err.println("Erreur lecture properties : "+ e.getMessage());}
        props.setProperty(username, password);
        try(FileWriter writer = new FileWriter(PROPERTIES_PATH)){
            props.store(writer, "Credentials KineApp");
        }catch(IOException e){
            System.err.println("Erreur écriture properties : " + e.getMessage());
        }
        credentials.put(username, password);
    }

    public boolean usernameExists(String username){
        loadCredentials();
        return credentials.containsKey(username);
    }

}
