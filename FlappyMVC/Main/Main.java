package Main;
import java.util.Random;
import Modele.*;
import Vue.*;
import Controleur.*;

public class Main {
	public static Random rand = new Random();
	public static int score = 0; //J'ai �t� oblig� de le sortir de PJeu sinon il fait n'imorte quoi
	
	public static void main(String[] args) {
		try {
			Fenetre frame = new Fenetre();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main2(String[] args) {
		int delay = 15;
		// Generation du Jeu (etat initial)
		Jeu jeu = new Jeu();
		boolean saut = true;
		Checker checker = new Checker();
		
		// Generation de la fenetre
		Fenetre window = null;
		try {
			window = new Fenetre();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// boucle de jeu
		while(!jeu.end()) {
			// update du modele
			jeu.update(saut);
			// update de l'affichage 
			(window.getPjeu()).repaint();
			// update du controleur
			saut = checker.getJump();
			
			// 
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}