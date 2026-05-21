package org.example.authentication;

import Contrats.InterfaceAuthentification;
import Contrats.InterfaceKineDAO;
import Contrats.InterfaceUtilisateur;
import java.util.Optional;

import entity.Kine;

public class AuthentificationService implements InterfaceAuthentification{
    //référence au DAO
    private InterfaceKineDAO kineDAO;

    //stocker le kine actuellement connecté
    private InterfaceUtilisateur kineconnecte = null;

    public AuthentificationService(InterfaceKineDAO kineDAO){
        this.kineDAO = kineDAO;
    }

    @Override
    public InterfaceUtilisateur authentification(String username, String password){
        if(username == null || password == null || username.isBlank() ||password.isBlank()){
            return null;
        }
        //vérifier mdp via DAO
        if(!kineDAO.checkPassword(username, password)){
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
        InterfaceUtilisateur newKine = new Kine(name, firstname, specialisation, inami, photo);
        kineDAO.save(username, password, newKine);
        return newKine;
    }
}
