package Controleur;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
	
public class Checker extends JPanel implements KeyListener {
	private static boolean jump;
	
	// Constructor
	public Checker() {
		jump = false;
		this.addKeyListener(this);
		this.setFocusable(true);
	}
	
	// warning ! not a real get() ! (also sets jump at false)
	public boolean getJump() {
		boolean save = jump;
		if (jump) {System.out.println("jump ! getJump");}
		jump = false;
		return save;
	}

	// methods (key listening)
	public void keyPressed(KeyEvent e) {
	    if(e.getKeyCode() == 32) { //Appuie sur la barre d'espace
	    	jump = true;
	    	System.out.println("jump ! keyPressed");
	    }
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
