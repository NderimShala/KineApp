
package Contrats;

public interface InterfaceLogin {
    public String getUsername();
    public String getPassword();
    public boolean isNouveauChecked();

    public void afficherMessageErreurLogin(String message);

    public void fermer();

    public void setLoginListener(InterfaceLoginListener listener);
}

