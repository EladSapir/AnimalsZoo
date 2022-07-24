package animals;

import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

/**
 * class of the omnivore factory that create an herbivore animal
 * @author solal ohana elad sapir
 *
 */
public class OmnFactory implements FactoryMethod{

	/**
	 * create an animal of the omnivore type depending on what was send to the construcore 
	 * the animal can be bear.
	 */
	@Override
	public Animal makeAnimal(String type, String tempName, Point p,int hspeed, int vspeed, String color,
			int animalsize, boolean Flag, ZooPanel zoo,Controller con) {
		Animal newAnimal=null;
		if(type=="Bear") {
			double weight = (double)animalsize*1.5;
			newAnimal=new Bear(tempName,weight,hspeed,vspeed,color,animalsize,Flag,zoo,con);
		}
		return newAnimal;
	}

}
