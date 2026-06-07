package Contrats;

public interface InterfaceNouveauKine {
    public String getNom();
    public String getPrenom();
    public String getSpecialisation();
    public String getInami();
    public String getPhoto();

    public void afficherMessageSuccesKine();
    public void afficherMessageErreurKine(String message);

    //enregister le listener (contrôleur)
    public void setListener(InterfaceNouveauKineListener listener);

    public void fermer();
}
