package Contrats;

public interface InterfaceLoginListener {
    public void onLoginClicked(String username, String password, boolean isNouveau) throws Exception;
    public void onCancelClicked();
}
