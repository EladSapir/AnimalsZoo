package animalMemento;

import java.util.ArrayList;

import animals.Animal;
import food.IEdible;
import food.Meat;
import graphics.ZooPanel;
import plants.Cabbage;
import plants.Lettuce;

/**
 * originator class of the memento design pattern
 * @author ASUS
 *
 */
public class ZooOriginator {
	ZooPanel zoo;
	/**
	 * constructor of the class
	 * @param tempZoo zoopannel where all the animal are saved
	 */
	public ZooOriginator(ZooPanel tempZoo){
		zoo=tempZoo;
	}
	
	/**
	 * create a new state to save
	 * @return the present state to save
	 */
	public ZooState save() {
		return new ZooState(zoo.getAnimallist(),zoo.getFood(),zoo.getColor());
	}
	
	/**
	 * restore to the system the send state and calls the setanimallistandfoods methods of the zoo panel to restore it 
	 * @param state the state that we wish to restore
	 */
	public void restore(ZooState state) {
		ArrayList<Animal>animallist= new ArrayList<Animal>();
		for(int i=0;i<state.getanimals().size();i++)
		{
			animallist.add(state.getanimals().get(i).clone());
		}
		IEdible food;
		if(state.getfood()!=null)
		{
			switch(state.getfood().getClass().getSimpleName())
			{
			case "Lettuce":
				food=Lettuce.get_instance();
				break;
			case "Cabbage":
				food=Cabbage.get_instance();
				break;
			case "Meat":
				food=Meat.get_instance();
				break;
			default:
				food=null;
				break;
			}
		}
		else {
			food=null;
		}
		zoo.setAnimallistAndFoods(animallist,food,state.getColor());
	}
}
