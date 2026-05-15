package Contrats;

public interface InterfaceMainFrame {
    public void activerMenuItemPatient();
    public void desactiverMenuItemPatient();
    public void activerMenuItemLogout();
    public void desactiverMenuItemLogout();

    public void setLoginMenuListener(InterfaceMenuListener listener);
}

