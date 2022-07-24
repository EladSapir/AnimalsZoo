//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import animalMemento.ZooMementoCareTaker;
import animals.Animal;
import food.*;
import mobility.Point;
import plants.*;

/**
 * create the panel where all the animal will appears
 * @author solal ohana
 *@version 1.0 28/04/22
 */
public class ZooPanel extends JPanel implements ActionListener{
	private static volatile ZooPanel ref;
	JPanel Buttons;
	zooPlacePanel zooPlace;
	JButton addAnimal;
	JButton Sleep;
	JButton WakeUp;
	JButton clear;
	JButton food;
	JButton info;
	JButton exit;
	JButton ChangeColor;
	JButton Save;
	JButton Restore;
	IEdible foods;
	ArrayList<Animal> animallist;
	ImageIcon addIcon;
	//ImageIcon moveIcon;
	ImageIcon clearIcon;
	ImageIcon foodIcon;
	ImageIcon infoIcon;
	ImageIcon exitIcon;
	ImageIcon sleepIcon;
	ImageIcon wakeUpIcon;
	ImageIcon saveIcon;
	ImageIcon restoreIcon;
	ImageIcon colorIcon;
	static int size = 10;
	AtomicBoolean flagAwake;
	//Thread controller;
	Controller controllerX;
	ThreadPoolExecutor TPE;
	BlockingQueue<Runnable> blockingQueue;
	ZooMementoCareTaker careTaker;
	private static final int maxWaiting = 5;
	String color="NONE";
	/**
	 * constructor of the panel, build all the buttons and icons
	 * build the threadpool of the animal that will run in the panel
	 * build the controller of all the zoo
	 * give all the options to the user
	 * action listeners of the buttons
	 * @see ActionListener
	 */
	private ZooPanel() {
 		//controller=new Thread(this);
 		addIcon=new ImageIcon(Animal.PICTURE_PATH+"plusIcon.png");
 		//moveIcon=new ImageIcon(Animal.PICTURE_PATH+"moveIcon.png");
 		clearIcon=new ImageIcon(Animal.PICTURE_PATH+"clearIcon.png");
 		foodIcon=new ImageIcon(Animal.PICTURE_PATH+"foodIcon.png");
 		infoIcon=new ImageIcon(Animal.PICTURE_PATH+"infoIcon.png");
 		exitIcon=new ImageIcon(Animal.PICTURE_PATH+"exitIcon.png");
 		wakeUpIcon=new ImageIcon(Animal.PICTURE_PATH+"wakeUpIcon.png");
 		sleepIcon=new ImageIcon(Animal.PICTURE_PATH+"sleepIcon.png");
 		saveIcon=new ImageIcon(Animal.PICTURE_PATH+"save.png");;
 		restoreIcon=new ImageIcon(Animal.PICTURE_PATH+"restore.png");;
 		colorIcon=new ImageIcon(Animal.PICTURE_PATH+"color.png");;
		animallist = new ArrayList<Animal>();
		Buttons = new JPanel();
		Buttons.setLayout(new GridLayout(1, 10, 2, 0));
		Buttons.setPreferredSize(new Dimension(700, 75));
		this.setLayout(new BorderLayout());
		addAnimal = new JButton();
		editbutton(addAnimal,"Add Animal",addIcon);
		Buttons.add(addAnimal);
		Sleep = new JButton();
		editbutton(Sleep,"Sleep",sleepIcon);
		Buttons.add(Sleep);
		WakeUp = new JButton();
		editbutton(WakeUp,"Wake Up",wakeUpIcon);
		Buttons.add(WakeUp);
		
		food = new JButton();
		editbutton(food,"Food",foodIcon);
		Buttons.add(food);
		
		ChangeColor=new JButton();
		editbutton(ChangeColor,"Change Color",colorIcon);
		Buttons.add(ChangeColor);
		
		Save=new JButton();
		editbutton(Save,"Save",saveIcon);
		Buttons.add(Save);
		
		Restore=new JButton();
		editbutton(Restore,"Restore",restoreIcon);
		Buttons.add(Restore);
		
		
		info = new JButton();
		editbutton(info,"Info",infoIcon);
		Buttons.add(info);
		clear = new JButton();
		editbutton(clear,"Clear",clearIcon);
		Buttons.add(clear);
		exit = new JButton();
		editbutton(exit,"Exit",exitIcon);
		Buttons.add(exit);
		this.add(Buttons, BorderLayout.SOUTH);
		blockingQueue=new LinkedBlockingQueue<Runnable>(maxWaiting);
		TPE=new ThreadPoolExecutor(10,10,1,TimeUnit.HOURS,blockingQueue);
		zooPlace = new zooPlacePanel(animallist, foods);
		zooPlace.setLayout(null);
		
		// zooPlace.setBackground(pic1);
		zooPlace.setSize(800, 600);
		this.add(zooPlace, BorderLayout.CENTER);
		Sleep.addActionListener(this);
		WakeUp.addActionListener(this);
		addAnimal.addActionListener(this);
		Sleep.addActionListener(this);
		clear.addActionListener(this);
		food.addActionListener(this);
		info.addActionListener(this);
		exit.addActionListener(this);
		ChangeColor.addActionListener(this);
		Save.addActionListener(this);
		Restore.addActionListener(this);
		flagAwake=new AtomicBoolean();
		controllerX=new Controller(this);
		flagAwake.set(true);
		careTaker=new ZooMementoCareTaker(this);
		
		//controller.start();
	}
	/**
	 * singleton system of the zoopanel
	 * @return reference of the zoo panel if already exists if no build one
	 */
	public static ZooPanel get_instance() {
		if(ref==null) {
			synchronized(ZooPanel.class) 
			{
				if(ref==null) {
					ref=new ZooPanel();
				}			
			}
		}
		return ref;
	}

	/**
	 *action listener of the panel when clicking on a button it calls the right methods
	 *when pressing add calls the addanimal dialog
	 *when pressing move call the Sleep dialog
	 *clear reset the system
	 *food print food on the screen depending on what was selected
	 *info present a table with all the info of the system
	 *change color change the color of a define animal
	 *save create a save of the system at a given point
	 *restore the last save that was made
	 *exit close the system
	 */
	@Override
	public void actionPerformed(ActionEvent buttonsE) {
		if (buttonsE.getSource() == addAnimal) {
				if(blockingQueue.size()==maxWaiting) {
			        JOptionPane.showMessageDialog(null, "Can't add more animals","Field Error" ,JOptionPane.ERROR_MESSAGE);
			        return;
			 	}
				String[] options = { "Herbivore", "Omnivore", "Carnivore" };
				int x=-1;
				x = JOptionPane.showOptionDialog(null, "Please choose type of animal", "Type of animal",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,foodIcon, options,null);
				switch(x) {
				case 0:
					String[] typesHerb= {"Giraffe","Turtle","Elephant"};
					JFrame frame1 = new JFrame();				
					AddAnimalDialog dial1 = new AddAnimalDialog(frame1,animallist,TPE, this,flagAwake.get(),typesHerb,x);
					break;
				case 1:
					String[] typesOmn= {"Bear"};
					JFrame frame2 = new JFrame();				
					AddAnimalDialog dial2 = new AddAnimalDialog(frame2,animallist,TPE, this,flagAwake.get(),typesOmn,x);
					break;
				case 2:
					String[] typesCarn= {"Lion"};
					JFrame frame3 = new JFrame();				
					AddAnimalDialog dial3 = new AddAnimalDialog(frame3,animallist,TPE, this,flagAwake.get(),typesCarn,x);
					break;
				}
				
				repaint();
		}
//		if (buttonsE.getSource() == Sleep) {
//			if (animallist.size() == 0) {
//				JOptionPane.showMessageDialog(null, "The zoo is empty", "Zoo is Empty",
//						JOptionPane.INFORMATION_MESSAGE);
//			} else {
//				JFrame frame = new JFrame();
//				SleepDialog dial = new SleepDialog(frame, animallist);
//				manageZoo();
//			}
//		}
		if (buttonsE.getSource() == clear) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all the animals?", "Clear warning",
					JOptionPane.YES_NO_OPTION) == 0)
			{
				synchronized(this) {
					for(int i=0;i<animallist.size();i++)
					{
						animallist.get(i).setDead();
					}
					animallist.clear();
				}
				repaint();
			}
		}
		if (buttonsE.getSource() == food) {
			String[] options = { "Lettuce", "Cabbage", "Meat" };
			int x = JOptionPane.showOptionDialog(null, "Please choose food", "Food for animal",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,foodIcon, options,null);
				switch(x) {
				case 0:
					foods=Lettuce.get_instance();
					break;
				case 1:
					foods=Cabbage.get_instance();
					break;
				case 2:
					foods=Meat.get_instance();
					break;
				default:
					break;
				}
				if(x ==0 || x==1 || x==2)
				{
					zooPlace.addFood(foods);
					Animal.setFood(true);
					repaint();
				}
			
			//manageZoo();
			}

		if (buttonsE.getSource() == info) {
			JFrame infoframe = new JFrame();
			String[] titles = { "Name", "Animal", "Color", "Weight", "Hor.speed", "Ver.speed", "Eat counter" };

			String[][] data = new String[animallist.size() + 1][7];
			int sum = 0;
			JTable table = new JTable(animallist.size() + 2, 7);
			for (int i = 0; i < 7; i++) {
				table.setValueAt(titles[i], 0, i);
			}
			for (int i = 0; i < animallist.size(); i++) {
				table.setValueAt(animallist.get(i).getAnimalName(), i + 1, 0);
				table.setValueAt(animallist.get(i).getClass().getSimpleName(), i + 1, 1);
				table.setValueAt(animallist.get(i).getColor(), i + 1, 2);
				table.setValueAt(String.valueOf(animallist.get(i).getWeight()), i + 1, 3);
				table.setValueAt(String.valueOf(animallist.get(i).getHorSpeed()), i + 1, 4);
				table.setValueAt(String.valueOf(animallist.get(i).getVerSpeed()), i + 1, 5);
				table.setValueAt(String.valueOf(animallist.get(i).getEatCount()), i + 1, 6);
				sum += animallist.get(i).getEatCount();
			}

			table.setValueAt("Total", animallist.size() + 1, 0);
			table.setValueAt(String.valueOf(sum), animallist.size() + 1, 6);
			table.setBounds(30, 40, 400, 400);
			infoframe.add(table);
			infoframe.setSize(500, 200);
			infoframe.setVisible(true);

		}
		if (buttonsE.getSource() == exit) {
			for(int i=0;i<animallist.size();i++)
			{
				animallist.get(i).setDead();
			}
			System.exit(0);
		}
		if(buttonsE.getSource() == Sleep) {
			synchronized(this) {
				this.flagAwake.set(false);
			}
			for(int i=0;i<this.animallist.size();i++) {
				synchronized(this.animallist.get(i)){
				animallist.get(i).setSuspended();
				}
			}
			
		}
		if(buttonsE.getSource() == WakeUp) {
			synchronized(this) {
				this.flagAwake.set(true);
			}
			for(int i=0;i<this.animallist.size();i++) {
				synchronized(this.animallist.get(i)){
				animallist.get(i).setResumed();
				}
			}		
		}
		if(buttonsE.getSource() == ChangeColor)
		{
			if(animallist.size()==0)
			{
				JOptionPane.showMessageDialog(null, "There is no animal to repaint", "Zoo is empty",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				String[] Colors= {"Red","Blue","Natural"};
				JFrame ColorFrame=new JFrame();
				new ChangeColorDialog(ColorFrame,Colors,animallist);
			}
		}
		if(buttonsE.getSource() == Save)
		{
			careTaker.hitSave();
		}
		
		if(buttonsE.getSource() == Restore)
		{
			careTaker.hitUndo();
		}
	}

	/**
	 * changes the background of the screen to the image of the savanna
	 */
	public void changeToIMG() {
		try {
			BufferedImage savIMG = ImageIO.read(new File(IDrawable.PICTURE_PATH + "savanna.jpg"));
			this.zooPlace.setIMG(savIMG);
			this.zooPlace.repaint();
			this.color="IMG";
		} catch (IOException e) {
			/**/ }
	}

	/**
	 *  changes the background of the screen to the green color
	 */
	public void changeToGreen() {
		zooPlace.setIMG(null);
		zooPlace.setBackground(Color.GREEN);
		this.color="GREEN";
	}

	/**
	 * reset the background of the screen
	 */
	public void changeToNone() {
		zooPlace.setIMG(null);
		zooPlace.setBackground(null);
		this.color="NONE";
	}

	/**
	 * check if there were changes made to the frame and if yes print everything that changed
	 * check if an eating action is possible if yes the eat method is called and update the data of the animal
	 * if animal is dead or food is eaten - updates the food to null or list of animals 
	 */
	public void manageZoo() {
//			if (isChange())
//				repaint();	
		synchronized(this) {
				for (int i = 0; i < Math.min(animallist.size(),10); i++) {
					Animal pred=animallist.get(i);
					for (int j = 0; j < animallist.size(); j++) {
							if (i != j) {
								Animal prey=animallist.get(j);
								if (pred.calcDistance(prey.getLocation())<=prey.getSize()) {
									if (pred.getWeight() > (2 * prey.getWeight())) {
										if (pred.eat(prey)) {
											synchronized(animallist) {
											animallist.get(j).setDead();
											animallist.remove(j);
											}
											//repaint();
										}
									}
								}
							}
							
					}
						if (Math.abs(pred.getLocation().getX() - 400)<=10 && Math.abs(pred.getLocation().getY() - 300)<=10) {
							{
								
									if(foods!=null) {
										synchronized(foods) {
											if(foods!=null) {
												if (pred.eat(foods)) {									
													foods=null;
													Animal.setFood(false);
													zooPlace.addFood(foods);
												repaint();
												}
											}
										}
									}	
							}
						}
					}
						
				}
			
	}

	/**
	 * check if there were change made to any of the animals
	 * @return boolean 
	 */

//	public boolean isChange() {
//		for (int i = 0; i < animallist.size(); i++) {
//			if (animallist.get(i).getChanges())
//				animallist.get(i).setChanges(false);
//			return true;
//		}
//		return false;
//	}

	/**
	 * update the frame of the zoo 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		zooPlace.repaint();
	}
	
	/**
	 * change how the button looks 
	 * @param j the button the method needs to change
	 * @param txt the text of the button 
	 * @param icon the icon of the button 
	 */
	public static void editbutton(JButton j,String txt,ImageIcon icon) {
		j.setText(txt);
		j.setFocusable(false);
		j.setIcon(icon);
		j.setBorder(new RoundedBorder(10));
		j.setHorizontalTextPosition(JButton.CENTER);
		j.setVerticalTextPosition(JButton.BOTTOM);
		j.setFont(new Font("Comic Sans",Font.BOLD,12));
	}
	
	/**
	 * @return the food that is on the screen
	 */
	public IEdible getFood()
	{
		return foods;
	}
	
	/**
	 * allows us to get the controller of the panel
	 * @return controller of the pannel
	 */
	public Controller getcontroller() {
		return this.controllerX;
	}
	
	public String getColor() {return this.color;}

	
	/**
	 * allows us to return the lis of animals in the zoo
	 * @return arraylist of type animal that contains all the animal in the zoo
	 */
	public ArrayList<Animal> getAnimallist(){
		return animallist;
	}
	
	/**
	 * this method allows us to restore the save made by the memento 
	 * @param animals list of the animal in the saved state
	 * @param food food in the saved state
	 * @param color color of the screen in the saved state
	 */
	public void setAnimallistAndFoods(ArrayList<Animal> animals,IEdible food,String color)
	{		
		synchronized(this)
			{
				for(int i=0;i<animallist.size();i++)
				{
					animallist.get(i).setDead();
				}
				animallist.clear();
			foods=food;
			animallist=animals;
			if(food!=null)
				Animal.setFood(true);
			//zooPlace = new zooPlacePanel(animallist, foods);
			blockingQueue=new LinkedBlockingQueue<Runnable>(maxWaiting);
			TPE=new ThreadPoolExecutor(10,10,1,TimeUnit.HOURS,blockingQueue);
			for(int i=0;i<animallist.size();i++) {
				TPE.submit(animallist.get(i));
			}
			zooPlace.hide();
			zooPlace=null;
			zooPlace = new zooPlacePanel(animallist, foods);
			zooPlace.setLayout(null);		
			// zooPlace.setBackground(pic1);
			zooPlace.setSize(800, 600);
			this.add(zooPlace, BorderLayout.CENTER);
			switch (color) {
			case "NONE":
				this.changeToNone();
				break;
			case "GREEN":
				this.changeToGreen();
				break;
			case "IMG":
				this.changeToIMG();
				break;
			default:
				break;
			}
		}
	}
	//@Override
	/**
	 * runs the panel as long as the program runs,
	 * calls manageZoo to do all the checks and updates that are needed
	 * @see manageZoo
	 */
//	public void run() {
//		while(true) {
//			if(flagAwake.get()) {
//				manageZoo();
//			}
//		}
//	}
}
