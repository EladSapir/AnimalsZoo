//Elad Sapir - 209479948, Solal Ohana - 336410055, SCE Ashdod

package graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import animals.Animal;

/**
 * class that prints the main frame of the program
 * @author elad sapid
 * @version 1.0 28/04/22
 *
 */
public class ZooFrame extends JFrame implements ActionListener,KeyListener{
	
	JMenuBar menubar;
	JMenu filemenu;
	JMenu backgroundmenu;
	JMenu helpmenu;
	JMenuItem exititem;
	JMenuItem imageitem;
	JMenuItem greenitem;
	JMenuItem noneitem;
	JMenuItem helpitem;
	ZooPanel zoo;
	BufferedImage iconImg;
	
	/**
	 * constructor of the zoo frame which is the main frame of the program
	 * and calls all the main function of the program 
	 */
	ZooFrame()
	{
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,700);
		this.setTitle("Zoo");
		this.addKeyListener(this);
		this.setResizable(false);
		try {
			iconImg =ImageIO.read(new File(Animal.PICTURE_PATH+"animalsIcon.png"));
		} catch (IOException ignored) {}
		menubar = new JMenuBar();
		filemenu =new JMenu("File");
		filemenu.setFont(new Font("Comic Sans",Font.BOLD,14));
		backgroundmenu =new JMenu("Background");
		backgroundmenu.setFont(new Font("Comic Sans",Font.BOLD,14));
		helpmenu =new JMenu("Help");
		helpmenu.setFont(new Font("Comic Sans",Font.BOLD,14));
		exititem = new JMenuItem("Exit");
		exititem.setFont(new Font("Comic Sans",Font.BOLD,14));
		imageitem =new JMenuItem("Image");
		imageitem.setFont(new Font("Comic Sans",Font.BOLD,14));
		greenitem =new JMenuItem("Green");
		greenitem.setFont(new Font("Comic Sans",Font.BOLD,14));
		noneitem =new JMenuItem("None");
		noneitem.setFont(new Font("Comic Sans",Font.BOLD,14));
		helpitem =new JMenuItem("Help");
		helpitem.setFont(new Font("Comic Sans",Font.BOLD,14));
		
		exititem.addActionListener(this);
		exititem.addKeyListener(this);
		imageitem.addActionListener(this);
		greenitem.addActionListener(this);
		noneitem.addActionListener(this);
		helpitem.addActionListener(this);
		filemenu.add(exititem);
		backgroundmenu.add(imageitem);
		backgroundmenu.add(greenitem);
		backgroundmenu.add(noneitem);
		helpmenu.add(helpitem);
		
		menubar.add(filemenu);
		menubar.add(backgroundmenu);
		menubar.add(helpmenu);
		
		zoo=ZooPanel.get_instance();
		this.add(zoo);
		this.setIconImage(iconImg);
		this.setJMenuBar(menubar);
		this.setVisible(true);
	}
	
	/**
	 * main of the system call the creation of the frame
	 * @param args
	 */
	public static void main(String[] args) {
		ZooFrame tempframe= new ZooFrame();
	}

	/**
	 * action listener of the frame 
	 * calls the different function of the program depending on which button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent actmenubar) {
		if(actmenubar.getSource()==exititem)
		{
			System.exit(0);
		}
		if(actmenubar.getSource()==imageitem)
		{
			zoo.changeToIMG();
		}
		if(actmenubar.getSource()==greenitem)
		{
			zoo.changeToGreen();
		}
		if(actmenubar.getSource()==noneitem)
		{
			zoo.changeToNone();
		}
		if(actmenubar.getSource()==helpitem)
		{
		JOptionPane.showMessageDialog(null,"Home Work 2\nGUI", "Message", JOptionPane.PLAIN_MESSAGE,new ImageIcon("infoIMG.png"));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * key listener of the system close the system when the escape key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
