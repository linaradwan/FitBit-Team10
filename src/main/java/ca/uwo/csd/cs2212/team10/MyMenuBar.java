package ca.uwo.csd.cs2212.team10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

/**
 * This class makes a JMenuBar object that is used in the MainWindow class. 
 * @author UI Team
 */
public class MyMenuBar extends JMenuBar {

    @Override
    /**
     * This method draws the menu bar on the screen
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(87, 87, 87));
       

        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

}
