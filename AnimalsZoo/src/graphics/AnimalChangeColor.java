package graphics;

import animals.Animal;
/**
 * abstract class of the animal change color type 
 * @author elad sapir solal ohana
 *
 */
public abstract class AnimalChangeColor implements ChangeColor{
	Animal animal;
	protected AnimalChangeColor(Animal a){
		animal=a;
	}
	/**
	 * change the color of an animal depending on what color was send and
	 */
	@Override
	public boolean changeColor(String color) {
		synchronized(animal) {
			if(animal.setColor(color)) {return true;};
		}
		return false;
	}
}
