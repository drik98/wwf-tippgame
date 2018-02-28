/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Database.DB;
import View.AddMatchausgang;
import View.MainJFrame;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author hendr
 */
public class Ausgang {

    private long id;
    private String ausgang;

    public Ausgang() {
    }

    public Ausgang(long id, String ausgang) {
        this.id = id;
        this.ausgang = ausgang;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAusgang() {
        return ausgang;
    }

    public Ausgang setAusgang(String ausgang) {
        this.ausgang = ausgang;
        return this;
    }

    @Override
    public String toString() {
        String[] split = ausgang.split(";");
        String ret = "";
        for (String s : split) {
            ret += " / " + s;
        }
        return ret.replaceFirst(" / ", "");
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (Ausgang.class.getTypeName().equals(o.getClass().getTypeName())) {
                return ((Ausgang) o).getId() == this.getId();
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public static Ausgang createAusgang(MainJFrame frame) throws SQLException {
        AddMatchausgang panel = new AddMatchausgang();
        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Matchausgang anlegen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            DB db = new DB();
            if (panel.getAusgaenge().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Bitte einen gültigen Wert eingeben");
            } else {
                return db.createAusgang(panel.getAusgaenge());
            }

        }
        return null;
    }

    public void update(MainJFrame frame) throws SQLException {
        AddMatchausgang panel = new AddMatchausgang();
        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Matchausgang bearbeiten", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            DB db = new DB();
            if (panel.getAusgaenge().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Bitte einen gültigen Wert eingeben");
            } else {
                db.updateAusgang(this.setAusgang(panel.getAusgaenge()));
            }

        }
    }

}
