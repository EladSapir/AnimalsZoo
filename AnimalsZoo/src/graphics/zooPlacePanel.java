//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import animals.Animal;
import food.IEdible;
import food.Meat;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;

/**
 *class of the panel where the animal are printed
 * @author elad sapir
 * @version 1.0 28/04/22
 */
public class zooPlacePanel extends JPanel{

	BufferedImage img;
	ArrayList<Animal>animallist;
	IEdible foodl=null;
	
	
	/**
	 * constructof the panel
	 * @param anlist list of the animal in the system
	 * @param foodlist list of the food in the system
	 */
	zooPlacePanel(ArrayList<Animal>anlist, IEdible foodlist){
		animallist=anlist;
		foodl=foodlist;
		this.setLayout(null);
		this.img=null;
		
	}
	
	/**
	 * print everything int the system background animal and foods by calling the right methods
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(img!=null)
			g.drawImage(img,0,0,getWidth(),getHeight(), this);
		PaintAnimals(g);
		PaintPlant(g);
	}
	
	/**
	 * set the image of the frame
	 * @param tempIMG the image we wish to change
	 */
	public void setIMG(BufferedImage tempIMG) {
		img=tempIMG;
	}
	
	/**
	 * print every animal in the list where it should be
	 * @param g
	 */
	public void PaintAnimals(Graphics g)
	{
		for (int i=0;i<Math.min(animallist.size(),10);i++) {
			animallist.get(i).loadImages(animallist.get(i).getColor());
			animallist.get(i).drawObject(g);
		}
	}
	
	
	/**
	 * print every food in the list where it should be
	 * @param g
	 */
	public void PaintPlant(Graphics g) 
	{
		if(foodl!=null) {
			if(foodl instanceof Cabbage) {
				((Cabbage)foodl).drawObject(g);
			}
			else if(foodl instanceof Lettuce) {
				((Lettuce)foodl).drawObject(g);
			}
			else if(foodl instanceof Meat){
				try {BufferedImage meatIMG = ImageIO.read(new File(IDrawable.PICTURE_PATH+"meat.gif")); 
					g.drawImage(meatIMG,getWidth()/2,getHeight()/2,25,25, this);
				}
				catch (IOException e) {/**/ }
			}
		}
	}
	
	/**
	 * sets the food of the panel
	 * @param f - given food
	 */
	public void addFood(IEdible f) {
		foodl=f;
	}
}
