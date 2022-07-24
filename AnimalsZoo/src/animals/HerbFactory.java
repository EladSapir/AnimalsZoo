package animals;

import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

/**
 * class of herbfactory that creates an herbivore animal
 * @author elad sapir solal ohana
 *
 */
public class HerbFactory implements FactoryMethod {

	/**
	 * create an herbivore animal which can be giraffe turtle or elephant 
	 * depending on what was send to the constructor.
	 */
	@Override
	public Animal makeAnimal(String type, String tempName, Point p, int hspeed, int vspeed, String color,
			int animalsize, boolean Flag, ZooPanel zoo,Controller con) {
		Animal newAnimal=null;
		double weight;
		switch(type) {
		case "Giraffe":			
			weight = (double)animalsize*2.2;
			newAnimal=new Giraffe(tempName,weight,hspeed,vspeed,color,animalsize,Flag,zoo,con);
			break;
		case "Turtle":			
			weight = (double)animalsize*0.5;
			newAnimal=new Turtle(tempName,weight,hspeed,vspeed,color,animalsize,Flag,zoo,con);
			break;
		case "Elephant":			
			weight = (double)animalsize*10;
			newAnimal=new Elephant(tempName,weight,hspeed,vspeed,color,animalsize,Flag,zoo,con);
			break;
		}
		return newAnimal;
	}

}
