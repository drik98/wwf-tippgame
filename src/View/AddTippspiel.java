package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Can be used for adding Projekt as well as Station objects to the database.
 *
 * @author nrg
 */
public class AddTippspiel extends JPanel {

    private JTextField name;

    public AddTippspiel() {
        super();
        name = new JTextField(25);
        initComponents();
    }

    /**
     * Oeffnet das Panel mit vorgebener Bezeichnung
     *
     * @param bezeichnung
     */
    public AddTippspiel(String bezeichnung) {
        super();
        this.name = new JTextField(25);
        initComponents();
        this.name.setText(bezeichnung);
    }

    private void initComponents() {
        this.add(new JLabel("Name:"));
        this.add(name);

    }

    public JTextField getBezeichnung() {
        return name;
    }

}
