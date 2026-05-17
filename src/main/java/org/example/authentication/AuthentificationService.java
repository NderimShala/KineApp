package org.example.authentication;

import Contrats.InterfaceAuthentification;
import Contrats.InterfaceKineDAO;
import entity.Kine;
import java.util.Optional;

public class AuthentificationService implements InterfaceAuthentification{
    //référence au DAO
    private InterfaceKineDAO kineDAO;

    //stocker le kine actuellement connecté
    private Kine kineconnecte = null;

    public AuthentificationService(){
        this.kineDAO = kineDAO;
    }

    @Override
    public Kine authentification(String username, String password){
        if(username == null || password == null || username.isBlank() ||password.isBlank()){
            return null;
        }
        //vérifier mdp via DAO
        if(!kineDAO.checkPassword(username, password)){
            return null;
        }
        //récup Kine via DAO
        Optional<Kine> kine = kineDAO.findByUsername(username);
        if(kine.isPresent()){
            this.kineconnecte = kine.get();
            return kineconnecte;
        }
        return null;
    }

    @Override
    public Kine getKineConnected(){
        return kineconnecte;
    }

    @Override
    public void deconnecter(){
        kineconnecte = null;
    }

    @Override
    public boolean isConnected(){
        return kineconnecte != null;
    }

    @Override
    public void addKine(String username, String password, String name, String firstname, String specialisation, String inami, String photo){
        Kine newKine = new Kine(name, firstname, specialisation, inami, photo);
        kineDAO.save(username, password, newKine);
    }
}
