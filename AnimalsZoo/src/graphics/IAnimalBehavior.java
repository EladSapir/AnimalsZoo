//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;
/**
 * interface of the behavior of the animals
 * @see animal 
 * @author elad sapir
 *
 */
public interface IAnimalBehavior {
	public String getAnimalName();
	public int getSize();
	public void eatInc();
	public int getEatCount();
	public boolean getChanges();
	public void setChanges (boolean state);
	public void setSuspended();
	public void setResumed();
}
