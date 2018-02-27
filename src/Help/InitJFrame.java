package Help;

import Domain.Match;
import Domain.Tippspiel;
import View.NewTippgame;
import View.MainJFrame;
import View.MainMenuBar;
import View.NewMatch;
import View.UebersichtTeilnehmer;
import View.UebersichtTippspiele;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Ausgelagerte Methoden fuer den Wechsel des mainpanels
 *
 * @author sht
 */
public class InitJFrame {

    /**
     *
     * @param frame
     */
    public static void showUbersichtTippgames(MainJFrame frame) {
        frame.remove(frame.getMainPanel());
        UebersichtTippspiele panel = new UebersichtTippspiele(frame);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     *
     * @param frame
     */
    public static void showUbersichtTeilnehmer(MainJFrame frame) {
        frame.remove(frame.getMainPanel());
        UebersichtTeilnehmer panel = new UebersichtTeilnehmer(frame);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     *
     * @param frame
     */
    public static void showNewTippgame(MainJFrame frame) {
        frame.remove(frame.getMainPanel());
        NewTippgame panel = new NewTippgame(frame);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     *
     * @param frame
     * @param t
     */
    public static void showNewTippgame(MainJFrame frame, Tippspiel t) {
        frame.remove(frame.getMainPanel());
        NewTippgame panel = new NewTippgame(frame, t);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     *
     * @param frame
     * @param t
     */
    public static void showNewTippgame(MainJFrame frame, Tippspiel t, Match m) {
        frame.remove(frame.getMainPanel());
        NewTippgame panel = new NewTippgame(frame, t, m);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     *
     * @param frame
     * @param t
     */
    public static void showNewMatch(MainJFrame frame, Tippspiel t) {
        frame.remove(frame.getMainPanel());
        NewMatch panel = new NewMatch(frame, t);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @param frame
     */
    public static void showStartLogo(MainJFrame frame) {
        frame.remove(frame.getMainPanel());
        JPanel panel = new JPanel();
        try {
            int width = (int) (1.5 * 423), height = (int) (1.5 * 105);
            BufferedImage img = ImageIO.read(new File("./res/start.png"));
            Image temp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage img_scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img_scaled.createGraphics();
            g2d.drawImage(temp, 0, 0, null);
            g2d.dispose();
            JLabel label = new JLabel(new ImageIcon(img_scaled));
            label.setBorder(new EmptyBorder(200, 0, 0, 0));
            panel.add(label);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridwidth = 3;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 0.9;
            frame.setMainPanel(panel);
            panel.setBackground(Color.white);
            frame.add(panel, c);
            frame.revalidate();
            frame.repaint();

        } catch (IOException ex) {
            Logger.getLogger(MainMenuBar.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void showNewMatch(MainJFrame frame, Tippspiel selectedTippspiel, Match selectedMatch) {
        frame.remove(frame.getMainPanel());
        NewMatch panel = new NewMatch(frame, selectedTippspiel, selectedMatch);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.9;
        frame.setMainPanel(panel);
        panel.setBackground(Color.white);
        frame.add(panel, c);
        frame.revalidate();
        frame.repaint();
    }

}
