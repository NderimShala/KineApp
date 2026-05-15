package View;

import Contrats.InterfaceLogin;
import Contrats.InterfaceLoginListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ✅ LoginDialog implémente InterfaceLogin
public class LoginDialog extends JDialog implements InterfaceLogin {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox checkBoxNouveau;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JLabel UsernameField;
    private JLabel PassField;

    // ✅ Listener pour communiquer avec le contrôleur
    private InterfaceLoginListener loginListener;

    public LoginDialog() {
        setTitle("LOGIN");
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        // ✅ CRÉER LE PANEL PRINCIPAL
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ✅ CRÉER LES COMPOSANTS
        UsernameField = new JLabel("Username:");
        textField1 = new JTextField(15);

        PassField = new JLabel("Password:");
        passwordField1 = new JPasswordField(15);

        checkBoxNouveau = new JCheckBox("Nouveau");

        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        // ✅ AJOUTER LE LABEL USERNAME
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(UsernameField, gbc);

        // ✅ AJOUTER LE TEXTFIELD USERNAME
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(textField1, gbc);

        // ✅ AJOUTER LE LABEL PASSWORD
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(PassField, gbc);

        // ✅ AJOUTER LE PASSWORDFIELD
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(passwordField1, gbc);

        // ✅ AJOUTER LE CHECKBOX
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(checkBoxNouveau, gbc);

        // ✅ AJOUTER LES BOUTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPane.add(buttonPanel, gbc);

        // ✅ DÉFINIR LE CONTENTPANE
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        pack();

        // ✅ AJOUTER LES ACTIONLISTENERS
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // Fermer si on clique le X
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Fermer si on appuie sur ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    // ✅ Implémenter les méthodes de InterfaceLogin

    @Override
    public String getUsername(){
        // ✅ Retourner le texte du champ username
        return textField1.getText();
    }

    @Override
    public String getPassword() {
        // ✅ Retourner le mot de passe
        return new String(passwordField1.getPassword());
    }

    @Override
    public boolean isNouveauChecked() {
        // ✅ Retourner si le checkbox est coché
        return checkBoxNouveau.isSelected();
    }

    @Override
    public void fermer() {
        // ✅ Fermer la dialog
        dispose();
    }

    @Override
    public void setLoginListener(InterfaceLoginListener listener) {
        // ✅ Enregistrer le listener (le contrôleur)
        this.loginListener = listener;
    }

    // ✅ Méthode privée : appelée quand l'utilisateur clique OK
    private void onOK() {
        // ✅ Si un listener (contrôleur) est enregistré
        if (loginListener != null){
            // Appeler la méthode métier du contrôleur
            loginListener.onLoginClicked(
                    getUsername(),
                    getPassword(),
                    isNouveauChecked()
            );
        }
    }

    // ✅ Méthode privée : appelée quand l'utilisateur clique Cancel
    private void onCancel() {
        // ✅ Si un listener (contrôleur) est enregistré
        if (loginListener != null){
            loginListener.onCancelClicked();
        }
    }

    public static void main(String[] args) {
        LoginDialog dialog = new LoginDialog();
        dialog.setVisible(true);
    }
}