package Contrats;

public interface InterfaceNouveauKine {
    public String getNom();
    public String getPrenom();
    public String getSpecialisation();
    public String getInami();
    public String getPhoto();

    //enregister le listener (contrôleur)
    public void setListener(InterfaceNouveauKineListener listener);

    public void fermer();
}
