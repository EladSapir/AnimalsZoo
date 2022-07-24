//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package animals;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import diet.Omnivore;
import food.EFoodType;
import graphics.AnimalChangeColor;
import graphics.IDrawable;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;
import utilities.MessageUtility;

/**
 * class that defines what is a bear object
 * @version 1.0 01/04/22
 * @author  solal ohana
 */
public class Bear extends Roar {
	private String furColor;
	private static double startingW=308.2;
	private static Omnivore omnBear=new Omnivore();
	private static Point startBear= new Point(100,5);
	
	/**
	 * constructor of the bear class
	 * @param tempName name of the bear as string
	 * @param w weight of the bear as a double 
	 * @param hspeed horizontal speed of the bear as an integer
	 * @param vspeed vertical speed of the bear as an integer
	 * @param color color of the bear as a string
	 * @param animalsize size of the bear as an integer
	 */
	public Bear(String tempName,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con)
	{
		super(tempName, startBear,w,hspeed,vspeed,color,animalsize, Flag,zoo,con);
		this.furColor="GRAY";
		this.setDiet(omnBear);
		MessageUtility.logConstractor("Bear",this.getName());
	}
	
//	
//	/**
//	 * constructor of the bear class
//	 * @param tName name of the bear
//	 * @param tempfur color of the bear
//	 * @param p starting point of the bear
//	 */
//	public Bear(String tName,String tempfur,Point p) {
//		super(tName, p);
//		if(tempfur=="GRAY" || tempfur=="BLACK" || tempfur=="WHITE") {
//			this.furColor=tempfur;
//		}
//		else 
//			this.furColor="GRAY";
//		this.setWeight(startingW);
//		this.setDiet(omnBear);
//		MessageUtility.logConstractor("Bear",this.getName());
//	}
//	
//	/**
//	 * constructor of the bear class
//	 * @param tName name of the bear
//	 * @param tempfur color of the bear
//	 */
//	public Bear(String tName,String tempfur) {
//		super(tName, startBear);
//		if(tempfur=="GRAY" || tempfur=="BLACK" || tempfur=="WHITE") {
//			this.furColor=tempfur;
//		}
//		else 
//			this.furColor="GRAY";
//		this.setWeight(startingW);
//		this.setDiet(omnBear);
//		MessageUtility.logConstractor("Bear",this.getName());
//	}
//	/**
//	 * constructor of the bear class
//	 * @param tName name of the bear
//	 * @param p color of the bear
//	 */
//	public Bear(String tName,Point p)
//	{
//		super(tName, p);
//		this.furColor="GRAY";
//		this.setWeight(startingW);
//		this.setDiet(omnBear);
//		MessageUtility.logConstractor("Bear",this.getName());
//	}
//	/**
//	 * constructor of the bear class
//	 * @param tName name of the bear
//	 */
//	public Bear(String tName)
//	{
//		super(tName, startBear);
//		this.furColor="GRAY";
//		this.setWeight(startingW);
//		this.setDiet(omnBear);
//		MessageUtility.logConstractor("Bear",this.getName());
//	}
//	
	/**
	 * return the type of food the bear is 
	 * @return type of food the bear is 
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getName(),"getFoodtype", EFoodType.MEAT);
		return EFoodType.MEAT;
	}

	/**
	 * change the fur of the bear
	 * @param tempfur new fur of the bear
	 * @return true or false if the color was change
	 */
	public boolean setFurColor(String tempfur) {
		if(tempfur=="GRAY" || tempfur=="BLACK" || tempfur=="WHITE") {
			MessageUtility.logSetter(this.getName(), "setFurColor", tempfur, true);
			this.furColor=tempfur;
			return true;
		}
		MessageUtility.logSetter(this.getName(), "setFurColor", tempfur, false);
		return false;
	}
	
	
	/**
	 * Load the image of the bear depending on the color the user selected 
	 *  then call the setimage method
	 * @see animal setimage
	 */
	@Override
	public void loadImages(String nm) {
		BufferedImage pic1,pic2;
		String colTemp=nm;
		switch(colTemp) {
		case "Red":
			 try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_r_1.png")); 
			 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_r_2.png"));
					 this.setImages(pic1, pic2);
			 }
			 catch (IOException e) {/**/ }
			 break;
		case "Natural":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_n_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_n_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		case "Blue":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_b_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"bea_b_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		}		
	}
	
	@Override
	public synchronized Animal clone() {
		Animal tempAnimal=new Bear(this.getName(),this.getWeight(),this.getHorSpeed(),this.getVerSpeed(),this.getColor(),this.getSize(),this.getSuspendedFlag(),this.getPanel(),(Controller)this.getController());
		tempAnimal.setLocation(this.getLocation());
		tempAnimal.setXdir(this.getXdir());
		tempAnimal.setYdir(this.getYdir());
		tempAnimal.setCount(this.getEatCount());
		return tempAnimal;
	}

}
