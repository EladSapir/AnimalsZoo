//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package mobility;

/**
 * interface of an object that allow him to move and gives us his emplacement
 * @author solal ohana
 * @version 1.0 01/04/22
 *
 */
public interface ILocatable {
	Point getLocation();
	boolean setLocation(Point p);
}
