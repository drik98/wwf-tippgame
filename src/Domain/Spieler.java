package Domain;

import Exceptions.InvalidTippException;
import java.util.ArrayList;

/**
 * Created by pc2 on 11.09.2016.
 */
public class Spieler {

    private Long id;
    private String name;
    private int punkte_current_year;
    private int siege_current_year;
    private int siege;
    private int punkte;

    public Spieler() {
        this.punkte = 0;
    }

    public Spieler(String name, int punkte) {
        this.name = name;
        this.punkte = punkte;
    }

    public Spieler(Long id, String name, int punkte_current_year, int siege_current_year, int siege, int punkte) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    public void addPunkte(int punkte) {
        this.punkte += punkte;
    }

    public void setTipp(String[] tipps, ArrayList<Match> matchliste) throws InvalidTippException {

        if (!tipps[0].contains(this.name)) {
            throw new InvalidTippException("Erster Tipp enthaelt nicht den Namen.");
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

}
