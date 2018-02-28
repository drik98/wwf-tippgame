package View;

import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * Can be used for adding Projekt as well as Station objects to the database.
 *
 * @author nrg
 */
public class AddMatchausgang extends JPanel {

    private JList ausgaenge = new JList();
    private String ausgang = "";
    private DefaultListModel model;

    public AddMatchausgang() {
        super();
        model = new DefaultListModel();
        if (!ausgang.isEmpty()) {
            String[] sieger = this.ausgang.split(";");
            for (String s : sieger) {
                model.addElement(s);
            }
        }
        ausgaenge.setModel(model);
        initComponents();
    }

    /**
     * Oeffnet das Panel mit vorgebener Bezeichnung
     *
     * @param ausgang
     */
    public AddMatchausgang(String ausgang) {
        super();
        this.ausgang = ausgang;
        model = new DefaultListModel();
        if (!this.ausgang.isEmpty()) {
            String[] sieger = this.ausgang.split(";");
            for (String s : sieger) {
                model.addElement(s);
            }
        }
        ausgaenge.setModel(model);
        initComponents();
    }

    private void initComponents() {
        this.add(new JLabel("Ausgänge:"));
        this.add(ausgaenge);

    }

    public String getAusgaenge() {
        ausgang = "";
        for (int i = 0; i < model.getSize(); i++) {
            ausgang += ";" + model.getElementAt(i);
        }
        return ausgang.replaceFirst(Pattern.quote(";"), "");
    }

}
