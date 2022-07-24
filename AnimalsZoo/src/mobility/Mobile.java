//Elad Sapir, Solal Ohana, SCE Ashdod

package mobility;



/**
 * mobile class that contains all the actions that relates to the animal movement
 * @author solal ohana
 * @version 1.0 01/04/22
 *
 */
public abstract class Mobile implements ILocatable{
	private Point location;
	private double totalDistance;
	/**
	 * constructor of the mobile class
	 * @param p starting point
	 */
	public Mobile(Point p) {
		this.location=new Point(p.getX(),p.getY());
		this.totalDistance=0.0;
	}
	/**
	 * add the distance tempD to the total distance that the object moved
	 * @param tempD distance to add
	 */
	public void addTotalDistance(double tempD) {
		this.totalDistance+=tempD;
	}
	/**
	 * calculate the distance between two points
	 * @param p destination point
	 * @return the distance between those two point
	 */
	public double calcDistance(Point p) {
		return Math.pow(Math.pow(this.location.getX()-p.getX(),2)+Math.pow(this.location.getY()-p.getY(),2),0.5); 
	}

	/**
	 * allow the object to move then add the distance to the total distance
	 * @param p destination point
	 * @return the distance moved
	 */
	public double move(Point p) {
		if (Point.cheackBounderies(p)) {
			double distance = calcDistance(p);
			this.setLocation(p);
			this.addTotalDistance(distance);
			return distance;
		}
		return 0;
	}

	
	/**
	 * change the point to the given point
	 * @param p new point of the object
	 * @return true or false if the point was change 
	 */
	@Override
	public boolean setLocation(Point p) {
		this.location.setX(p.getX());
		this.location.setY(p.getY());
		return true;
	}
	/**
	 * @return the location of the object
	 */
	public Point getLocation() {
		return this.location;
	}
}
