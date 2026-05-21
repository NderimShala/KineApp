package View;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contrats.InterfaceMainFrame;
import Contrats.InterfaceMenuListener;

import Controller.appController;

public class MainFrame extends JFrame implements InterfaceMainFrame{
    private JMenuItem itemLogin, itemLogout, itemAjout, itemSuppr, itemOuvrir;
    private JMenu menuPatient;

    private InterfaceMenuListener loginMenuListener;
    public MainFrame() {

        super("KineAPP");
        SwingUtilities.invokeLater(() -> {
            try{
                UIManager.setLookAndFeel(new FlatLightLaf());

            }catch (Exception e){
                System.err.println("flatlaf n'a pas pu etre chargé");
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);


        JPanelKine kinePanel = new JPanelKine();
        JPanelPatient patientPanel = new JPanelPatient();
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JPanelRDV rdvPanel = new JPanelRDV();
        LoginDialog loginDialog = new LoginDialog();

        //creation de la barre de menu
        JMenu menuLogin = new JMenu("Connexion");
        itemLogin = new JMenuItem("login");
        itemLogout = new JMenuItem("logout");
        //ajout actionListeners aux item menu
        itemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginMenuListener != null){
                    loginMenuListener.onLoginMenuClicked();
                }
            }
        });
        itemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginMenuListener != null){
                    loginMenuListener.onLogoutMenuClicked();
                }
            }
        });

        menuLogin.add(itemLogin);
        menuLogin.add(itemLogout);
        menu.add(menuLogin);
        menuPatient = new JMenu("Patients");
        itemAjout = new JMenuItem("ajouter");
        itemSuppr = new JMenuItem("Supprimer");
        itemOuvrir = new JMenuItem("Ouvrir");
        menuPatient.add(itemAjout);
        menuPatient.add(itemSuppr);
        menuPatient.add(itemOuvrir);
        menu.add(menuPatient);

        //créer controller
        appController controller = new appController(kinePanel, patientPanel, this, loginDialog);

        JSplitPane splitPaneGauche = new JSplitPane(JSplitPane.VERTICAL_SPLIT, kinePanel, rdvPanel);
        splitPaneGauche.setDividerLocation(150);
        splitPaneGauche.setContinuousLayout(true);

        JSplitPane splitPanePrincipal = new JSplitPane((JSplitPane.HORIZONTAL_SPLIT), splitPaneGauche, patientPanel);
        splitPanePrincipal.setDividerLocation(350); // mettre la barre de séparation au centre
        splitPanePrincipal.setContinuousLayout(true);


        this.setContentPane(splitPanePrincipal);


        kinePanel.revalidate();
        kinePanel.repaint();
        setVisible(true);
    }

    @Override
    public void activerMenuItemPatient(){
        menuPatient.setEnabled(true);
    }
    @Override
    public void desactiverMenuItemPatient(){
        menuPatient.setEnabled(false);
    }
    @Override
    public void activerMenuItemLogout(){
        itemLogout.setEnabled(true);
    }
    @Override
    public void desactiverMenuItemLogout(){
        itemLogout.setEnabled(false);
    }
    @Override
    public void activerMenuItemLogin(){itemLogin.setEnabled(true);}
    @Override
    public void desactiverMenuItemLogin(){itemLogin.setEnabled(false);}

    @Override
    public void setLoginMenuListener(InterfaceMenuListener listener){
        this.loginMenuListener = listener;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}