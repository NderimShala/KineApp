package Contrats;

public interface InterfaceNouveauKineListener {

    public void onNouveauKineClicked(String username, String password, String nom, String prenom, String specialisation, String inami, String photo) throws Exception;

    public void onCancelClicked();

}
