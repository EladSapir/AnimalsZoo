//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import animals.*;
/**
 * class that opens a dialog window to add an animal to the system
 * @version 1.0 28/04/22
 * @author Solal ohana
 *
 */
public class AddAnimalDialog extends JDialog implements ActionListener,KeyListener{
	private String animalType;
	private int size;
	private int horSpeed;
	private int verSpeed;
	private String color;
	private String AnimalN;
	JComboBox typeBox;
	JTextField sizefield;
	JTextField AnimalName;
	JTextField XSpeedfield;
	JTextField YSpeedfield;
	JComboBox Colorbox;
	JButton submitbutton;
	ArrayList<Animal> animals;
	ExecutorService animalsPool;
	private String[] Types;
	private static String[] AnimColors = {"Natural","Red","Blue"};
	ZooPanel tempzoopanel;
	BufferedImage iconIMG;
	boolean moveFlag;
	abstractFactoryAnimal factoryAnimal;
	String choiceDiet;
	/**
	 * Constructor of the addanimaldialog 
	 * @param Window the new dialog frame that the class will use
	 * @param animlist the list of the animals that already exists in the system 
	 * @param zoo the panel on which the zoo is appeared
	 */
 	AddAnimalDialog(Frame Window,ArrayList<Animal> Animals,ExecutorService animPool,ZooPanel zoo,boolean flag1,String[] types,int choice){
		super(Window, "Add Animal",true);
		tempzoopanel=zoo;
		animalsPool=animPool;
		animals=Animals;
		Types=types;
 		typeBox=new JComboBox(Types);
 		submitbutton = new JButton("Submit");
 		submitbutton.addActionListener(this);
 		AnimalName= new JTextField();
 		sizefield= new JTextField();
 		XSpeedfield=new JTextField();
 		YSpeedfield=new JTextField();
 		Colorbox=new JComboBox(AnimColors);
 		factoryAnimal=new abstractFactoryAnimal();
 		moveFlag=flag1;
 		switch(choice) {
 		case 0:
 			choiceDiet="Herbivore";
 			break;
 		case 1:
 			choiceDiet="Omnivore";
 			break;
 		case 2:
 			choiceDiet="Carnivore";
 			break;
 		}
 		try {
			iconIMG =ImageIO.read(new File(Animal.PICTURE_PATH+"addAnimal.jpg"));
		} catch (IOException ignored) {}
 		
		this.setLayout(new GridLayout(7,2));	
		this.add(new JLabel("Name:"));
		this.add(AnimalName);
		this.add(new JLabel("Type:"));
		this.add(typeBox);
		this.add(new JLabel("Size (50-300):"));
		this.add(sizefield);
		this.add(new JLabel("Horizontal speed (1-10):"));
		this.add(XSpeedfield);
		this.add(new JLabel("Vertical speed (1-10):"));
		this.add(YSpeedfield);
		this.add(new JLabel("Color:"));	
		this.add(Colorbox);
		this.add(new JLabel());
		this.add(submitbutton);
		this.addKeyListener(this);
		this.getRootPane().setDefaultButton(submitbutton);	
		this.setIconImage(iconIMG);
		this.setSize(350,250);
		this.setResizable(false);
		this.setVisible(true);
	}

 	/**
 	 * Check if all the inputs from the user is in the range required from the system (if not throwing exception)
 	 * When all the inputs are correct creates the animal asked by the user and adds it the to the system
 	 * through the animal list
 	 */
 	private void submitAnimal() {		
 		String Tempsize,TempX,TempY,Name;
		StringBuilder Messages=new StringBuilder();
 		Tempsize=sizefield.getText();
		TempX=XSpeedfield.getText();
		TempY=YSpeedfield.getText();
		Name=AnimalName.getText();
		Messages.append("");
		 try{
			 	System.out.println(Name);
			 	boolean flag=false;
			 	if(Name.equals(""))
	            {
	            	Messages.append("\nYou must enter a name");
	            	flag=true;
	            }
	            if(flag==true) {
	            	String msg= Messages.toString();
	            	throw new Exception(msg);
	            }
	            Integer intsize = Integer.valueOf(Tempsize);
	            Integer intX = Integer.valueOf(TempX);
	            Integer intY = Integer.valueOf(TempY);
	            
	            if(!(intsize>=50 && intsize<=300)) {
	            	Messages.append("\nThe size must be between 50-300");
	            	flag=true;
	            }
	            if(!(intX>=1 && intX<=10)) {
	            	Messages.append("\nThe horizontal speed must be between 1-10");
	            	flag=true;
	            }
	            if(!(intY>=1 && intY<=10)) {
	            	Messages.append("\nThe vertical speed must be between 1-10");
	            	flag=true;
	            } 
	            if(flag==true) {
	            	String msg= Messages.toString();
	            	throw new Exception(msg);
	            }
	            else {
	            	double weight=0;
	            	this.animalType=typeBox.getSelectedItem().toString();
	            	this.size=intsize;
	            	this.horSpeed=intX;
	            	this.verSpeed=intY;
	            	this.AnimalN=Name;
	            	this.color=Colorbox.getSelectedItem().toString();
	            	Animal a=factoryAnimal.getAnimal(choiceDiet, animalType, AnimalN, null, horSpeed, verSpeed, color, size, moveFlag, tempzoopanel,tempzoopanel.getcontroller());	            	
	            	animalsPool.submit(a);
            		animals.add(a);          	
	            	tempzoopanel.manageZoo();
	            	this.dispose();           	
	            }
	        }
	        catch (NumberFormatException ex){
	        	JOptionPane.showMessageDialog(null, "ERROR - enter an integer number","Field Error" ,JOptionPane.ERROR_MESSAGE);
	        }

		 	catch(Exception E) {
		 		JOptionPane.showMessageDialog(null, E.getMessage(),"Field Error" ,JOptionPane.ERROR_MESSAGE );
		 	}
 	}
 	
 	/**
 	 * actionlistener of the submit button of the window
 	 * calls sumbit animal when the submit button is pressed
 	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource()==submitbutton) {
			submitAnimal();
		}
			
	}

	/**
	 * keylistener of the enter key and escape key 
	 * when the enter key is pressed the submit method is called
	 * when the escape key is pressed close the window
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			submitAnimal();
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			this.dispose();
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
