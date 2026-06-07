package Contrats;

public interface InterfaceNouveauPatient {
    public String getNom();
    public String getPrenom();
    public String getMutuelle();
    public String getNomPatho();

    public void afficherMessageErreurInfoManquante(String message);
    public void afficherMessageSuccesPatient();

    public void setListener(InterfaceNouveauPatientListener listener);

    public void fermer();
}
