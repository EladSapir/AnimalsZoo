//Elad Sapir , Solal Ohana , SCE Ashdod

package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
/**
 * an interface of the action animal can make to eat
 * @author  solal ohana
 * @version 1.0 01/04/22 
 *
 */
public interface IDiet {
	/**
	 * @see carnivore/herbivore/omnivore
	 * @param food food type
	 * @return boolean 
	 */
	boolean canEat(EFoodType food);
	
	/**
	 * @see carnivore/herbivore/omnivore
	 * @param animal type object
	 * @param food 
	 * @return double
	 */
	double eat(Animal animal,IEdible food);
	
	/**
	 * @see carnivore/herbivore/omnivore
	 * @return string
	 */
	public String toString();
	
}
