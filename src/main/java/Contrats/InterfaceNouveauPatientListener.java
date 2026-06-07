package Contrats;

public interface InterfaceNouveauPatientListener {
    public void onNouveauPatientClicked(String nom, String prenom, String mutuelle, String nomPatho) throws Exception;

    public void onCancelPatientClicked();


}
