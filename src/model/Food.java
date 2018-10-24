package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Food extends Element implements Runnable {

	int x;
	int y;
	boolean fresh;
	boolean eaten;
	Image imageOldFood = new Image("./view/oldFood.png");

	public Food(Pane pane, int width, int height, Image img) {
		super(pane, width, height, img);
		this.x = (int) (Math.random()*(Parametre.WIDTH - Parametre.FOOD_SIZE));
		this.y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.FOOD_SIZE));
		this.setLocation(x, y);
		this.fresh = true;
		this.eaten = false;
	}

	public void run() {
		while(fresh) {
			try {
				Thread.sleep(Parametre.FOOD_TIME);
				fresh = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.setImage(imageOldFood);
		System.out.println("nourriture avariée");
	}

	public boolean getFresh() {
		return this.fresh;
	}

	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}

	public boolean getEaten() {
		return this.eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
}
