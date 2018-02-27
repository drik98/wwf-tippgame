package Main;

import Database.DB;
import Help.InitJFrame;
import View.MainJFrame;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Main-klasse wird ausgefuehrt bei start initialieisert Main-frame und menubar,
 * ruft startseite auf
 *
 * @author nrg, sht
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //create JFrame
                MainJFrame main;
                main = new MainJFrame();
                main.setVisible(true);
                InitJFrame.showStartLogo(main);

            }

        });

    }

}
