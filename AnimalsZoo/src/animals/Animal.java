//Elad Sapir , Solal Ohana , SCE Ashdod

package animals;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicBoolean;

import diet.IDiet;
import food.IEdible;
import graphics.AnimalChangeColor;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import graphics.Controller;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

/**
 * class that defines what is an animal object
 * @version 1.0 01/04/22
 * @author  elad sapir
 */
public abstract class Animal extends Mobile implements IEdible,IDrawable,IAnimalBehavior,Runnable {	
	private String name;
	private double weight;
	private IDiet diet;
	private final int EAT_DISTANCE = 5;
	private int size;
	private String col;
	private int horSpeed;
	private int verSpeed;
	private boolean coordChanged;
	private int x_dir=1;
	private int y_dir=1;
	private int eatCount=0;
	private ZooPanel pan;
	private BufferedImage img1, img2;
	protected static boolean threadSuspended;
	AtomicBoolean deadflag;
	Controller currentController;
	static boolean thereIsFood=false;
	private Observable obser=new Observable(){
		@Override
		public void notifyObservers(Object o)
		{
			super.setChanged();
			super.notifyObservers(o);
		}
	};
	/**
	 * constructor of the animal class
	 * @param tempName name of the animal as a string
	 * @param p point of the animal as a point
	 * @param w weight of the animal as a double
	 * @param hspeed horizontal speed of the animal as an integer
	 * @param vspeed vertical speed of the animal as an integer
	 * @param color color of the animal as a string
	 * @param animalsize size of the animal as an integer
	 */
	public Animal(String tempName, Point p,double w,int hspeed,int vspeed,String color,int animalsize,boolean Flag,ZooPanel zoo,Observer obs) {
		super(p);
		obser.addObserver(obs);
		currentController=(Controller)obs;
		deadflag=new AtomicBoolean();
		deadflag.set(false);
		if(Point.cheackBounderies(p)==false)
		{
			Point Tzero=new Point(0,0);
			this.setLocation(Tzero);
		}
		this.pan=zoo;
		this.name=tempName;
		this.weight=w;
		this.horSpeed=hspeed;
		this.verSpeed=vspeed;
		this.col=color;
		this.size=animalsize;
		this.coordChanged=true;
		threadSuspended=Flag;
		MessageUtility.logConstractor("Animal",this.name);
	}
	/**
	 * abstract function make sound
	 * @see makesound in roar and chew
	 */
	public abstract void makeSound();
	
	/**
	 * get animal weight
	 * @return the weight of the animal
	 */
	public double getWeight() {
		MessageUtility.logGetter(this.getAnimalName(),"getWeight", this.weight);
		return this.weight;}
	
	/**
	 * change the weight of the animal
	 * @param tempW new weight of the animal
	 * @return true or false if the weight was changed
	 */
	public boolean setWeight(double tempW) {
		if (tempW>=0) {
			this.weight=tempW;
			MessageUtility.logSetter(this.getAnimalName(), "setWeight", tempW, true);
			return true;
		}
		MessageUtility.logSetter(this.getAnimalName(), "setWeight", tempW, false);
		return false;
	}
	
	/**
	 * change the diet of the animal
	 * @param tempDiet new diet of the animal
	 * @return true or false if the weight was changer
	 */
	public boolean setDiet(IDiet tempDiet) {
		MessageUtility.logSetter(this.getAnimalName(), "setDiet", tempDiet, true);
		this.diet=tempDiet;return true;
		}
	
	/**
	 * the eat action of the animal check if he can eat it or not if true he it it if not return false
	 * @param tempfood the food of the animal 
	 * @see caneat (idiet)
	 * @return true or false if te food was eaten
	 */
	public boolean eat(IEdible tempfood) {
		if(this.getDiet().canEat(tempfood.getFoodtype())==true){
			this.setWeight(this.getDiet().eat(this,tempfood ));
			MessageUtility.logBooleanFunction(this.getAnimalName(), "eat", tempfood, true);
			this.makeSound();
			eatCount++;
			return true;
		}
		MessageUtility.logBooleanFunction(this.getAnimalName(), "eat", tempfood, false);
		return false;
	}
	
	/**
	 * return the diet of the animal 
	 * @return diet of the animal 
	 */
	public IDiet getDiet() {
		MessageUtility.logGetter(this.getAnimalName(),"getDiet", this.diet); 
		return this.diet;}
	
	/**
	 * change the name of the animal
	 * @param tempName new name of the animal
	 * @return true or false
	 */
	public boolean setName(String tempName) {
		MessageUtility.logSetter(this.getAnimalName(), "setName", tempName, true);
		this.name=tempName;
		return true;
	}
	
	/**
	 * return the name of the animal 
	 * @return  string name of the animal 
	 */
	public String getName() {
		MessageUtility.logGetter(this.name,"getName", this.name);
		return this.name;
		}
	
	/**
	 * moves the animal according to two options:
	 * If there is food on the panel and the animal can eat it, moves the animal towards the food
	 * if there is no food on the panel or the animal cant eat the food, moves it according to the speeds entered by the user
	 * @param p the new point of the animal
	 * @return double the distance traveled by the animal
	 * @see setChanges
	 * @see setWeight
	 */
	public double move() {
		//System.out.println("x: "+this.getLocation().getX()+"y: "+this.getLocation().getY());
		if (thereIsFood &&this.diet.canEat(pan.getFood().getFoodtype())){
			double curX=this.getLocation().getX();
			double curY=this.getLocation().getY();
			double verSp=0;
			double horSp=0;
			if (curX==400) {
				verSp=this.verSpeed;
				if(curY>300)
					this.y_dir=-1;
				else
					this.y_dir=1;
			}
			else 
			{
				if(curY==300) 
				{
					horSp=this.horSpeed;
					if(curX>400)
						this.x_dir=-1;
					else
						this.x_dir=1;
				}
				else 
				{
					double xDiff=400-curX;
					double yDiff=300-curY;
					if(xDiff<0) 
					{
						x_dir=-1;
						xDiff=-xDiff;
					}
					else
						x_dir=1;
					if(yDiff<0) 
					{
						y_dir=-1;
						yDiff=-yDiff;
					}
					else 
					{
						y_dir=1;
					}
					//System.out.println("Ydiff: "+yDiff);System.out.println("Xdiff: "+xDiff);
					double m=yDiff/xDiff;
					//System.out.println("m "+m);
					//System.out.println("verspeed "+verSpeed +"horrspeed "+horSpeed);
					if(m<=10&&m>=0.1) 
					{
						horSp=1;
						verSp=m;
					}
					else 
					{
						if(m>10) 
						{
							horSp=1;
							while(m>10) 
							{
								horSp/=10;
								m/=10;
							}
							verSp=m;
						}
						else {
							horSp=1;
							while(m<0.1) 
							{
								horSp/=10;
								m*=10;
							}
							verSp=m;
						}
					}
				}
			}
			double newX=curX+horSp*this.x_dir;
			double newY=curY+verSp*this.y_dir;
			double dis = super.move(new Point((int)newX,(int)newY));
			if (dis != 0) {
				double w = this.getWeight();
				this.setWeight(w - (dis * w * 0.00025));
			}
			this.setChanges(true);
			obser.notifyObservers();
			return dis;
		}
		else {
			int newX=this.getLocation().getX()+this.horSpeed*this.x_dir;
			int newY=this.getLocation().getY()+this.verSpeed*this.y_dir;
			if(newX>=800) {
				x_dir=-1;
				newX=800-(newX-800);
			}
			if(newX<=0) {
				x_dir=1;
				newX=-newX;
			}
			if(newY>=600) {
				y_dir=-1;
				newY=600-(newY-600);
			}
			if(newY<=0) {
				y_dir=1;
				newY=-newY;
			}
			double dis = super.move(new Point(newX,newY));
			if (dis != 0) {
				double w = this.getWeight();
				this.setWeight(w - (dis * w * 0.00025));
			}
			this.setChanges(true);
			//obser.notifyObservers();
			return dis;
		}
	}

	
	/**
	 * return format as string of the class
	 */
	public String toString()
	{
		if(this instanceof Lion) {
			return "[Lion]"+this.name;
		}
		if(this instanceof Elephant) {
			return "[Elephant]"+this.name;
		}
		if(this instanceof Turtle) {
			return "[Turtle]"+this.name;
		}
		if(this instanceof Giraffe) {
			return "[Giraffe]"+this.name;
		}
		if(this instanceof Bear) {
			return "[Bear]"+this.name;
		}
		else return "Animal";
	}
	
	/**
	 * Return the color of the animal as a string
	 * @return color
	 */
	public String getColor()
	{
		return this.col;
	}
	
	/**
	 * Return the size of the animal as an integer
	 * @return  size
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * Return the eat counter as an integer
	 * @return eatcount
	 */
	public int getEatCount()
	{
		return this.eatCount;
	}
	
	/**
	 * Return if there were changes in the coordinates of the animal as a boolean
	 * @return coordChanged
	 */
	public boolean getChanges()
	{
		return this.coordChanged;
	}
	
	/**
	 * Increase the eat counter of the animal
	 */
	public void eatInc()
	{
		this.eatCount++;
	}
	
	/**
	 * Set the change flag to true if there were any change to the animal
	 */
	public void setChanges (boolean state)
	{
		this.coordChanged=state;
	}
	
	/**
	 * Return the name of the animal as a string
	 * @return name
	 */
	public String getAnimalName() {
		MessageUtility.logGetter(this.name,"getName", this.name);
		return this.name;
		}
	
	/**
	 * abstract method 
	 */
	public void loadImages(String nm)
	{
	}
	
	/**
	 * print the animal on the frame depending on the direction and the color
	 */
	public void drawObject (Graphics g)
	{
	 //g.setColor(col);
	Point location=this.getLocation();
	 if(x_dir==1) // giraffe goes to the right side
	g.drawImage(img1, location.getX()-size/2, location.getY()-size/10, (int)(size * 1.2),  size, pan);
	 else // giraffe goes to the left side
	g.drawImage(img2, location.getX()-size/2, location.getY()-size/10, (int)(size * 1.2),  size, pan);
	}
	
	/**
	 * Return the horizontal speed of the animal as an integer 
	 * @return horSpeed
	 */
	public int getHorSpeed() {
		return this.horSpeed;
	}
	
	/**
	 * Return the vertical speed of the animal as an integer
	 * @return verspeed
	 */
	public int getVerSpeed() {
		return this.verSpeed;
	}
	
	/**
	 * change the image of the animal depending on what was loaded
	 * @param p1 first image (when the animal is going to the left)
	 * @param p2 second image (when the animal is going to the right)
	 */
	public void setImages(BufferedImage p1,BufferedImage p2) {
		this.img1=p1;
		this.img2=p2;
	}
	public boolean getSuspendedFlag() {
		return this.threadSuspended;
	}
	public ZooPanel getPanel() {
		return this.pan;
	}
	public Observer getController() {
		return this.currentController;
	}
	/**
	 * synchronized, sets the suspended flag to false and notifies
	 */
	public synchronized void setSuspended() {
		threadSuspended=false;
		notify();
	}
	
	/**
	 *  synchronized, sets the suspended flag to true and notifies
	 */
	public synchronized void setResumed() {
		threadSuspended=true;
		notify();
	}
	
	/**
	 * sets the dead flag to true
	 */
	public void setDead() {
		//thread.interrupt();
		deadflag.set(true);;
//		setChanged();
//		notifyObservers();
	}
	
	/**
	 * synchronized, sets the food flag to the given boolean value
	 * @param f
	 */
	public synchronized static void setFood(boolean f) {
		thereIsFood=f;
	}
	
	public boolean setColor(String newCol) {
		if(newCol=="Natural"||newCol=="Blue"||newCol=="Red") {
			this.col=newCol;
			obser.notifyObservers();
			return true;
		}
		obser.notifyObservers();
		return false;
	}
	public Animal clone() {
		return null;
	}
	
	public int getXdir() {return this.x_dir;}
	public int getYdir() {return this.y_dir;}
	public void setXdir(int x) {this.x_dir=x;}
	public void setYdir(int y) {this.y_dir=y;}
	public void setCount(int count) {this.eatCount=count;}
	/**
	 * runs the animal while it is alive, when dies - interrupts the thread
	 * @see Animal - move
	 * @see ZooPanel - repaint
	 */
	@Override
	public void run() {
			while(!deadflag.get())			
			{
				if(!threadSuspended) {
					synchronized(this) {
						try {
							wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
				else
				{
					this.move();
					obser.notifyObservers();
					this.pan.repaint();
					
					synchronized(this){
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
}
