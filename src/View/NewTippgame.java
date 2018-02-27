package View;

import Database.DB;

import Domain.Match;
import Domain.Spieler;
import Domain.Tippspiel;
import Exceptions.InvalidTippException;
import Help.InitJFrame;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Panel zum Aufzeichnen eines Arbeitsgangs
 *
 * @author nrg, sht
 */
public final class NewTippgame extends javax.swing.JPanel {

    private DB db;
    private MainJFrame frame;
    private Tippspiel selectedTippspiel = null;
    private Match selectedMatch = null;
    private ArrayList<Tippspiel> tippspiele;
    private ArrayList<Match> matchliste;

    /**
     * Creates new form ArbeitsgangPanel
     *
     * @param frame
     */
    public NewTippgame(MainJFrame frame) {
        this.db = new DB();
        this.frame = frame;
        initStyle();
        initComponents();
        initLists();
        initOnClickListeners();
        enableMatches(this.selectedTippspiel != null);
    }

    /**
     * Creates new form ArbeitsgangPanel
     *
     * @param frame
     * @param t
     */
    public NewTippgame(MainJFrame frame, Tippspiel t) {
        this.db = new DB();
        this.frame = frame;
        initStyle();
        initComponents();
        initLists();
        initOnClickListeners();
        selectTippspiel(t);
        enableMatches(this.selectedTippspiel != null);
    }

    public NewTippgame(MainJFrame frame, Tippspiel t, Match m) {
        this.db = new DB();
        this.frame = frame;
        initStyle();
        initComponents();
        initLists();
        initOnClickListeners();
        selectTippspiel(t);
        selectMatch(m);
    }

    private void initStyle() {
        UIManager.put("TextArea.selectionBackground", new javax.swing.plaf.ColorUIResource(Color.RED));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        matchesComboBox = new javax.swing.JComboBox();
        tippgamePanel = new javax.swing.JLabel();
        deleteMatchBtn = new javax.swing.JButton();
        tippgamePanel1 = new javax.swing.JLabel();
        tippgameComboBox = new javax.swing.JComboBox();
        speichernTippspielbtn = new javax.swing.JButton();
        bearbeitenMatchBtn = new javax.swing.JButton();
        addMatchBtn = new javax.swing.JButton();
        addTippgameBtn = new javax.swing.JButton();
        bearbeitenTippgameBtn = new javax.swing.JButton();
        deleteTippgameBtn = new javax.swing.JButton();
        tippgamePanel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tipps = new javax.swing.JTextArea();
        tippgamePanel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ausgabe = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        auswertenBtn = new javax.swing.JButton();
        hinzufuegenBtn3 = new javax.swing.JButton();
        hinzufuegenBtn4 = new javax.swing.JButton();

        matchesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchesComboBoxActionPerformed(evt);
            }
        });

        tippgamePanel.setText("Tippgame");

        deleteMatchBtn.setText("Entfernen");
        deleteMatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMatchBtnActionPerformed(evt);
            }
        });

        tippgamePanel1.setText("Matches");

        speichernTippspielbtn.setText("Speichern");
        speichernTippspielbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speichernTippspielbtnActionPerformed(evt);
            }
        });

        bearbeitenMatchBtn.setText("Bearbeiten");
        bearbeitenMatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bearbeitenMatchBtnActionPerformed(evt);
            }
        });

        addMatchBtn.setText("Hinzufügen");
        addMatchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMatchBtnActionPerformed(evt);
            }
        });

        addTippgameBtn.setText("Hinzufügen");
        addTippgameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTippgameBtnActionPerformed(evt);
            }
        });

        bearbeitenTippgameBtn.setText("Bearbeiten");
        bearbeitenTippgameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bearbeitenTippgameBtnActionPerformed(evt);
            }
        });

        deleteTippgameBtn.setText("Entfernen");
        deleteTippgameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTippgameBtnActionPerformed(evt);
            }
        });

        tippgamePanel2.setText("Tipps");

        tipps.setColumns(20);
        tipps.setRows(5);
        jScrollPane1.setViewportView(tipps);

        tippgamePanel3.setText("Ausgabe");

        ausgabe.setColumns(20);
        ausgabe.setRows(5);
        jScrollPane2.setViewportView(ausgabe);

        auswertenBtn.setText("Auswerten");
        auswertenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auswertenBtnActionPerformed(evt);
            }
        });

        hinzufuegenBtn3.setText("Zeige Mitglieder die nicht teilgenommen haben");
        hinzufuegenBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hinzufuegenBtn3ActionPerformed(evt);
            }
        });

        hinzufuegenBtn4.setText("Zeige Platzierungen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tippgamePanel2)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane1)
                        .addGap(16, 16, 16)
                        .addComponent(tippgamePanel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tippgamePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tippgamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addTippgameBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bearbeitenTippgameBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteTippgameBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tippgameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addMatchBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bearbeitenMatchBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteMatchBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(matchesComboBox, 0, 495, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speichernTippspielbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(auswertenBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hinzufuegenBtn4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hinzufuegenBtn3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tippgamePanel)
                    .addComponent(tippgameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteTippgameBtn)
                    .addComponent(bearbeitenTippgameBtn)
                    .addComponent(addTippgameBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matchesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tippgamePanel1)
                    .addComponent(deleteMatchBtn)
                    .addComponent(bearbeitenMatchBtn)
                    .addComponent(addMatchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tippgamePanel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tippgamePanel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speichernTippspielbtn)
                    .addComponent(auswertenBtn)
                    .addComponent(hinzufuegenBtn3)
                    .addComponent(hinzufuegenBtn4))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteMatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMatchBtnActionPerformed
        if (selectedMatch != null) {
            int confirmation = JOptionPane.showConfirmDialog(null, "Soll das Match wirklich gelöscht werden?");
            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    new DB().removeMatch(selectedMatch.getId());
                    selectedMatch = null;
                    initMatchList(selectedTippspiel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_deleteMatchBtnActionPerformed

    private void bearbeitenMatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bearbeitenMatchBtnActionPerformed
        if (selectedMatch != null) {
            InitJFrame.showNewMatch(frame, selectedTippspiel, selectedMatch);
        }
    }//GEN-LAST:event_bearbeitenMatchBtnActionPerformed

    private void addMatchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMatchBtnActionPerformed
        InitJFrame.showNewMatch(frame, selectedTippspiel);
    }//GEN-LAST:event_addMatchBtnActionPerformed

    private void addTippgameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTippgameBtnActionPerformed
        try {
            Tippspiel t = Tippspiel.createTippspiel(frame);
            initTippspielList();
            selectTippspiel(t);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_addTippgameBtnActionPerformed

    private void bearbeitenTippgameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bearbeitenTippgameBtnActionPerformed
        if (selectedTippspiel != null) {
            InitJFrame.showNewTippgame(frame, selectedTippspiel);
        }
    }//GEN-LAST:event_bearbeitenTippgameBtnActionPerformed

    private void deleteTippgameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTippgameBtnActionPerformed
        if (selectedTippspiel != null) {
            int confirmation = JOptionPane.showConfirmDialog(null, "Soll das Tippgame wirklich gelöscht werden?");
            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    db.removeTippspiel(selectedTippspiel.getId());
                    selectedTippspiel = null;
                    initTippspielList();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_deleteTippgameBtnActionPerformed

    private void matchesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matchesComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matchesComboBoxActionPerformed

    private void speichernTippspielbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speichernTippspielbtnActionPerformed
        selectedTippspiel.setTipps(tipps.getText()).update();
    }//GEN-LAST:event_speichernTippspielbtnActionPerformed

    private void auswertenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_auswertenBtnActionPerformed
        ausgabe.setText("");
        ausgabe.setBackground(Color.WHITE);
        selectedTippspiel.setTipps(tipps.getText()).update();
        int counter = -1;
        String print = "";
        int anzahlMatches = 0;
        List<String> temp = new ArrayList<>();
        try {
            anzahlMatches = db.getAnzahlMatches(selectedTippspiel);
            List<String> lines = Arrays.asList(selectedTippspiel.getTipps().split("\n"));

            lines.stream().filter((x) -> (!x.isEmpty()
                    && isNaN(x)
                    && !x.contains("Verwalten")
                    && !x.contains("Gefällt mir")))
                    .forEach((x) -> {
                        temp.add(x);

                    });
            tipps.setText(String.join("\n", temp));
            lines = new ArrayList(temp);

            List<Spieler> spielerListe = new ArrayList<>();

            while (lines.size() > 0) {

                Spieler player = new Spieler(null, 0);
                player.setName(lines.get(0));
                lines.remove(0);
                String[] tipp = new String[anzahlMatches];
                for (int j = 0; j < tipp.length; j++) {
                    tipp[j] = lines.get(0);
                    lines.remove(0);

                }
                player.setTipp(tipp, this.matchliste);
                counter += anzahlMatches + 1;
                spielerListe.add(player);
                print += player.getName() + "\n";
                print += player.getPunkte() + "\n";
                print += Arrays.toString(tipp) + "\n";
                print += "--------------------------------------------------------" + "\n";
            }

            Collections.sort(spielerListe, new Comparator<Spieler>() {

                @Override
                public int compare(Spieler p1, Spieler p2) {
                    if (p1.getPunkte() > p2.getPunkte()) {
                        return -1;
                    } else if (p1.getPunkte() < p2.getPunkte()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            for (Spieler player : spielerListe) {
                print += player.toString() + "\n";
            }
            print(print);

            db.saveTippgameTeilnehmer(selectedTippspiel.getId(), spielerListe);

        } catch (SQLException | InvalidTippException e) {
//            System.out.println(temp.size());
//            System.out.println(temp);
//            String text = String.join("\n", temp);
            int startindex = 0;
            for (int i = 0; i < counter - anzahlMatches; i++) {
                startindex += temp.get(i).length() + 1;
            }
            int endIndex = startindex + temp.get(counter - anzahlMatches).length();
//            int startIndex = tipps.getText().indexOf(temp.get(counter));
//            int endIndex = startindex + temp.get(counter).length();
//            tipps.setCaretPosition(tipps.getDocument().getDefaultRootElement().getElement(counter - anzahlMatches).getStartOffset());
            printError(e.getMessage());
            tipps.requestFocus();
            tipps.select(startindex, endIndex);
        }
    }//GEN-LAST:event_auswertenBtnActionPerformed

    private void hinzufuegenBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hinzufuegenBtn3ActionPerformed
        ausgabe.setText("");
        try {
            ArrayList<Spieler> nichtGetippt = db.getSpielerDieNichtGetipptHaben(selectedTippspiel.getId());

            int counter = 0;
            String auswertung = "";
            for (Spieler player : nichtGetippt) {
                auswertung += player.getName() + "\n";
                if (++counter % 50 == 0) {
                    auswertung += "\n";
                }
            }

            print(auswertung);

        } catch (SQLException ex) {
            Logger.getLogger(NewTippgame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_hinzufuegenBtn3ActionPerformed

    private void print(Object text) {
        ausgabe.setText(ausgabe.getText() + text + "\n");
    }

    public void printError(Object text) {
        ausgabe.setText(ausgabe.getText() + text + "\n");
        ausgabe.setBackground(Color.red);
    }

    private void initLists() {
        initTippspielList();
    }

    private void initTippspielList() {
        try {
            this.tippspiele = db.getTippspielList();
            tippgameComboBox.removeAllItems();
            tippspiele.forEach((t) -> {
                tippgameComboBox.addItem(t);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initMatchList(Tippspiel t) {
        try {
            this.matchliste = db.getMatchList(t.getId());
            matchesComboBox.removeAllItems();
            matchliste.forEach((m) -> {
                matchesComboBox.addItem(m);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void selectTippspiel(Tippspiel t) {
        if (t != null) {
            this.selectedTippspiel = t;
            int size = tippgameComboBox.getModel().getSize();
            for (int i = 0; i < size; i++) {
                String element = tippgameComboBox.getModel().getElementAt(i).toString();
                if (element.equals(t.toString())) {
                    tippgameComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void selectMatch(Match m) {
        if (m != null) {
            this.selectedMatch = m;
            int size = matchesComboBox.getModel().getSize();
            for (int i = 0; i < size; i++) {
                String element = matchesComboBox.getModel().getElementAt(i).toString();
                if (element.equals(m.toString())) {
                    matchesComboBox.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            matchesComboBox.setSelectedIndex(0);
        }
    }

    private void enableMatches(boolean enable) {
        matchesComboBox.setEnabled(enable);
        addMatchBtn.setEnabled(enable);
        bearbeitenMatchBtn.setEnabled(enable);
        deleteMatchBtn.setEnabled(enable);
    }

    private void initOnClickListeners() {

        tippgameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    //show corresponding taetigkeiten for projekt
                    enableMatches(true);
                    selectedTippspiel = (Tippspiel) e.getItem();
                    initMatchList(selectedTippspiel);
                    tipps.setText(selectedTippspiel.getTipps());

                }
            }
        });

        matchesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedMatch = (Match) e.getItem();

                }
            }
        });

    }

    private boolean isNaN(String s) {
        return !(s != null && s.matches("[-+]?\\d*\\.?\\d+"));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMatchBtn;
    private javax.swing.JButton addTippgameBtn;
    private javax.swing.JTextArea ausgabe;
    private javax.swing.JButton auswertenBtn;
    private javax.swing.JButton bearbeitenMatchBtn;
    private javax.swing.JButton bearbeitenTippgameBtn;
    private javax.swing.JButton deleteMatchBtn;
    private javax.swing.JButton deleteTippgameBtn;
    private javax.swing.JButton hinzufuegenBtn3;
    private javax.swing.JButton hinzufuegenBtn4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox matchesComboBox;
    private javax.swing.JButton speichernTippspielbtn;
    private javax.swing.JComboBox tippgameComboBox;
    private javax.swing.JLabel tippgamePanel;
    private javax.swing.JLabel tippgamePanel1;
    private javax.swing.JLabel tippgamePanel2;
    private javax.swing.JLabel tippgamePanel3;
    private javax.swing.JTextArea tipps;
    // End of variables declaration//GEN-END:variables

}
