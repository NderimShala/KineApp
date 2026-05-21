package Contrats;

import  entity.Kine;

public interface InterfaceAuthentification {

    public InterfaceUtilisateur authentification(String username, String password);

    public InterfaceUtilisateur getKineConnected();

    public void deconnecter();

    public boolean isConnected();

    public InterfaceUtilisateur addKine(String username, String password, String name, String firstname, String specialisation, String inami, String photo);

}
