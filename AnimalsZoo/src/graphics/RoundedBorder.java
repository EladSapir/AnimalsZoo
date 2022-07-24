//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;
/**
 * class that define a rounded border for the buttons
 * @author solal ohana 
 *@version 1.0 28/04/22
 */
class RoundedBorder implements Border {

    private int radius;

/**
 * constructor of the class
 * @param radius radius of the round
 */
    RoundedBorder(int radius) {
        this.radius = radius;
    }

/**
 * return the insets of the border 
 */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    /**
     * return if the border is opaque or not trought a boolean 
     */
    public boolean isBorderOpaque() {
        return true;
    }

/**
 * print the border of the button
 */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
