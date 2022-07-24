//Elad Sapir, Solal Ohana , SCE Ashdod

package animals;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import diet.Herbivore;
import food.EFoodType;
import graphics.AnimalChangeColor;
import graphics.IDrawable;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;
import utilities.MessageUtility;

/**
 * class that defines what is a giraffe object
 * @version 1.0 01/04/22
 * @author  elad sapir
 */
public class Giraffe extends Chew {
	private double neckLength;
	private static double startingW=450;
	private static Herbivore herbGir=new Herbivore();
	private static Point startGir= new Point(50,0);
	
	/**
	 * constructor of the giraffe class
	 * @param tempName name of the giraffe as a string 
	 * @param w weight of the giraffe as a double 
	 * @param hspeed horizontal speed of the giraffe as an integer
	 * @param vspeed vertical speed of the giraffe as an integer
	 * @param color color of the giraffe as a string
	 * @param animalsize size of the giraffe as an integer
	 */
	public Giraffe(String tempName,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con)
	{
		super(tempName, startGir,w,hspeed,vspeed,color,animalsize, Flag,zoo,con);
		this.neckLength=1.5;
		this.setDiet(herbGir);
		MessageUtility.logConstractor("Giraffe",this.getName());
	}
	
	
//	
//	/**
//	 * constructor of the giraffe class
//	 * @param tName name of the giraffe
//	 */
//	public Giraffe(String tName)
//	{
//		super(tName, startGir);
//		this.neckLength=1.5;
//		this.setWeight(startingW);
//		this.setDiet(herbGir);
//		MessageUtility.logConstractor("Giraffe",this.getName());
//	}
//	/**
//	 * constructor of the giraffe class
//	 * @param tName name of the giraffe
//	 * @param tempNeck neck of the giraffe
//	 * @param p starting point of the giraffe
//	 */
//	public Giraffe(String tName,double tempNeck,Point p) {
//		super(tName, p);
//		if(tempNeck<=2.5 && tempNeck>=1) {
//			this.neckLength=tempNeck;
//		}
//		else 
//			this.neckLength=1.5;
//		this.setWeight(startingW);
//		this.setDiet(herbGir);
//		MessageUtility.logConstractor("Giraffe",this.getName());
//	}
//	
//	/**
//	 * constructor of the giraffe class
//	 * @param tName name of the giraffe
//	 * @param tempNeck neck of the giraffe
//	 */
//	public Giraffe(String tName,double tempNeck) {
//		super(tName, startGir);
//		if(tempNeck<=2.5 && tempNeck>=1) {
//			this.neckLength=tempNeck;
//		}
//		else 
//			this.neckLength=1.5;
//		this.setWeight(startingW);
//		this.setDiet(herbGir);
//		MessageUtility.logConstractor("Giraffe",this.getName());
//	}
//	
//	/**
//	 * constructor of the giraffe class
//	 * @param tName name of the giraffe
//	 * @param p starting point of the giraffe
//	 */
//	public Giraffe(String tName,Point p)
//	{
//		super(tName, p);		
//		this.neckLength=1.5;
//		this.setWeight(startingW);
//		this.setDiet(herbGir);
//		MessageUtility.logConstractor("Giraffe",this.getName());
//	}

	/**
	 * @return the type of food a giraffe is 
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getName(),"getFoodtype", EFoodType.MEAT);
		return EFoodType.MEAT;
	}

	/**
	 * change the neck of the giraffe
	 * @param tempNeck new neck of the giraffe
	 * @return true or false if the neck was change
	 */
	public boolean setNeckLength(double tempNeck) {
		if(tempNeck<=2.5 && tempNeck>=1) {
			MessageUtility.logSetter(this.getName(), "setNeckLength", tempNeck, true);
			this.neckLength=tempNeck;
			return true;
		}
		MessageUtility.logSetter(this.getName(), "setNeckLength", tempNeck, false);
		return false;
	}
	
	/**
	 * load the images of the giraffe depending on the color chosen by the user
	 *  then call the setimage method
	 * @see animal setimage 
	 */
	@Override
	public void loadImages(String nm) {
		BufferedImage pic1,pic2;
		String colTemp=nm;
		switch(colTemp) {
		case "Red":
			 try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_r_1.png")); 
			 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_r_2.png"));
					 this.setImages(pic1, pic2);
			 }
			 catch (IOException e) {/**/ }
			 break;
		case "Natural":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_n_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_n_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		case "Blue":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_b_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"grf_b_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		}		
	}
	@Override
	public synchronized Animal clone() {
		Animal tempAnimal=new Giraffe(this.getName(),this.getWeight(),this.getHorSpeed(),this.getVerSpeed(),this.getColor(),this.getSize(),this.getSuspendedFlag(),this.getPanel(),(Controller)this.getController());
		tempAnimal.setLocation(this.getLocation());
		tempAnimal.setXdir(this.getXdir());
		tempAnimal.setYdir(this.getYdir());
		tempAnimal.setCount(this.getEatCount());
		return tempAnimal;
	}
}