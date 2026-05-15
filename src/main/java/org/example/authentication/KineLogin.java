package org.example.authentication;

import java.util.HashMap;
import java.util.Map;

public class KineLogin extends AbstractLogin {

    private final Map<String, String> users;

    public KineLogin(){
        users = new HashMap<>(); //map vide au démarrage
    }

    @Override
    protected Map<String, String> getUsers() {return users;}
}
