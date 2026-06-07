package View;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.*;
import java.awt.*;
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

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            // Personnalisation des boutons
            UIManager.put("Button.arc", 8); // Coins arrondis
            UIManager.put("Button.focusWidth", 2);
            UIManager.put("Component.focusWidth", 2);
            UIManager.put("Button.foreground", new Color(255, 255, 255));
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Erreur FlatLaf: " + e.getMessage());
        }

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

        itemAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginMenuListener != null){
                    loginMenuListener.onAjouterPatientMenuClicked();
                }
            }
        });
        menuPatient.add(itemAjout);

        itemSuppr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginMenuListener != null){
                    loginMenuListener.onSupprPatientMenuClicked();
                }
            }
        });
        menuPatient.add(itemSuppr);

        itemOuvrir.addActionListener(new  ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(loginMenuListener != null){
                    loginMenuListener.onOuvrirPatientMenuClicked();
                }
            }
        });
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