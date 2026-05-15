package org.example.authentication;
import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends AbstractLogin {

    private final Map<String, String> users;

    public PatientLogin(){
        users = new HashMap<>();
    }

    @Override
    protected Map<String, String> getUsers(){ return users;}
}
