package graphics;

import java.util.Observable;
import java.util.Observer;

/**
 * controller of the zoo panel that calls the managezoo method when he get notified that there was change in the observable class
 * @author solal ohana elad sapir
 *
 */
public class Controller implements Observer{
	private ZooPanel ZPanel;

	/**
	 * constructor of the controller class that receive the zoo panel so that he can calls the managezoo method
	 * @param zPanel
	 */
	public Controller(ZooPanel zPanel) {
		ZPanel=zPanel;
	}
	/**
	 * update method that is called whenever the notifyobserver method is called.
	 */
	@Override
	public void update(Observable o, Object arg) {
		ZPanel.manageZoo();
	}

}
