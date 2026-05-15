package org.example.authentication;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLogin {

    public final boolean login(String username, String password) {
        if (!inputValid(username, password)) {
            return false;
        }
        if(!userExists(username)){
            return false;
        }
        return isPasswordCorrect(username, password);
    }

    protected abstract Map<String, String> getUsers();

    protected boolean inputValid(String username, String password){
        if(username != null && password != null && !username.isBlank() && !password.isBlank())
            return true;
        else return false;
    }

    protected boolean userExists(String username){
        if(getUsers().containsKey(username))
            return true;
        else return false;
    }

    protected boolean isPasswordCorrect(String username, String password){
        if(getUsers().get(username).equals(password))
            return true;
        else return false;
    }

    public void addUser(String username, String password){
        if(inputValid(username, password)){
            getUsers().put(username, password);
        }
    }

    public void suppUser(String username){
        getUsers().remove(username);
    }
}
