package View;

import Database.DB;
import Domain.Spieler;
import Help.TableCellListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableCell;

/**
 * Zur Ansicht und Bearbeitung der Stationen
 *
 * @author sht
 */
public class UebersichtTeilnehmer extends javax.swing.JPanel {

    List<Spieler> list;
    private DB db;
    private MainJFrame frame;

    /**
     * Creates new form StationAnsichtPanel
     *
     * @param frame
     */
    public UebersichtTeilnehmer(MainJFrame frame) {
        this.frame = frame;
        initComponents();
        changeStyle();
        try {
            this.db = new DB();
            this.list = this.db.getSpieler();
            fuelleTabelle();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Fehler beim Initialisieren von Spielern.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        entfernenBtn = new javax.swing.JButton();
        bearbeitenBtn = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        entfernenBtn.setText("Entfernen");
        entfernenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entfernenBtnActionPerformed(evt);
            }
        });

        bearbeitenBtn.setText("Bearbeiten");
        bearbeitenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bearbeitenBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(entfernenBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bearbeitenBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entfernenBtn)
                    .addComponent(bearbeitenBtn))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Entfernt die ausgewaehlte Station
     *
     * @param evt
     */
    private void entfernenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entfernenBtnActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(null, "Soll der Spieler wirklich gelöscht werden?");
        if (confirmation == JOptionPane.YES_OPTION) {
            int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            if (index >= 0) {
                Spieler s = list.get(index);
                try {
                    db.removeSpieler(s.getId());
                    list = this.db.getSpieler();
                    fuelleTabelle();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Löschen von Spieler.");
                }
            }
        }
    }//GEN-LAST:event_entfernenBtnActionPerformed

    /**
     * Ruft die Form zum bearbeiten der ausgewaehlten Station aus
     *
     * @param evt
     */
    private void bearbeitenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bearbeitenBtnActionPerformed
        try {
            int index = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
            if (index >= 0) {
                Spieler s = list.get(index);
                s.initUpdate();
                list = this.db.getSpieler();
                fuelleTabelle();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Fehler beim Updaten von Spieler.");
        }
    }//GEN-LAST:event_bearbeitenBtnActionPerformed

    /**
     * Fuellt die Tabelle mit den Informationen der Stationen
     *
     * @throws IOException
     */
    private void fuelleTabelle() {
        String[] spalten = {"id", "Name", "Punkte", "Punkte dieses Jahr", "Siege", "Siege dieses Jahr"};
        Object[][] input = new Object[list.size()][spalten.length];
        int i = 0;
        for (Spieler s : list) {
            input[i][0] = s.getId();
            input[i][1] = s.getName();
            input[i][2] = s.getPunkte();
            input[i][3] = s.getPunkte_current_year();
            input[i][4] = s.getSiege();
            input[i][5] = s.getSiege_current_year();
            i++;
        }

        Comparator comparatorLong = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                long l1 = (long) o1;
                long l2 = (long) o2;
                return (int) (l1 - l2);
            }
        };
        Comparator comparatorInt = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int l1 = (int) o1;
                int l2 = (int) o2;
                return (l1 - l2);
            }
        };

        TableModel myModel = new javax.swing.table.DefaultTableModel(
                input,
                spalten
        );
        TableRowSorter sorter = new TableRowSorter(myModel);
        sorter.setComparator(0, comparatorLong);
        sorter.setComparator(2, comparatorInt);
        sorter.setComparator(3, comparatorInt);
        sorter.setComparator(4, comparatorInt);
        sorter.setComparator(5, comparatorInt);
        jTable1.setModel(myModel);
        jTable1.setRowSorter(sorter);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                System.out.println("Row   : " + tcl.getRow());
                System.out.println("Column: " + tcl.getColumn());
                System.out.println("Old   : " + tcl.getOldValue());
                System.out.println("New   : " + tcl.getNewValue());
                Spieler s = list.get(tcl.getRow());
                try {
                    switch (tcl.getColumn()) {
                        case 0:
                            myModel.setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());
                            JOptionPane.showMessageDialog(frame, "Ändern der ID nicht möglich!");
                            break;
                        case 1:
                            s.setName((String) tcl.getNewValue());
                            break;
                        case 2:
                            s.setPunkte(Integer.parseInt((String) tcl.getNewValue()));
                            break;
                        case 3:
                            s.setPunkte_current_year(Integer.parseInt((String) tcl.getNewValue()));
                            break;
                        case 4:
                            s.setSiege(Integer.parseInt((String) tcl.getNewValue()));
                            break;
                        case 5:
                            s.setSiege_current_year(Integer.parseInt((String) tcl.getNewValue()));
                            break;
                        default:
                            JOptionPane.showMessageDialog(frame, "Fehler beim Ändern von Spieler.");
                            return;
                    }
                    s.update();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Fehler beim Ändern von Spieler.");
                } catch (java.lang.NumberFormatException ex) {
                    myModel.setValueAt(tcl.getOldValue(), tcl.getRow(), tcl.getColumn());
                    JOptionPane.showMessageDialog(frame, "Ungültiger Eingabewert.");
                }
            }
        };

        TableCellListener tcl = new TableCellListener(jTable1, action);

    }

    /**
     * Veraendert den Style der Tabellen
     */
    public final void changeStyle() {
        JTableHeader header = jTable1.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        entfernenBtn.setBackground(Color.BLACK);
        entfernenBtn.setForeground(Color.WHITE);
        bearbeitenBtn.setBackground(Color.BLACK);
        bearbeitenBtn.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        jTable1.setRowHeight(40);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bearbeitenBtn;
    private javax.swing.JButton entfernenBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
