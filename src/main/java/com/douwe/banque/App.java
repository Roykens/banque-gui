package com.douwe.banque;

import com.douwe.banque.gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {

    private App() {
    }

    public static void main(String[] args) throws ClassNotFoundException {
        lookAndFeel();
        Class.forName("org.sqlite.JDBC");
        SwingUtilities.invokeLater(new Runnable() { 
            @Override
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setSize(1200, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static void lookAndFeel() throws ClassNotFoundException {
        try {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        } catch (InstantiationException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
