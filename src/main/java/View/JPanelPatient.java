
package View;


import Contrats.InterfaceObjectif;
import entity.Patient;
import entity.Objectif;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Contrats.InterfacePatientModel;
import Contrats.InterfaceObjectif;

public class JPanelPatient extends JPanel implements InterfacePatientModel {
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel mutuelleLabel;
    private JLabel maladieLabel;
    private JTable objectifsTable;
    private DefaultTableModel tableModel;
    private JButton boutonAjout;
    private JButton boutonSupprime;
    private JButton boutonValider;

    private InterfaceObjectif listenerAdd;
    private InterfaceObjectif listenerSuppr;

    private java.util.Set<Integer> lignesValidees = new java.util.HashSet<>();

    public JPanelPatient(){
        nomLabel = new JLabel();
        prenomLabel = new JLabel();
        mutuelleLabel = new JLabel();
        maladieLabel = new JLabel();

        // ✨ BOUTONS MODERNES AVEC COULEURS
        boutonAjout = creerBoutonModerne("Ajouter", new Color(76, 175, 80));
        boutonSupprime = creerBoutonModerne("Supprimer", new Color(244, 67, 54));
        boutonValider = creerBoutonModerne("Valider", new Color(33, 150, 243));

        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informations Patient"));

        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mutuelleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        maladieLabel.setFont(new Font("Arial", Font.BOLD, 14));

        infoPanel.add(nomLabel);
        infoPanel.add(prenomLabel);
        infoPanel.add(mutuelleLabel);
        infoPanel.add(maladieLabel);

        //tableau des objectifs
        String[] colonnes = {"Objectifs"};
        tableModel = new DefaultTableModel(colonnes, 0);
        objectifsTable = new JTable(tableModel);
        objectifsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        objectifsTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(objectifsTable);

        objectifsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (lignesValidees.contains(row)) {
                    c.setBackground(new Color(144, 238, 144));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                }
                return c;
            }
        });

        //panel pour les boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelBoutons.add(boutonAjout);
        panelBoutons.add(boutonValider);
        panelBoutons.add(boutonSupprime);

        // Ajouter les panels
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBoutons, BorderLayout.SOUTH);

        // Listeners des boutons
        boutonAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(listenerAdd != null){
                        listenerAdd.onAjouterObjectifClicked();
                    }
                }catch (Exception ex){
                    afficherMessageErreur("Erreur lors de l'ajout de l'objectif : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        boutonSupprime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(listenerSuppr != null){
                        listenerSuppr.onSupprObjectifClicked();
                    }
                }catch(Exception ex){
                    afficherMessageErreur("Erreur : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int indice = objectifsTable.getSelectedRow();
                    if(indice != -1){
                        lignesValidees.add(indice);
                        objectifsTable.repaint();
                    } else {
                        throw new Exception("Veuillez sélectionner un objectif à valider");
                    }
                }catch(Exception ex){
                    afficherMessageErreur("Erreur : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    // ✨ MÉTHODE UTILITAIRE - Créer des boutons modernes
    private JButton creerBoutonModerne(String texte, Color couleur) {
        JButton btn = new JButton(texte);

        // Style moderne
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(150, 40));
        btn.setFocusPainted(false);
        btn.setBackground(couleur);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Effet au survol
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

    public void setListenerAjouter(InterfaceObjectif listenerAdd){
        this.listenerAdd = listenerAdd;
    }

    public void setListenerSupprimer(InterfaceObjectif listenerSuppr) {
        this.listenerSuppr = listenerSuppr;
    }

    public int getSelectedObjectifIndex(){
        return objectifsTable.getSelectedRow();
    }

    @Override
    public void afficherPatient(Patient patient) {
        nomLabel.setText("Nom : " + patient.getName());
        prenomLabel.setText("Prénom : " + patient.getFirstname());
        mutuelleLabel.setText("Mutuelle : " + patient.getMutuelle());
        maladieLabel.setText("Maladie : " + patient.getMaladie().getName());

        tableModel.setRowCount(0);

        if (patient.getMaladie().getObjectif() != null) {
            for (Objectif obj : patient.getMaladie().getObjectif()) {
                Object[] row = {obj.getName()};
                tableModel.addRow(row);
            }
        }
    }

    @Override
    public void activerBoutton() {
        boutonAjout.setEnabled(true);
        boutonValider.setEnabled(true);
        boutonSupprime.setEnabled(true);
    }

    @Override
    public void desactiverBoutton() {
        boutonSupprime.setEnabled(false);
        boutonValider.setEnabled(false);
        boutonAjout.setEnabled(false);
    }

    @Override
    public void afficherMessageErreur(String message){
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}