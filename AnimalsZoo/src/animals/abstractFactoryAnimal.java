package animals;

import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

/**
 * class of the abstract factory of animal that create a factory depending on the type asked and the calls it
 * @author elad sapir solal ohana
 *
 */
public class abstractFactoryAnimal {
	private FactoryMethod method=null;
	public abstractFactoryAnimal(){	
	}
	/**
	 * create a factory of the type chosen by the user
	 * @param diet diet of the animal
	 * @param type what animal 	needs to be createed
	 * @param tempName name of the animal
	 * @param p point of the animal
	 * @param hspeed horizontal speed of the animal
	 * @param vspeed vertical speed of the aninal
	 * @param color color of the animal
	 * @param animalsize size of the animal
	 * @param Flag weither the animal is sleeping or not
	 * @param zoo zoo panel where the animal will be printed on the screene
	 * @param con controler of the animal
	 * @return
	 */
	public Animal getAnimal(String diet, String type, String tempName, Point p,int hspeed, int vspeed, String color,
			int animalsize, boolean Flag, ZooPanel zoo,Controller con) {
		switch(diet) {
		case "Herbivore":
			method=new HerbFactory();
			break;
		case "Omnivore":
			method=new OmnFactory();
			break;
		case "Carnivore":
			method=new CarnFactory();
			break;
		}
		return method.makeAnimal(type, tempName, p, hspeed, vspeed, color, animalsize, Flag, zoo,con);
	}
	
}
