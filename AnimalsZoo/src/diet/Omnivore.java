//Elad Sapir , Solal Ohana , SCE Ashdod

package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;
import utilities.MessageUtility;

/**
 * class that defines what is an omnivore object
 * @author elad sapir
 * @version 1.0 01/04/22
 *
 */
public class Omnivore implements IDiet{
	private Carnivore innerCar;
	private Herbivore innerHerb;
	
	/**
	 * constructor of omnivore 
	 * an omnivore contain a carnivore field and an herbivore field 
	 */
	public Omnivore() {
		innerCar=new Carnivore();
		innerHerb=new Herbivore();
	}
	
	/**
	 * this function check if the object can eat the given object 
	 * @param food food type
	 * @return true or false  
	 * @see caneat herbivore/carnivore
	 */
	@Override
	public boolean canEat(EFoodType food) {
		int flag1=0;
		if(innerCar.canEat(food))
		{
			MessageUtility.logBooleanFunction(this.getClass().getSimpleName(), "canEat", food, true);
			flag1=1;
		}
		if(innerHerb.canEat(food))
		{
			MessageUtility.logBooleanFunction(this.getClass().getSimpleName(), "canEat", food, true);
			flag1=1;
		}
		if (flag1==0)
			MessageUtility.logBooleanFunction(this.getClass().getSimpleName(), "canEat", food, false);
		return(innerCar.canEat(food)||innerHerb.canEat(food));
	}

	/**
	 * this function check if the animal can eat the given food and if yes eat it
	 * @param animal animal that want to eat something
	 * @param food a specific food 
	 * @return double the weight of the animal 
	 * @see carnivore/herbivore
	 */
	@Override
	public double eat(Animal animal, IEdible food) {
		if(innerCar.canEat(food.getFoodtype())==true)
			return innerCar.eat(animal, food);
		if(innerHerb.canEat(food.getFoodtype())==true)
			return innerHerb.eat(animal, food);
		return 0;
	}
	
	/**
	 * @return string format  of omnivore
	 */
	public String toString()
	{
		return "Omnivore";
	}
}
