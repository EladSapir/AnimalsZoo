//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package animals;

import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import graphics.AnimalChangeColor;
import graphics.IDrawable;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import utilities.MessageUtility;

/**
 * class that defines what is a lion object
 * @version 1.0 01/04/22
 * @author  solal ohana
 */

public class Lion extends Roar{
	private static double startingW=408.2;
	private static Carnivore carnLion=new Carnivore();
	private static Point startLion= new Point(20,0);
	private int scarCount;
	
	/**
	 * constructor of the lion class 
	 * @param tempName name of the lion as a string
	 * @param w weight of the lion as a double 
	 * @param hspeed horizontal speed of the lion as an integer
	 * @param vspeed vertical speed of the lion as an integer
	 * @param color color of the lion as a string 
	 * @param animalsize size of the lion as an integer
	 */
	public Lion(String tempName,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con)
	{
		super(tempName, startLion,w,hspeed,vspeed,color,animalsize, Flag,zoo,con);
		this.setDiet(carnLion);
		this.scarCount=0;
		MessageUtility.logConstractor("Lion",this.getName());
	}
	
	
	
//	
//	
//	
//	/**
//	 * constructor of the lion class
//	 * @param tempNameL name of the lion
//	 * @param p starting point of the lion
//	 */
//	public Lion(String tempNameL,Point p){
//		super(tempNameL,p);
//		this.setWeight(startingW);
//		this.setDiet(carnLion);
//		this.scarCount=0;
//		MessageUtility.logConstractor("Lion",this.getName());
//	}
//	/**
//	 * constructor of the lion class
//	 * @param tempNameL name of the lion
//	 */
//	public Lion(String tempNameL){
//		super(tempNameL,startLion);
//		this.setWeight(startingW);
//		this.setDiet(carnLion);
//		this.scarCount=0;
//		MessageUtility.logConstractor("Lion",this.getName());
//	}
//	
	/**
	 * @return the type of food a lion is 
	 */
	@Override
	public EFoodType getFoodtype() {
		MessageUtility.logGetter(this.getName(),"getFoodtype", EFoodType.NOTFOOD);
		return EFoodType.NOTFOOD;
	}
	
	/**
	 * check if the animal can eat this type of food than eating it , there is a 50$ chance for the scar count to go up
	 * @param tempfood receive a type of food 
	 * @return true or false if the animal ate the food or not
	 * @see can eat (idiet
	 */
	@Override
	public boolean eat(IEdible tempfood) {
		if(this.getDiet().canEat(tempfood.getFoodtype())==true){
			this.setWeight(this.getDiet().eat(this,tempfood ));
			Random rand=new Random();
			int value=rand.nextInt(2);
			this.scarCount += value;
			MessageUtility.logBooleanFunction(this.getName(), "eat", tempfood, true);
			this.makeSound();
			this.eatInc();
			return true;
		}
		MessageUtility.logBooleanFunction(this.getName(), "eat", tempfood, false);
		return false;
	}
	
	/**
	 * load the image of the lion depending on the color chosen by the user
	 *  then call the setimage method
	 * @see animal setimage
	 */
	@Override
	public void loadImages(String nm) {
		BufferedImage pic1,pic2;
		String colTemp=nm;
		switch(colTemp) {
		case "Red":
			 try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_r_1.png")); 
			 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_r_2.png"));
					 this.setImages(pic1, pic2);
			 }
			 catch (IOException e) {/**/ }
			 break;
		case "Natural":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_n_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_n_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		case "Blue":
			try { pic1 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_b_1.png")); 
	 		pic2 = ImageIO.read(new File(IDrawable.PICTURE_PATH+"lio_b_2.png"));
			 this.setImages(pic1, pic2);
			}
			catch (IOException e) {/**/ }
			break;
		}	
		
	}
	@Override
	public synchronized Animal clone() {
		Animal tempAnimal=new Lion(this.getName(),this.getWeight(),this.getHorSpeed(),this.getVerSpeed(),this.getColor(),this.getSize(),this.getSuspendedFlag(),this.getPanel(),(Controller)this.getController());
		tempAnimal.setLocation(this.getLocation());
		tempAnimal.setXdir(this.getXdir());
		tempAnimal.setYdir(this.getYdir());
		tempAnimal.setCount(this.getEatCount());
		return tempAnimal;
	}
}
