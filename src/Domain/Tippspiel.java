package Domain;

import Database.DB;
import View.AddTippspiel;
import View.MainJFrame;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 * eine Station ist unabhängig von anderen Objekten und kann in verschiedenen
 * projekten verwendet werden sie beschreibt einen Ort
 *
 * @author nrg,sht
 */
public class Tippspiel implements Serializable {

    private Long id;
    private String name;
    private String tipps;
    private String URL;

    public Tippspiel(Long id, String name, String tipps, String url) {
        this.id = id;
        this.name = name;
        this.tipps = tipps;
        this.URL = url;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTipps() {
        return tipps;
    }

    public Tippspiel setTipps(String tipps) {
        this.tipps = tipps;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Tippspiel createTippspiel(MainJFrame frame) throws SQLException {
        AddTippspiel tippspielPanel = new AddTippspiel();
        int result = JOptionPane.showConfirmDialog(frame, tippspielPanel,
                "Tippspiel anlegen", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            DB db = new DB();
            if (db.getTippspiel(tippspielPanel.getBezeichnung().getText()) != null) {
                JOptionPane.showMessageDialog(frame, "Projekt mit diesem Namen bereits vorhanden.");
            } else if (tippspielPanel.getBezeichnung().getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Bitte einen gültigen Wert eingeben");
            } else {
                return db.createTippspiel(tippspielPanel.getBezeichnung().getText());
            }

        }
        return null;
    }

    public void update() {
        try {
            new DB().updateTippspiel(this);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (o.getClass().getTypeName().equals(Tippspiel.class.getTypeName())) {
                return ((Tippspiel) o).getId().equals(this.getId());
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
