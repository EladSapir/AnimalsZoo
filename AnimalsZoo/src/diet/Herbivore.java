//Elad Sapir , Solal Ohana , SCE Ashdod

package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import utilities.MessageUtility;

/**
 * class that defines what is a herbivore object
 * @version 1.0 01/04/22
 * @author  elad sapir
 */
public class Herbivore implements IDiet{

	/**
	 * check if the object can eat a food type
	 * @param food receive a food type 
	 * @return true or false if the object can eat the given food
	 */
	@Override
	public boolean canEat(EFoodType food) {
		if(food==EFoodType.VEGETABLE)
		{
			MessageUtility.logBooleanFunction(this.getClass().getSimpleName(), "canEat", food, true);
			return true;
		}
		
		MessageUtility.logBooleanFunction(this.getClass().getSimpleName(), "canEat", food, false);
		return false;
	}

	/**
	 * this function check if the object can eat a specific food and then eat it
	 * @param animal an animal type object 
	 * @param food food type object
	 * @return the weight of the animal 
	 * @see animal
	 */
	@Override
	public double eat(Animal animal, IEdible food) {
		if(this.canEat(food.getFoodtype())) {
			return animal.getWeight()*1.07;
		}
		else return animal.getWeight();
	}
	/**
	 * @return string format  of herbivore
	 */
	public String toString()
	{
		return "Herbivore";
	}
}
