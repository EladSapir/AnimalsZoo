//Elad Sapir, Solal Ohana , SCE Ashdod

package animals;

import graphics.AnimalChangeColor;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;
import utilities.MessageUtility;

/**
 * class that defines what is a chew object
 * @version 1.0 01/04/22
 * @author  solal ohana
 */
public abstract class Chew extends Animal {
	
	/**
	 * constuctor of chew - abstract class
	 * @param tName name of the animal 
	 * @param p - starting point
	 */
	public Chew (String tempName, Point p,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con) {
		super(tempName, p,w,hspeed,vspeed,color,animalsize,Flag,zoo,con);
	}
	
	
	/**
	 * calls makesound
	 * @see makeSound(chew)
	 */
	void roar(){
		this.makeSound();
	}
	
	/**
	 * send a format to the logger function with a specific message depending the animal 
	 */
	public void makeSound() {

		String animalMSG=null;
		if(this instanceof Giraffe)
		{
			animalMSG="Bleats and Stomps its legs, then chews";
		}
		if(this instanceof Turtle)
		{
			animalMSG="Retracts its head in then eats quietly";
		}
		if(this instanceof Elephant)
		{
			animalMSG="Trumpets with joy while flapping its ears, then chews";
		}
		MessageUtility.logSound(this.getName(), animalMSG);
	}
}
