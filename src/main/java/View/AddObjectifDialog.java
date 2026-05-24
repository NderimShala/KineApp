package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddObjectifDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nomObjectif;
    private JLabel objectif;

    public AddObjectifDialog() {
        setTitle("Nouvel Objectif");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModal(true);

        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        nomObjectif = new JTextField(15);
        objectif = new JLabel("Nom");
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(objectif, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(nomObjectif, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPane.add(buttonPanel, gbc);


        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ///  à implémenter
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        AddObjectifDialog dialog = new AddObjectifDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
