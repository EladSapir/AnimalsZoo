package AnimalColors;

import animals.Animal;
import graphics.AnimalChangeColor;

/**
 * decorator of the animal that paint him in blue
 * @author ASUS
 *
 */
public class PaintBlue extends AnimalChangeColor{
	public PaintBlue(Animal a){super(a);changeColor();}

	public boolean changeColor() {
		super.changeColor("Blue");
		return true;
	}
}
