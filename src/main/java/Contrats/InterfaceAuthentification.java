package Contrats;

import  entity.Kine;

public interface InterfaceAuthentification {

    public Kine authentification(String username, String password);

    public Kine getKineConnected();

    public void deconnecter();

    public boolean isConnected();

    public void addKine(String username, String password, String name, String firstname, String specialisation, String inami, String photo);

}
