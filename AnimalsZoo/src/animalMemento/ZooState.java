package animalMemento;

import java.util.ArrayList;

import animals.Animal;
import food.IEdible;
import food.Meat;
import plants.Cabbage;
import plants.Lettuce;

public class ZooState {
	ArrayList<Animal> animallist;
	IEdible food;
	String zooColor;
	/**
	 * create a new state of the system 
	 * @param list list of animal to save
	 * @param tfood food in the system to save
	 * @param color color in the system to save
	 */
	public ZooState(ArrayList <Animal> list,IEdible tfood,String color){
		animallist= new ArrayList<Animal>();
		for(int i=0;i<list.size();i++)
		{
			animallist.add(list.get(i).clone());
		}
		if(tfood!=null)
		{
			switch(tfood.getClass().getSimpleName())
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
		zooColor=color;
	}
	/**
	 * return the animals saved
	 * @return list of animals
	 */
	public ArrayList<Animal> getanimals()
	{
		return animallist;
	}
	/**
	 * return the food saved
	 * @return food
	 */
	public IEdible getfood()
	{
		return food;
	}
	/**
	 * return the color of the screen saved in the stae
	 * @return color as a string
	 */
	public String getColor() {return this.zooColor;}
}
