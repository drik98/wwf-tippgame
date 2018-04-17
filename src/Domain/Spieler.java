package Domain;

import Database.DB;
import Exceptions.InvalidTippException;
import View.AddTippspiel;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Created by pc2 on 11.09.2016.
 */
public class Spieler {

    private Long id;
    private long facebookid;
    private String name;
    private int punkte_current_year;
    private int siege_current_year;
    private int siege;
    private int punkte;

    public Long getId() {
        return id;
    }

    public long getFacebookid() {
        return facebookid;
    }

    public void setFacebookid(long facebookid) {
        this.facebookid = facebookid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPunkte_current_year() {
        return punkte_current_year;
    }

    public void setPunkte_current_year(int punkte_current_year) {
        this.punkte_current_year = punkte_current_year;
    }

    public int getSiege_current_year() {
        return siege_current_year;
    }

    public void setSiege_current_year(int siege_current_year) {
        this.siege_current_year = siege_current_year;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }

    public Spieler() {
        this.punkte = 0;
    }

    public Spieler(String name, int punkte) {
        this.name = name;
        this.punkte = punkte;
    }

    public Spieler(Long id, Long facebookid, String name, int punkte, int siege, int punkte_current_year, int siege_current_year) {
        this.id = id;
        this.facebookid = facebookid;
        this.name = name;
        this.punkte_current_year = punkte_current_year;
        this.siege_current_year = siege_current_year;
        this.siege = siege;
        this.punkte = punkte;
    }

    public static int getAusgang(String x) {
        int Ausgang = 0;
        if (x.contains("PIN")) {
            Ausgang = 1;
        } else if (x.contains("SUBMISSION")) {
            Ausgang = 2;
        } else if (x.contains("COUNT")) {
            Ausgang = 3;
        } else if (x.contains("KO")) {
            Ausgang = 4;
        } else if (x.contains("DQ")) {
            Ausgang = 5;
        } else if (x.contains("CONTEST")) {
            Ausgang = 6;
        } else if (x.contains("DRAW")) {
            Ausgang = 7;
        }
        return Ausgang;
    }

    public Spieler setName(String name) {
        this.name = name;
        return this;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public void addPunkte(int punkte) {
        this.punkte += punkte;
    }

    public void setTipp(String[] tipps, ArrayList<Match> matchliste) throws InvalidTippException {

        if (!tipps[0].contains(this.getName())) {
            throw new InvalidTippException("Fehler in der Formatierung");
        }

        for (int i = 0; i < tipps.length; i++) {
            tipps[i] = tipps[i].toUpperCase();
        }

        for (int i = 0; i < tipps.length; i++) {
            Match match = matchliste.get(i);
            String tipp = tipps[i];
            String[] sieger = match.getSieger().split(";");
            for (String s : sieger) {
                if (tipp.contains(s.toUpperCase())) {
                    this.punkte += match.getPunkte();
                    String[] ausgaenge = match.getAusgang().getAusgang().split(";");
                    for (String ausgang : ausgaenge) {
                        if (tipp.contains(ausgang.toUpperCase())) {
                            this.punkte++;
                            break;
                        }
                    }
                    break;
                }
            }

        }

    }

    public int getPunkte() {
        return this.punkte;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.punkte;
    }

    public void initUpdate() throws SQLException {
        AddTippspiel spielerPanel = new AddTippspiel(this.getName());
        int result = JOptionPane.showConfirmDialog(null, spielerPanel,
                "Spieler bearbeiten", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            DB db = new DB();
            if (spielerPanel.getBezeichnung().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bitte einen gültigen Wert eingeben");
            } else {
                db.updateSpieler(this.setName(spielerPanel.getBezeichnung().getText()));
            }

        }
    }

    public void update() throws SQLException {
        DB db = new DB();
        db.updateSpieler(this);
    }

}
