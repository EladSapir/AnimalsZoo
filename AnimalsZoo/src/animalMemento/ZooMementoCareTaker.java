package animalMemento;

import java.util.ArrayList;

import animals.Animal;
import food.IEdible;
import graphics.ZooPanel;
/**
 * caretaker class of the memento design pattern
 * @author solal ohana elad sapir
 *
 */
public class ZooMementoCareTaker {
	ArrayList<ZooState> memento;
	ZooOriginator originator;
	
	/**
	 * constructor of the caretaker class
	 * save automaticaly an empty state in the memento
	 * @param zoo get the zoo panel where the zoo is 
	 */
	public ZooMementoCareTaker(ZooPanel zoo){
		memento=new ArrayList<ZooState>();
		memento.add(new ZooState(new ArrayList<Animal>(),null,"NONE"));
		originator=new ZooOriginator(zoo);
	}
	
	/**
	 * allows us to save a new state in the memento since the maximum number of state is 3 we check how much state we saved
	 * if there is more than 3 we return the last one then deleting it 
	 */
	public void hitSave() {
		if(memento.size()==3)
		{
			memento.remove(0);
		}
		memento.add(originator.save());	
	}
	
	/**
	 * gets the last state that was save and call the restore methods of the originator with the state
	 * @see originator restore
	 */
	public void hitUndo() {
		if(memento.size()!=0) {
			originator.restore(memento.get(memento.size()-1));
			memento.remove(memento.size()-1);
		}
	}
}
