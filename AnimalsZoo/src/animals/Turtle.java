//Elad Sapir , Solal Ohana , SCE Ashdod

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
 * class that defines what is a turle object
 * @version 1.0 01/04/22
 * @author  solal ohana
 */
public class Turtle extends Chew {
	private int Age;
	private static double startingW=1;
	private static Herbivore herbTur=new Herbivore();
	private static Point startTur= new Point(80,0);
	
	/**
	 * constructor of the turtle class
	 * @param tempName name of the turtle as a string
	 * @param w weight of the turtle as a double
	 * @param hspeed horizontal speed of the turtle as an integer
	 * @param vspeed vertical speed of the turtle as an integer
	 * @param color color of the turtle as a string
	 * @param animalsize size of the turtle as an integer
	 */
	public Turtle(String tempName,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con)
	{
		super(tempName, startTur,w,hspeed,vspeed,color,animalsize, Flag,zoo,con);
		this.Age=1;
		this.setDiet(herbTur);
		MessageUtility.logConstractor("Turtle",this.getName());
	}
	
//	
//	/**
//	 * constructor of the turtle class
//	 * @param tName name of the turtle
//	 * @param tempAge age of the turtle
//	 * @param p starting point of the turtle
//	 */
//	public Turtle(String tName,int tempAge,Point p) {
//		super(tName, p);
//		if(tempAge<=500 && tempAge>=0) {
//			this.Age=tempAge;
//		}
//		else 
//			this.Age=1;
//		this.setWeight(startingW);
//		this.setDiet(herbTur);
//		MessageUtility.logConstractor("Turtle",this.getName());
//	}
//	
//	/**
//	 * constructor of the turtle class
//	 * @param tName name of the turtle
//	 * @param tempAge age of the turtle
//	 */
//	public Turtle(String tName,int tempAge) {
//		super(tName, startTur);
//		if(tempAge<=500 && tempAge>=0) {
//			this.Age=tempAge;
//		}
//		else 
//			this.Age=1;
//		this.setWeight(startingW);
//		this.setDiet(herbTur);
//		MessageUtility.logConstractor("Turtle",this.getName());
//	}
//	
//	/**
//	 * constructor of the turtle class
//	 * @param tName name of the turtle
//	 * @param p starting point of the turtle
//	 */
//	public Turtle(String tName,Point p)
//	{
//		super(tName, p);
//		this.Age=1;
//		this.setWeight(startingW);
//		this.setDiet(herbTur);
//		MessageUtility.logConstractor("Turtle",this.getName());
//
//	}
//	/**
//	 * constructor of the turtle class
//	 * @param tName name of the turtle
//	 */
//	public Turtle(String tName)
//	{
//		super(tName, startTur);
//		this.Age=1;
//		this.setWeight(startingW);
//		this.setDiet(herbTur);
//		MessageUtility.logConstractor("Turtle",this.getName());
//	}
//	
	/**
	 * @return the type of food a turtle is 
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getName(),"getFoodtype", EFoodType.MEAT);
		return EFoodType.MEAT;
	}
	
	/**
	 * change the age of the turtle
	 * @param tempAge new age of the turtle
	 * @return true or false if the age was changed
	 */
	public boolean setAge(int tempAge) {
		if(tempAge<=500 && tempAge>=0) {
			MessageUtility.logSetter(this.getName(), "setAge", tempAge, true);
			this.Age=tempAge;
			return true;
		}
		MessageUtility.logSetter(this.getName(), "setAge", tempAge, false);
		return false;
	}
	
	/**
	 * load the image of the turtle depending on the color chosen by the user
	 *  then call the setimage method
	 * @see animal setimage
	 */
	@Override
	public void loadImages(String nm) {
		BufferedImage pic1,pic2;
		String colTemp=nm;
		switch(colTemp) {
		case "Red":
			 try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_r_1.png")); 
			 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_r_2.png"));
					 this.setImages(pic1, pic2);
			 }
			 catch (IOException e) {/**/ }
			 break;
		case "Natural":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_n_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_n_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		case "Blue":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_b_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"trt_b_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		}		
	}
	@Override
	public synchronized Animal clone() {
		Animal tempAnimal=new Turtle(this.getName(),this.getWeight(),this.getHorSpeed(),this.getVerSpeed(),this.getColor(),this.getSize(),this.getSuspendedFlag(),this.getPanel(),(Controller)this.getController());
		tempAnimal.setLocation(this.getLocation());
		tempAnimal.setXdir(this.getXdir());
		tempAnimal.setYdir(this.getYdir());
		tempAnimal.setCount(this.getEatCount());
		return tempAnimal;
	}
}