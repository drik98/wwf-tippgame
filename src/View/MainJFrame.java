package View;

import Database.DB;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * Der aeussere Rahmen des Programms Kopfzeile, Inhalt, Fußzeile und Menubar
 *
 * @author nrg, sht
 */
public class MainJFrame extends JFrame {

    private MainMenuBar mainMenu;
    private final JPanel topLevelPanel;
    private JPanel mainPanel;

    public MainJFrame() {
        mainMenu = new MainMenuBar(this);
        topLevelPanel = new JPanel();
        
        mainPanel = new JPanel();
        initComponents();
    }


    /**
     * Intialisiert Komponenten<br>
     * Aktion bei Schliessen der Anwendung<br>
     * Setzen des Titels und des Icons<br>
     * Erstellen der Menubar<br>
     * Initalisieren von Kopf und Fusszeile
     */
    private void initComponents() {
        //general initialization
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("WWF Tippspiel");
        setIcon();

        setJMenuBar(mainMenu);

        //GridBagLayout allows to position everything into the smallest detail
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        //layout of the top panel (WZL Logo)
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.01;
        c.weightx = 1;
        c.gridwidth = 3;
        c.gridheight = 1;

        initTopLevelPanel();
        add(topLevelPanel, c);

        //layout of the mainPanel
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;

        initMainPanel();
        add(mainPanel, c);

        //layout of the bottom panel innowasLogo
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.01;
        c.weightx = 1;
        c.gridwidth = 3;
        c.gridheight = 1;


    }

    /**
     * Initialisiert die Kopfzeile<br>
     * WZL-Logo und IAW-Logo
     */
    private void initTopLevelPanel() {
        topLevelPanel.setBackground(Color.white);
        try {
            //adds the WZL logo to the top panel
            int wwfLogoWidth = 400/2, wwfLogoHeight = 309/2;
            BufferedImage wwf = ImageIO.read(new File("./res/WWF-Logo.png"));
            Image temp = wwf.getScaledInstance(wwfLogoWidth, wwfLogoHeight, Image.SCALE_SMOOTH);
            BufferedImage wwf_scaled = new BufferedImage(wwfLogoWidth, wwfLogoHeight, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = wwf_scaled.createGraphics();
            g2d.drawImage(temp, 0, 0, null);
            g2d.dispose();

            JLabel wwfLabel = new JLabel(new ImageIcon(wwf_scaled));
            wwfLabel.setBorder(new EmptyBorder(0, 50, 0, 50));
            topLevelPanel.add(wwfLabel);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

   

    private void initMainPanel() {
        mainPanel.setBackground(Color.white);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Ändern des Mainpanels
     *
     * @param mainPanel
     */
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        //aendert die Stylisierung
        Font f = new Font("Arial", this.getFont().getStyle(), 28);
        UIManager.put("Label.font", f);
        UIManager.put("Panel.font", f);
        this.mainPanel.setFont(f);
    }

    /**
     * setzt das Image als Icon fuer die Anwendung
     */
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/WWF-Logo.png")));
    }

}
