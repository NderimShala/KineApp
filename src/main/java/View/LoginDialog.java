
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Contrats.InterfaceLogin;
import Contrats.InterfaceLoginListener;

public class LoginDialog extends JDialog implements InterfaceLogin {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox isNouveauCheckBox;
    private JButton btnLogin;
    private JButton btnCancel;
    private InterfaceLoginListener loginListener;

    public LoginDialog() {
        super();
        setTitle("Connexion KineAPP");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(usernameLabel, gbc);

        // Field Username
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(usernameField, gbc);

        // Label Password
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        // Field Password
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(passwordField, gbc);

        // Checkbox Nouveau
        isNouveauCheckBox = new JCheckBox("Créer nouveau compte");
        isNouveauCheckBox.setFont(new Font("Arial", Font.PLAIN, 11));
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(isNouveauCheckBox, gbc);

        // Panel Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnLogin = creerBoutonModerne("Connexion", new Color(33, 150, 243));
        btnCancel = creerBoutonModerne("Annuler", new Color(158, 158, 158));

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        // Ajouter le panel des boutons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        this.setContentPane(mainPanel);

        // Listeners
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(loginListener != null) {
                        loginListener.onLoginClicked(getUsername(), getPassword(), isNouveauChecked());
                    }
                } catch (Exception ex) {
                    afficherMessageErreurLogin("Erreur : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginListener != null) {
                    loginListener.onCancelClicked();
                }
                fermer();
            }
        });
    }


    private JButton creerBoutonModerne(String texte, Color couleur) {
        JButton btn = new JButton(texte);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(130, 35));
        btn.setFocusPainted(false);
        btn.setBackground(couleur);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(couleur.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(couleur);
            }
        });

        return btn;
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    @Override
    public boolean isNouveauChecked() {
        return isNouveauCheckBox.isSelected();
    }

    @Override
    public void setLoginListener(InterfaceLoginListener listener) {
        this.loginListener = listener;
    }

    @Override
    public void fermer() {
        this.setVisible(false);
        usernameField.setText("");
        passwordField.setText("");
        isNouveauCheckBox.setSelected(false);
    }

    @Override
    public void afficherMessageErreurLogin(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
    }
}