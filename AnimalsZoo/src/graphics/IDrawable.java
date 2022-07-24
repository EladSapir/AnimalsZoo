//Elad Sapir , Solal Ohana , SCE Ashdod

package graphics;

import java.awt.Graphics;

/**
 * interface to draw the animal to the frame
 * @author solal ohana
 * @version 2.0 01/04/22
 *@see animal
 */
public interface IDrawable {
	public final static String PICTURE_PATH = "C:/javaPROJECTS/ex1/src/graphics/";
	public void loadImages(String nm);
	public void drawObject (Graphics g);
	public String getColor();
}
