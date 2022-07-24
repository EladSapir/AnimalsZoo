//Elad Sapir , Solal Ohana, SCE Ashdod

package animals;

import graphics.AnimalChangeColor;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;
import utilities.MessageUtility;

/**
 * class that defines what is a roar object
 * @version 1.0 01/04/22
 * @author  elad sapir
 */

public abstract class Roar extends Animal {
	/**
	 * constructor of the class roar
	 * @param tName name of the animal
	 * @param p starting point of the animal 
	 */
	public Roar (String tempName, Point p,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller con ) {
		super(tempName, p,w,hspeed,vspeed,color,animalsize,Flag,zoo,con);
	}
	/**
	 * call the makesound function 
	 * @see makesound function
	 */
	void roar(){
		this.makeSound();
	}
	/**
	 * send a format to the logger function with a specific message depending the animal 
	 */
	public void makeSound() {

		String animalMSG=null;
		if(this instanceof Lion)
		{
			animalMSG="Roars, then stretches and shakes its mane";
		}
		if(this instanceof Bear)
		{
			animalMSG="Stands on its hind legs, roars and scratches its belly";
		}
		MessageUtility.logSound(this.getName(), animalMSG);
	}

}
