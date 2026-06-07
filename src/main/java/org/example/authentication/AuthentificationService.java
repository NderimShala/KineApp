package org.example.authentication;

import Contrats.InterfaceAuthentification;
import Contrats.InterfaceKineDAO;
import Contrats.InterfaceUtilisateur;
import Properties.PropertiesAuthenticationService;
import java.util.Optional;

import entity.Kine;

public class AuthentificationService implements InterfaceAuthentification{
    //référence au DAO
    private InterfaceKineDAO kineDAO;
    private PropertiesAuthenticationService propertiesAuth;
    //stocker le kine actuellement connecté
    private InterfaceUtilisateur kineconnecte = null;


    public AuthentificationService(InterfaceKineDAO kineDAO){

        this.kineDAO = kineDAO;
        this.propertiesAuth = new PropertiesAuthenticationService();
    }

    @Override
    public InterfaceUtilisateur authentification(String username, String password){
        if(username == null || password == null || username.isBlank() ||password.isBlank()){
            return null;
        }
        //vérifier mdp via DAO
        if(!propertiesAuth.authentication(username, password)){
            return null;
        }
        //récup Kine via DAO
        Optional<InterfaceUtilisateur> kine = kineDAO.findByUsername(username);
        if(kine.isPresent()){
            this.kineconnecte = kine.get();
            return kineconnecte;
        }
        return null;
    }

    @Override
    public InterfaceUtilisateur getKineConnected(){
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
    public InterfaceUtilisateur addKine(String username, String password, String name, String firstname, String specialisation, String inami, String photo){
        if(propertiesAuth.usernameExists(username)){
            throw new RuntimeException("ce username existe déjà");
        }
        InterfaceUtilisateur newKine = new Kine(name, firstname, specialisation, inami, photo);
        //ajouter dans le fichier kines.bin
        kineDAO.save(username, password, newKine);
        //ajouter les credentials dans le fichier properties
        propertiesAuth.addCredentials(username, password);
        return newKine;
    }
}
