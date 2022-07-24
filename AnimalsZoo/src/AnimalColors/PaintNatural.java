package AnimalColors;

import animals.Animal;
import graphics.AnimalChangeColor;

/**
 * decorator of the animal that change him in his natural color
 * @author ASUS
 *
 */
public class PaintNatural extends AnimalChangeColor{
	public PaintNatural(Animal a){super(a);changeColor();}

	public boolean changeColor() {
		super.changeColor("Natural");
		return true;
	}
}
