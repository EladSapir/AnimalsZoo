//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package animals;


import java.awt.Graphics;
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
 * class that defines what is an elephant object
 * @version 1.0 01/04/22
 * @author  elad sapir
 */
public class Elephant extends Chew {
	private double trunkLength;
	private static double startingW=500;
	private static Herbivore herbElph=new Herbivore();
	private static Point startElph= new Point(50,90);
	
	/**
	 * constructor of the elephant class
	 * @param tempName name of the elephant as a string
	 * @param w weight of the elephant as a double 
	 * @param hspeed horizontal speed of the elephant as an integer
	 * @param vspeed vertical speed of the elephant as an integer
	 * @param color color of the elephant as a string
	 * @param animalsize size of the elephant as an integer
	 */
	public Elephant(String tempName,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con)
	{
		super(tempName, startElph,w,hspeed,vspeed,color,animalsize, Flag,zoo,con);
		this.trunkLength=1;
		this.setDiet(herbElph);
		MessageUtility.logConstractor("Elephant",this.getName());
	}
	
	
//	
//	
//	/**
//	 * constructor of the elephant class
//	 * @param tName name of the elephant
//	 * @param tempTrunk size of the trunk
//	 * @param p starting point of the elephant
//	 */
//	public Elephant(String tName,double tempTrunk,Point p) {
//		super(tName, p);
//		if(tempTrunk<=3 && tempTrunk>=0.5) {
//			this.trunkLength=tempTrunk;
//		}
//		else 
//			this.trunkLength=1;
//		this.setWeight(startingW);
//		this.setDiet(herbElph);
//		MessageUtility.logConstractor("Elephant",this.getName());
//	}
//	/**
//	 * constructor of the elephant class
//	 * @param tName name of the elephant
//	 * @param tempTrunk size of the trunk of the elephant
//	 */
//	public Elephant(String tName,double tempTrunk) {
//		super(tName, startElph);
//		if(tempTrunk<=3 && tempTrunk>=0.5) {
//			this.trunkLength=tempTrunk;
//		}
//		else 
//			this.trunkLength=1;
//		this.setWeight(startingW);
//		this.setDiet(herbElph);
//		MessageUtility.logConstractor("Elephant",this.getName());
//	}
//	/**
//	 * constructor of the elephant class
//	 * @param tName name of the elephant
//	 * @param p starting point of the elephant
//	 */
//	public Elephant(String tName,Point p)
//	{
//		super(tName, p);
//		this.trunkLength=1;
//		this.setWeight(startingW);
//		this.setDiet(herbElph);
//		MessageUtility.logConstractor("Elephant",this.getName());
//	}
//	/**
//	 * constructor of the elephant class
//	 * @param tName name of the elephant
//	 */
//	public Elephant(String tName)
//	{
//		super(tName, startElph);
//		this.trunkLength=1;
//		this.setWeight(startingW);
//		this.setDiet(herbElph);
//		MessageUtility.logConstractor("Elephant",this.getName());
//	}
	
	/**
	 * @return the type of food an elephant is
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getName(),"getFoodtype", EFoodType.MEAT);
		return EFoodType.MEAT;
	}

	/**
	 * change the trunk of the elephant 
	 * @param tempTrunk new trunk of the elephant
	 * @return true or false if the trunk was change
	 */
	public boolean settrunkLength(double tempTrunk) {
		if(tempTrunk<=3 && tempTrunk>=0.5) {
			MessageUtility.logSetter(this.getName(), "settrunkLength", tempTrunk, true);
			this.trunkLength=tempTrunk;
			return true;
		}
		MessageUtility.logSetter(this.getName(), "settrunkLength", tempTrunk, false);
		return false;
	}
	
	/**
	 * load the image of the elephant depending on the color chose by the user 
	 * then call the setimage method
	 * @see animal setimage
	 */
	@Override
	public void loadImages(String nm) {
		BufferedImage pic1,pic2;
		String colTemp=nm;
		switch(colTemp) {
		case "Red":
			 try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_r_1.png")); 
			 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_r_2.png"));
					 this.setImages(pic1, pic2);
			 }
			 catch (IOException e) {/**/ }
			 break;
		case "Natural":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_n_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_n_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		case "Blue":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_b_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"elf_b_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		}		
	}
	
	@Override
	public synchronized Animal clone() {
		Animal tempAnimal=new Elephant(this.getName(),this.getWeight(),this.getHorSpeed(),this.getVerSpeed(),this.getColor(),this.getSize(),this.getSuspendedFlag(),this.getPanel(),(Controller)this.getController());
		tempAnimal.setLocation(this.getLocation());
		tempAnimal.setXdir(this.getXdir());
		tempAnimal.setYdir(this.getYdir());
		tempAnimal.setCount(this.getEatCount());
		return tempAnimal;
	}
}

