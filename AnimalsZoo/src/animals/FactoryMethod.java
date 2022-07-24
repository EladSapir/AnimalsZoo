package animals;

import graphics.ZooPanel;
import graphics.Controller;
import mobility.Point;

public interface FactoryMethod {
	public Animal makeAnimal(String type,String tempName, Point p,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Controller cont);
}
