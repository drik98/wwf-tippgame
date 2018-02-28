package Database;

import Domain.Ausgang;
import Domain.Match;
import Domain.Spieler;
import Domain.Tippspiel;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles all DB interaction
 *
 * @author nrg, sht
 */
public class DB {

    private static final String FILENAME = "dbconfig.properties";
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Initialize the DB object and connect to the database specified above.
     *
     */
    public DB() {
        Properties prop = new Properties();
        InputStream input;
        try {
            input = DB.class.getClassLoader().getResourceAsStream(FILENAME);
            // load a properties file
            prop.load(input);
            Class.forName(prop.getProperty("db.driver"));
            con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.username"), prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Tippspiel> getTippspielList() throws SQLException {
        ArrayList<Tippspiel> tippspielList = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM tippgame ORDER BY name");
        rs = ps.executeQuery();
        while (rs.next()) {
            tippspielList.add(new Tippspiel(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("tipps"),
                    rs.getString("url")
            ));
        }
        return tippspielList;
    }

    public ArrayList<Match> getMatchList(long tippspielid) throws SQLException {
        ArrayList<Match> matchList = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM match WHERE tippspiel_id = ? ORDER BY matchnummer asc");
        ps.setLong(1, tippspielid);
        rs = ps.executeQuery();
        while (rs.next()) {
            matchList.add(new Match(
                    rs.getLong("id"),
                    rs.getLong("tippspiel_id"),
                    rs.getInt("punkte"),
                    rs.getString("tipp"),
                    rs.getLong("ausgang_id"),
                    rs.getString("sieger"),
                    rs.getInt("matchnummer")
            ));
        }
        return matchList;
    }

    public void removeTippspiel(Long tippspielid) throws SQLException {
        ps = con.prepareStatement("DELETE FROM tippgame WHERE id = ?");
        ps.setLong(1, tippspielid);
        ps.execute();

        ps = con.prepareStatement("SELECT * FROM tippgame_teilnehmer WHERE tippgame_id = ?");
        ps.setLong(1, tippspielid);
        rs = ps.executeQuery();
        PreparedStatement s;
        while (rs.next()) {
            s = con.prepareStatement("UPDATE spieler SET punkte = punkte - ?, punkte2017 = punkte2017 - ? WHERE id = ?");
            s.setLong(1, rs.getLong("punkte"));
            s.setLong(2, rs.getLong("punkte"));
            s.setLong(3, rs.getLong("spieler_id"));
            s.execute();
            if (rs.getBoolean("ist_sieger")) {
                s = con.prepareStatement("UPDATE spieler SET siege = siege - 1, siege2017 = siege2017 - 1 WHERE id = ?");
                s.setLong(1, rs.getLong("spieler_id"));
            }
        }
        ps = con.prepareStatement("DELETE FROM tippgame_teilnehmer WHERE tippgame_id = ?");
        ps.setLong(1, tippspielid);
        ps.execute();
    }

    /**
     * returns a project with the given id
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    public Tippspiel getTippspiel(Long id) throws SQLException {
        Tippspiel t = null;
        ps = con.prepareStatement("SELECT * FROM tippgame WHERE id = ?");
        ps.setLong(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            t = new Tippspiel(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("tipps"),
                    rs.getString("url"));
        }
        return t;
    }

    /**
     * Returns a project with the given description
     *
     * @param name
     * @return
     * @throws java.sql.SQLException
     */
    public Tippspiel getTippspiel(String name) throws SQLException {
        Tippspiel t = null;
        ps = con.prepareStatement("SELECT * FROM tippgame WHERE name = ?");
        ps.setString(1, name);
        rs = ps.executeQuery();
        if (rs.next()) {
            t = new Tippspiel(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("tipps"),
                    rs.getString("url"));
        }
        return t;
    }

    public Tippspiel createTippspiel(String name) throws SQLException {
        ps = con.prepareStatement("SELECT COALESCE(max(id),0) as id FROM tippgame");
        rs = ps.executeQuery();
        long id = (rs.next()) ? rs.getLong("id") + 1 : 1;

        ps = con.prepareStatement("INSERT INTO tippgame (id, name, tipps) VALUES(?,?,'')");
        ps.setLong(1, id);
        ps.setString(2, name);
        ps.execute();
        return new Tippspiel(id, name, "", "");
    }

    public ArrayList<Ausgang> getAusgangsList() throws SQLException {
        ArrayList<Ausgang> ausgangList = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM ausgang");
        rs = ps.executeQuery();
        while (rs.next()) {
            ausgangList.add(new Ausgang(
                    rs.getLong("id"),
                    rs.getString("ausgang")));
        }
        return ausgangList;
    }

    public Ausgang getAusgang(long ausgangsid) throws SQLException {
        Ausgang a = null;
        ps = con.prepareStatement("SELECT * FROM ausgang WHERE id = ?");
        ps.setLong(1, ausgangsid);
        rs = ps.executeQuery();
        if (rs.next()) {
            a = new Ausgang(
                    rs.getLong("id"),
                    rs.getString("ausgang"));
        }
        return a;
    }

    public Match createMatch(Match m) throws SQLException {
        ps = con.prepareStatement("SELECT COALESCE(max(id),0) as id FROM match");
        rs = ps.executeQuery();
        long id = (rs.next()) ? rs.getLong("id") + 1 : 1;

        ps = con.prepareStatement("INSERT INTO match (id, tippspiel_id, punkte, tipp, sieger, ausgang_id, matchnummer) VALUES (?, ?, ?, ?, ?, ?, ?)");
        ps.setLong(1, id);
        ps.setLong(2, m.getTippspiel_id());
        ps.setInt(3, m.getPunkte());
        ps.setString(4, m.getTipp());
        ps.setString(5, m.getSieger());
        ps.setLong(6, m.getAusgang().getId());
        ps.setLong(7, m.getMatchnummer());
        ps.execute();
        m.setId(id);
        return m;
    }

    public void removeMatch(long id) throws SQLException {
        ps = con.prepareStatement("DELETE FROM match WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
    }

    public Match updateMatch(Match m) throws SQLException {
        if (m.getId() == 0) {
            m = createMatch(m);
        }
        ps = con.prepareStatement("UPDATE match SET tippspiel_id = ?, punkte = ?, tipp = ?, sieger = ?, ausgang_id = ?, matchnummer = ? "
                + "WHERE id = ?");
        ps.setLong(1, m.getTippspiel_id());
        ps.setInt(2, m.getPunkte());
        ps.setString(3, m.getTipp());
        ps.setString(4, m.getSieger());
        ps.setLong(5, m.getAusgang().getId());
        ps.setLong(6, m.getMatchnummer());
        ps.setLong(7, m.getId());
        ps.execute();
        return m;
    }

    public Tippspiel updateTippspiel(Tippspiel t) throws SQLException {
        if (t.getId() == 0) {
            t = createTippspiel(t.getName());
        }
        ps = con.prepareStatement("UPDATE tippgame SET tipps = ?, name = ?, url = ? "
                + "WHERE id = ?");
        ps.setString(1, t.getTipps());
        ps.setString(2, t.getName());
        ps.setString(3, t.getURL());
        ps.setLong(4, t.getId());
        ps.execute();
        return t;
    }

    public Long getNextMatchnummer(Long tippgameid) {
        try {
            ps = con.prepareStatement("SELECT COALESCE(max(matchnummer),0) as matchnummer FROM match WHERE tippspiel_id = ?");
            ps.setLong(1, tippgameid);
            rs = ps.executeQuery();
            return (rs.next()) ? rs.getLong("matchnummer") + 1 : 1;
        } catch (SQLException ex) {
            return 1L;
        }
    }

    public int getAnzahlMatches(Tippspiel t) throws SQLException {
        ps = con.prepareStatement("SELECT count(*) as count FROM match WHERE tippspiel_id = ?");
        ps.setLong(1, t.getId());
        rs = ps.executeQuery();
        return (rs.next()) ? rs.getInt("count") : 1;
    }

    public ArrayList<Spieler> getSpieler() throws SQLException {
        ArrayList<Spieler> spielerlist = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM spieler");
        rs = ps.executeQuery();
        while (rs.next()) {
            spielerlist.add(new Spieler(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("punkte"),
                    rs.getInt("siege"),
                    rs.getInt("punkte_current_year"),
                    rs.getInt("siege_current_year")));
        }
        return spielerlist;
    }
//
//    public ArrayList<Spieler> getSpieler(long tippgame_id) throws SQLException {
//        ArrayList<Spieler> spielerlist = new ArrayList<>();
//        ps = con.prepareStatement("SELECT * FROM spieler WHERE id NOT IN (SELECT spieler_id FROM tippgame_teilnehmer WHERE tippgame_id = ?)");
//        ps.setLong(1, tippgame_id);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            spielerlist.add(new Spieler(
//                    rs.getLong("id"),
//                    rs.getString("name"),
//                    rs.getInt("punkte"),
//                    rs.getInt("siege"),
//                    rs.getInt("punkte_current_year"),
//                    rs.getInt("siege_current_year")));
//        }
//        return spielerlist;
//    }

    public void saveTippgameTeilnehmerTemp(Long tippspielid, List<Spieler> spielerListe) throws SQLException {
        ps = con.prepareStatement("DELETE FROM tippgame_teilnehmer WHERE tippgame_id = ?");
        ps.setLong(1, tippspielid);
        ps.execute();

        int platzierung = 0;
        int punkte = Integer.MAX_VALUE;
        for (Spieler s : spielerListe) {
            if (s.getPunkte() < punkte) {
                platzierung++;
                punkte = s.getPunkte();
            }
            //id
            ps = con.prepareStatement("SELECT COALESCE(max(id),0) as id FROM tippgame_teilnehmer");
            rs = ps.executeQuery();
            long id = (rs.next()) ? rs.getLong("id") + 1 : 1;

            //spieler id
            ps = con.prepareStatement("SELECT COALESCE(id,(SELECT max(id) FROM spieler) + 1) as id FROM spieler WHERE name = ?");
            ps.setString(1, s.getName());
            rs = ps.executeQuery();
            long spielerid = (rs.next()) ? rs.getLong("id") : 1;

            ps = con.prepareStatement("INSERT INTO tippgame_teilnehmer (id, spieler_id, tippgame_id, punkte, ist_sieger, platzierung) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setLong(1, id);
            ps.setLong(2, spielerid);
            ps.setLong(3, tippspielid);
            ps.setInt(4, s.getPunkte());
            ps.setBoolean(5, platzierung == 1);
            ps.setInt(6, platzierung);
            ps.execute();
        }
    }

    public ArrayList<Spieler> getSpielerDieNichtGetipptHaben(long tippspielid) throws SQLException {
        ArrayList<Spieler> spielerlist = new ArrayList<>();
        ps = con.prepareStatement("SELECT * FROM spieler WHERE id NOT IN (SELECT spieler_id FROM tippgame_teilnehmer WHERE tippgame_id = ?)");
        ps.setLong(1, tippspielid);
        rs = ps.executeQuery();
        while (rs.next()) {
            spielerlist.add(new Spieler(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("punkte"),
                    rs.getInt("siege"),
                    rs.getInt("punkte_current_year"),
                    rs.getInt("siege_current_year")));
        }
        return spielerlist;
    }

    public void removeAusgang(long id) throws SQLException {
        ps = con.prepareStatement("DELETE FROM ausgang WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
        ps = con.prepareStatement("DELETE FROM match WHERE ausgang_id = ?");
        ps.setLong(1, id);
        ps.execute();
    }

    public Ausgang createAusgang(String ausgaenge) throws SQLException {
        ps = con.prepareStatement("SELECT COALESCE(max(id),0) as id FROM ausgang");
        rs = ps.executeQuery();
        long id = (rs.next()) ? rs.getLong("id") + 1 : 1;

        ps = con.prepareStatement("INSERT INTO ausgang (id, ausgang) VALUES (?, ?)");
        ps.setLong(1, id);
        ps.setString(2, ausgaenge);
        ps.execute();
        return new Ausgang(id, ausgaenge);
    }

    public Ausgang updateAusgang(Ausgang a) throws SQLException {
        ps = con.prepareStatement("UPDATE ausgang SET ausgang = ? "
                + "WHERE id = ?");
        ps.setString(1, a.getAusgang());
        ps.setLong(2, a.getId());
        ps.execute();
        return a;
    }

    public void removeSpieler(Long id) throws SQLException {
        ps = con.prepareStatement("DELETE FROM spieler WHERE id = ?");
        ps.setLong(1, id);
        ps.execute();
        ps = con.prepareStatement("DELETE FROM tippgame_teilnehmer WHERE spieler_id = ?");
        ps.setLong(1, id);
        ps.execute();
    }

    public void updateSpieler(Spieler s) throws SQLException {
        ps = con.prepareStatement("UPDATE spieler SET name = ?, punkte = ?, punkte_current_year = ?, siege = ?, siege_current_year = ? "
                + "WHERE id = ?");
        ps.setString(1, s.getName());
        ps.setInt(2, s.getPunkte());
        ps.setInt(3, s.getPunkte_current_year());
        ps.setInt(4, s.getSiege());
        ps.setInt(5, s.getSiege_current_year());
        ps.setLong(6, s.getId());
        ps.execute();
    }

}
