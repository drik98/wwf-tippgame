package View;

import Help.InitJFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 * Blaue Menuleiste der Anwendung<br>
 *
 * @author nrg, sht
 */
public class MainMenuBar extends JMenuBar {

    private MainJFrame frame;

    /**
     * Initialisiert die menubar
     *
     * @param frame
     */
    public MainMenuBar(MainJFrame frame) {
        super();
        this.frame = frame;
        changeStyling();
        initComponents();
    }

    /**
     * Initialisiert die Reiter der Menubar<br>
     * Projektübersicht, Station, Help und Informationen
     *
     */
    private void initComponents() {

        //Uebersicht
        JMenuItem menu = new JMenu("Übersicht");
        this.add(menu);

        JMenuItem menuItem;
        menuItem = new JMenuItem("Tippspiele", KeyEvent.VK_A);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InitJFrame.showUbersichtTippgames(frame);
            }
        });
        menu.add(menuItem);

        //Hinzufügen Button
        menuItem = new JMenuItem("Teilnehmer", KeyEvent.VK_H);
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InitJFrame.showUbersichtTeilnehmer(frame);
            }
        });
        menu.add(menuItem);

        //PROJEKTÜBERSICHT
        menu = new JMenuItem("Neues Tippspiel");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitJFrame.showNewTippgame(frame);

            }
        });
        //anpassungen der groesse, da es sonst buggt
        //das ist nötig, da man normalerweise keine JMenuitems in die Menubar implementieren kann
        menu.setMaximumSize(menu.getPreferredSize());
        menu.setMinimumSize(menu.getPreferredSize());
        this.add(menu);

    }

    /**
     * Passt die Stiliesierung an
     */
    private void changeStyling() {
        //button
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);

        //schrift
        Font f = new Font("Arial", Font.PLAIN, 28);
        UIManager.put("List.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("Panel.font", f);
        UIManager.put("Table.font", f);
        f = new Font("Arial", Font.BOLD, 28);
        UIManager.put("Label.font", f);
        UIManager.put("Button.font", f);
        UIManager.put("TableHeader.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);

        //groesse der Scrollbar
        UIManager.put("ScrollBar.width", 40);

        //Menubar Hintergrund
        this.setOpaque(true);
        this.setBackground(Color.BLACK);

        //Reiter der Menubar
        UIManager.put("Menu.opaque", true);
        UIManager.put("Menu.background", Color.BLACK);
        UIManager.put("Menu.foreground", Color.WHITE);
        UIManager.put("Menu.selectionBackground", Color.WHITE);
        UIManager.put("Menu.selectionForeground", Color.BLACK);

        //untergeordnete Items
        UIManager.put("MenuItem.opaque", true);
        UIManager.put("MenuItem.background", Color.BLACK);
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("MenuItem.selectionBackground", Color.WHITE);
        UIManager.put("MenuItem.selectionForeground", Color.BLACK);

    }
}
