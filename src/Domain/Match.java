package Domain;

import Database.DB;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author hendr
 */
public class Match {

    private long id;
    private int matchnummer;
    private long tippspiel_id;
    private int punkte;
    private String tipp;
    private Ausgang ausgang;
    private String sieger;

    public Match() {
    }

    public Match(long id, long tippspiel_id, int punkte, String tipp, long ausgangsid, String sieger, int matchnummer) {
        this.id = id;
        this.tippspiel_id = tippspiel_id;
        this.punkte = punkte;
        this.tipp = tipp;
        this.sieger = sieger;
        this.matchnummer = matchnummer;
        try {
            this.ausgang = new DB().getAusgang(ausgangsid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMatchnummer() {
        return matchnummer;
    }

    public Match setMatchnummer(int matchnummer) {
        this.matchnummer = matchnummer;
        return this;
    }

    @Override
    public String toString() {
        return "#" + this.matchnummer + ": " + this.tipp;
    }

    public long getTippspiel_id() {
        return tippspiel_id;
    }

    public Match setTippspiel_id(long tippspiel_id) {
        this.tippspiel_id = tippspiel_id;
        return this;
    }

    public int getPunkte() {
        return punkte;
    }

    public Match setPunkte(int punkte) {
        this.punkte = punkte;
        return this;
    }

    public long getId() {
        return id;
    }

    public Match setId(long id) {
        this.id = id;
        return this;
    }

    public String getTipp() {
        return tipp;
    }

    public Match setTipp(String tipp) {
        this.tipp = tipp;
        return this;
    }

    public Ausgang getAusgang() {
        return ausgang;
    }

    public Match setAusgang(Ausgang ausgang) {
        this.ausgang = ausgang;
        return this;
    }

    public String getSieger() {
        return sieger;
    }

    public Match setSieger(String sieger) {
        this.sieger = sieger;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (Match.class.getTypeName().equals(o.getClass().getTypeName())) {
                return ((Match) o).getId() == this.getId();
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

}
