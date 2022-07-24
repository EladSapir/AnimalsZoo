package AnimalColors;

import animals.Animal;
import graphics.AnimalChangeColor;

/**
 * decorator of the animal that paint it in red
 * @author ASUS
 *
 */
public class PaintRed extends AnimalChangeColor{
	public PaintRed(Animal a){super(a);changeColor();}

	public boolean changeColor() {
		super.changeColor("Red");
		return true;
	}
}
