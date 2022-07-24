//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import food.Meat;
import graphics.IDrawable;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	private static Lettuce ref;
	protected Lettuce() {
		MessageUtility.logConstractor("Lettuce", "Lettuce");
	}
	public static Lettuce get_instance() {
		if(ref==null) {
			ref=new Lettuce();
		}
		return ref;
	}
	@Override
	public void drawObject(Graphics g) {
		try {BufferedImage pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lettuce.png")); 
		g.drawImage(pic1,(int)(this.getLocation().getX()-this.getHeight()/2), (int)(this.getLocation().getY()-this.getHeight()/2),(int)getHeight(),(int)getHeight(), null);
	}
	catch (IOException e) {/**/ }
	
	}
}



