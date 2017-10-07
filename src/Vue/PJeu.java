package Vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Bird;
import Modele.Jeu;
import Modele.Obstacle;
import Main.Main;

public class PJeu extends JPanel implements KeyListener {
	
	private Bird bird;
	private ArrayList<Obstacle> obstacles;
	private float tolerance; // also amounts to shift necessary for ignoring the tail
	private static int score;
	private static JLabel labScore;
	private BufferedImage imBirdDown;
	private BufferedImage imBirdUp;
	private Image background;
	
	public PJeu(int dimx, int dimy, Jeu jeu) {
		this.setSize(new Dimension(dimx,dimy));	
	
		//Creation des composants
		bird = jeu.getBird();
		obstacles = jeu.getObstacles();
		tolerance = jeu.getTolerance();
		
		//Images
		Image imBirdTempDown = null;
		Image imBirdTempUp = null;
		try {
			imBirdTempDown = ImageIO.read(this.getClass().getResource("ressources/whaleDown.png"));
			imBirdTempDown = imBirdTempDown.getScaledInstance(bird.getSize(), bird.getSize(), Image.SCALE_DEFAULT);
			imBirdTempUp = ImageIO.read(this.getClass().getResource("ressources/whaleUp.png"));
			imBirdTempUp = imBirdTempUp.getScaledInstance(bird.getSize(), bird.getSize(), Image.SCALE_DEFAULT);
		}catch (IOException e) {
			System.out.println("Erreur lecture fichier bird");
			e.printStackTrace();
		}
		
		int mask = 0xFFFFF000;
		
		imBirdDown = new BufferedImage(imBirdTempDown.getWidth(null),imBirdTempDown.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		imBirdDown.getGraphics().drawImage(imBirdTempDown, 0, 0 , null);
		imBirdDown = createColorImage(imBirdDown,mask);
		
		imBirdUp = new BufferedImage(imBirdTempUp.getWidth(null),imBirdTempUp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		imBirdUp.getGraphics().drawImage(imBirdTempUp, 0, 0 , null);
		imBirdUp = createColorImage(imBirdUp,mask);
		
		try {
			background = ImageIO.read(this.getClass().getResource("ressources/background.png"));
			background = background.getScaledInstance(Fenetre.DIMX, Fenetre.DIMY, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		//Score
		score = 0;
		labScore = new JLabel();
		labScore.setText(String.valueOf(score));
		labScore.setForeground(Color.red);
		Font f = new Font("Serif", Font.PLAIN, 36);
		labScore.setFont(f);
		this.add(labScore);
	}
	
	
	
	public void paintComponent(Graphics g) {
		   
		Graphics2D g2d = (Graphics2D) g;
		//Recouvrement de la fenetre avec la couleur de fond afin d'effacer ce qui est present
		g2d.drawImage(background, 0, 0, this);
		
		/// Bird
		if (bird.getSpeed() < 0) {
			g2d.drawImage(imBirdDown, bird.getPosX()-(int)((1+tolerance)*bird.getSize()/2), bird.getPosY()-bird.getSize()/2,this);		
		} else {
			g2d.drawImage(imBirdUp, bird.getPosX()-(int)((1+tolerance)*bird.getSize()/2), bird.getPosY()-bird.getSize()/2,this);
		}
		/// Obstacles
		g2d.setColor(Color.green);
		for(int i=0; i<obstacles.size();i++) {
			g2d.fillRect(obstacles.get(i).getPosX(),obstacles.get(i).getPosObstBas(), Obstacle.LARGEUR,this.getHeight()-obstacles.get(i).getPosObstBas());		
			g2d.fillRect(obstacles.get(i).getPosX(),0, Obstacle.LARGEUR,obstacles.get(i).getPosObstHaut());		
		}
		
	}
	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 32) { //Appuie sur la barre d'espace
        	bird.up();
        }
    }

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
    private BufferedImage createColorImage(BufferedImage originalImage, int mask) {
        BufferedImage colorImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(), originalImage.getType());

        for (int x = 0; x < originalImage.getWidth(); x++) {
            for (int y = 0; y < originalImage.getHeight(); y++) {
                int pixel = originalImage.getRGB(x, y) & mask;
                colorImage.setRGB(x, y, pixel);
            }
        }

        return colorImage;
    }
}
