package model;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Food extends Element implements Runnable {

	int x;
	int y;
	boolean fresh;
	boolean aDetruire;
	boolean eaten;
	Lock lock = new ReentrantLock();

	Image imageOldFood = new Image("./view/oldFood.png");

	public Food(Pane pane, int width, int height, Image img) {
		super(pane, width, height, img);
		this.x = (int) (Math.random()*(Parametre.WIDTH - Parametre.FOOD_SIZE));
		this.y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.FOOD_SIZE));
		this.setLocation(x, y);
		this.fresh = true;
		this.aDetruire = false;
		this.eaten = false;
	}

	public Food(Pane pane, double x, double y, int width, int height, Image img) {
		super(pane, width, height, img);
		this.setLocation(x, y);
		this.fresh = true;
		this.aDetruire = false;
		this.eaten = false;
	}

	public void finalize()
    {
         //System.out.println("Objet nettoyé de la mémoire");
    }

	public void run() {
		//while(fresh) {
			try {
				Thread.sleep(Parametre.FOOD_TIME);
				fresh = false;
				this.setImage(imageOldFood);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(Parametre.FOOD_DESTROY_DELAY);
				this.aDetruire = true;
			} catch (Exception e ) {
				e.printStackTrace();
			}
		//}
		//System.out.println("nourriture avariée");
	}

	public boolean checkEaten(ArrayList<Pigeon> allPigeon) {
		for (int i = 0; i < allPigeon.size(); i++) {
			double d = this.distance(allPigeon.get(i));

			if (d == 0 || d < Parametre.PIGEON_SPEED) {
				return true;
			}
		}
		return false;
	}

	public boolean getFresh() {
		return this.fresh;
	}

	public void setFresh(boolean eaten) {
		this.eaten = eaten;
	}

	public boolean getEaten() {
		return this.eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public boolean isaDetruire() {
		return aDetruire;
	}
}
