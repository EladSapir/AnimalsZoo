package animals;

import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

/**
 * carnivaore factory creates an animal that is a carnivore
 * @author solal ohana elad sapir
 *
 */
public class CarnFactory implements FactoryMethod{
	
	/**
	 * create an animal of the type sended
	 */
	@Override
	public Animal makeAnimal(String type, String tempName, Point p, int hspeed, int vspeed, String color,
			int animalsize, boolean Flag, ZooPanel zoo, Controller cont) {
		Animal newAnimal=null;
		if(type=="Lion") {
			double weight = (double)animalsize*0.8;
			newAnimal=new Lion(tempName,weight,hspeed,vspeed,color,animalsize,Flag,zoo,cont);
		}
		return newAnimal;
	}

}
