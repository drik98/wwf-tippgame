package Main;

import Help.InitJFrame;
import View.MainJFrame;

/**
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
                InitJFrame.showNewTippgame(main);

            }

        });

    }

}
