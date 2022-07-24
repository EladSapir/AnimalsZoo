package graphics;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import AnimalColors.PaintBlue;
import AnimalColors.PaintNatural;
import AnimalColors.PaintRed;
import animals.Animal;

/**
 * create the dialog windows to change the color of the animal
 * @author solal ohana elad sapir
 *
 */
public class ChangeColorDialog extends JDialog implements ActionListener,KeyListener {
	
	JComboBox Colorbox;
	JComboBox AnimalNames;
	JButton submitbutton;
	ArrayList <Animal> tempAnimals;
	
	/**
	 * create the dialog frame to let the user choose of what animal the color needs to change
	 * @param frame the frame that will be used for the dialog
	 * @param colors the list of colors that the animals can have
	 * @param animallist the lisrt of animals
	 */
	ChangeColorDialog(Frame frame,String[] colors, ArrayList <Animal> animallist){
		super(frame, "Change Color",true);
		this.setLayout(new GridLayout(3,1));
		this.setSize(150,150);
		Colorbox=new JComboBox(colors);
		tempAnimals=animallist;
		String[] animalNames= new String[animallist.size()];
		for (int i=0;i<animallist.size();i++) {
			animalNames[i]=animallist.get(i).getName();
		}
		AnimalNames=new JComboBox(animalNames);
		submitbutton=new JButton("OK");
		submitbutton.addActionListener(this);
		this.add(AnimalNames);
		this.add(Colorbox);
		this.add(submitbutton);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * send the colors to the decorator with what animal that needs to be changed
	 */
	private void submitColor() {
		String color=Colorbox.getSelectedItem().toString();
		int index=AnimalNames.getSelectedIndex();
		tempAnimals.get(index);
		if(color=="Red") {
			new PaintRed(tempAnimals.get(index));
		}
		if(color=="Natural") {
			new PaintNatural(tempAnimals.get(index));
		}
		if(color=="Blue") {
			new PaintBlue(tempAnimals.get(index));
		}
		this.dispose();
	}
	
	/**
	 * action listener of the dialog
	 */
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource()==submitbutton) {
			submitColor();
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
			submitColor();
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
