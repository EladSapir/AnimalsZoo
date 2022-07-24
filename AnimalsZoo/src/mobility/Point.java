//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package mobility;
/**
 * class that describe a point
 * @author elad sapir
 *@version 1.0 01/04/22
 *
 */
public class Point {
	private int x;
	private int y;
	private static final int maxX = 800;
	private static final int maxY = 600;
	private static final int MIN = 0; 
	
	/**
	 * constructor of the point class
	 * @param tempX x coordinate of the point
	 * @param tempY y coordinate of the point
	 */
	public Point(int tempX, int tempY) {
			this.x = tempX;
			this.y = tempY;
	}
	
	/**
	 * constructor of the point class
	 * @param tempX x coordinate of the point
	 */
	public Point(int tempX) {
		if (tempX < maxX) {
			this.x = tempX;
			this.y = 0;
		}
	}
	
	/**
	 * constructor of the point class
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * 
	 * @return the x coordinate of the point
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * 
	 * @return the y coordinate of the point 
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * change the x value of the point
	 * @param tempX new x coordinate of the point
	 * @return true or false if changed
	 */
	public boolean setX(int tempX) {
		if (tempX <= maxX && tempX >= 0) {
			this.x = tempX;
			return true;
		}
		return false;
	}

	/**
	 * change the y value of the point
	 * @param tempY new y coordinate of the point
	 * @return true or false if changed
	 */
	public boolean setY(int tempY) {
		if (tempY <= maxY && tempY >= 0) {
			this.y = tempY;
			return true;
		}
		return false;
	}
	
	/**
	 * static method that check if a point is in specific boundaries
	 * @param newLocation point object
	 * @return true or false depending on the point 
	 */
	public static boolean cheackBounderies(Point newLocation) {
		int tempX = newLocation.getX();
		int tempY = newLocation.getY();
		if (tempX <= maxX && tempY <= maxY && tempX >= MIN && tempY >= MIN)
			return true;
		return false;
	}
	
	/**
	 * @return string format of point
	 */
	public String toString() {
		return "("+x+","+y+")";
	}

}
