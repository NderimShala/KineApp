package View;

import entity.Kine;
import entity.Pathologie;
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

public class JPanelRDV extends JPanel {
    private JTable rdvTable;
    private DefaultTableModel tableModel;
    private JButton boutonAjouter;
    private JButton boutonSupprimer;

    public JPanelRDV(){
        boutonAjouter = new JButton("Ajouter");
        boutonSupprimer = new JButton("Supprimer");

        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(new EmptyBorder(15,15,15,15));

        String[] colonnes = {"Patient", "Date"};
        Object[][] donneesTest = {
                {"Jean Dupont", "12/05/2026 10:00"},
                {"Marie Curie", "12/05/2026 11:30"},
                {"Lucas Martin", "13/05/2026 09:15"},
                {"Sophie Bernard", "13/05/2026 14:00"}
        };

        tableModel = new DefaultTableModel(donneesTest, colonnes){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        rdvTable = new JTable(tableModel);
        rdvTable.setRowHeight(25);
        rdvTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane((rdvTable));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Rendez-vous"));

        //pannel boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoutons.add(boutonAjouter);
        panelBoutons.add(boutonSupprimer);

        this.add(panelBoutons, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);

        /// ///////action listeners à déplacer dans le controlleur plus tard
        boutonSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = rdvTable.getSelectedRow();
                tableModel.removeRow(indice);
            }
        });
    }
}
