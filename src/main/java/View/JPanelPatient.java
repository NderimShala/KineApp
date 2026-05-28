package View;

import entity.Patient;
import entity.Objectif;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import Contrats.InterfacePatientModel;
import Contrats.InterfaceAddObjectifListener;
import Contrats.InterfaceSupprObjectifListener;

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

    private InterfaceAddObjectifListener listenerAdd;
    private InterfaceSupprObjectifListener listenerSuppr;

    private java.util.Set<Integer> lignesValidees = new java.util.HashSet<>(); //variable pour stocker les lignes validées

    public JPanelPatient(){
        nomLabel = new JLabel();
        prenomLabel = new JLabel();
        mutuelleLabel = new JLabel();
        maladieLabel = new JLabel();
        boutonAjout = new JButton("Ajouter");
        boutonSupprime = new JButton("Supprimer");
        boutonValider = new JButton("Valider");

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

        // ... après la création de objectifsTable ...
        objectifsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Si la ligne est dans notre liste de lignes validées
                if (lignesValidees.contains(row)) {
                    c.setBackground(new Color(144, 238, 144)); // Vert clair
                    c.setForeground(Color.BLACK);
                } else {
                    // Couleur par défaut (blanc ou bleu si sélectionné)
                    c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                    c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
                }
                return c;
            }
        });

        //panel pour les boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutons.add(boutonAjout);
        panelBoutons.add(boutonValider);
        panelBoutons.add(boutonSupprime);


        // Ajouter les panels
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBoutons,BorderLayout.SOUTH);

        //les listeners a mettre dans le controlllllleeeeeeeeeurrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr///////////////////////////////////////////////////////
        ////////////////////////////////////
        ////////////////////////////////////
        ////////////////////////////////////
        ////////////////////////////////////
        boutonAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listenerAdd != null){
                    listenerAdd.onAjouterObjectifClicked();
                }

            }
        });
        boutonSupprime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listenerSuppr != null){
                    listenerSuppr.onSupprObjectifClicked();
                }
            }
        });
        boutonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = objectifsTable.getSelectedRow();
                if(indice != -1){
                    lignesValidees.add(indice);
                    objectifsTable.repaint();
                }
                else JOptionPane.showMessageDialog(null, "Veuillez sélectionner un objectif à valider.");
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    }

    public void setListenerAjouter(InterfaceAddObjectifListener listenerAdd){
        this.listenerAdd = listenerAdd;
    }
    public void setListenerSupprimer(InterfaceSupprObjectifListener listenerSuppr) {this.listenerSuppr = listenerSuppr; }

    public int getSelectedObjectifIndex(){
        return objectifsTable.getSelectedRow();
    }

    @Override
    public void afficherPatient(Patient patient) {
        // Afficher les infos du patient
        nomLabel.setText("Nom : " + patient.getName());
        prenomLabel.setText("Prénom : " + patient.getFirstname());
        mutuelleLabel.setText("Mutuelle : " + patient.getMutuelle());
        maladieLabel.setText("Maladie : " + patient.getMaladie().getName());

        // Remplir le tableau des objectifs
        tableModel.setRowCount(0);  // Vider le tableau

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
}
