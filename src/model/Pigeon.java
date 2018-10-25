package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pigeon extends Element implements Runnable {

	int x;
	int y;
	ArrayList<Food> allFood;
	Food cible;
	Pane pane;
	int iterationFeared;
	int foodStock;

	public Pigeon(Pane pane, int width, int height, Image img, ArrayList<Food> allFood) {
		super(pane, width, height, img);
		this.x = (int) (Math.random()*(Parametre.WIDTH - Parametre.PIGEON_SIZE));
		this.y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.PIGEON_SIZE));
		this.setLocation(x, y);
		this.allFood = allFood;
		this.pane = pane;
		this.foodStock = 0;
	}

	public Pigeon(Pane pane, double x, double y, int width, int height, Image img, ArrayList<Food> allFood) {
		super(pane, width, height, img);
		this.setLocation(x, y);
		this.allFood = allFood;
		this.pane = pane;
		this.foodStock = 0;
	}

	public void run() {
		while(true) {
			cible = null;
			if (!allFood.isEmpty()) {
				selectCible();
			}
			if (iterationFeared <= 0 && cible != null) {
				move();
				if (this.getX() == cible.getX() && this.getY() == cible.getY() && cible.getEaten() == false) {
					eat(cible);
					foodStock++;
				}
			} else if(iterationFeared == 1) {
				Image imv = new Image("./view/pigeon.png");
				setImage(imv);
				iterationFeared--;
			} else if(iterationFeared > 0) {
				fear();
				iterationFeared--;
			}
			try {Thread.sleep(Parametre.PIGEON_DELAY);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	public void selectCible() {
		double distanceMin = Parametre.HEIGHT^2 + Parametre.WIDTH^2;

		for (int i = 0; i < allFood.size(); i++) {
			//System.out.println(allFood.size());
			if (distance(allFood.get(i)) < distanceMin && allFood.get(i).getFresh()) {
				distanceMin = distance(allFood.get(i));
				cible = allFood.get(i);
			}
		}
	}

	public void move() {
		double d = this.distance(cible);

		if (d != 0) {
			if (d > Parametre.PIGEON_SPEED) {
				double cosAngle = (cible.getX() - getX()) / d;
				double sinAngle = (cible.getY() - getY()) / d;

				x = (int) Math.round(Parametre.PIGEON_SPEED * cosAngle);
				y = (int) Math.round(Parametre.PIGEON_SPEED * sinAngle);
				setLocation(this.getX() + x, this.getY() + y);
			} else {
				setLocation(cible.getX(), cible.getY());
			}
		}
		//System.out.println(this.getX() + " / " + this.getY());
	}

	public void eat(Food cible) {
		boolean tryLock = cible.lock.tryLock();
		if(tryLock) {
			cible.setEaten(true);
			System.out.println("pigeon " + Thread.currentThread().getId() + " eat the food");
		}
	}

	public void fear() {
		//Move the pigeon randomly
		Random rdm = new Random();

		x = (int) Math.round(Parametre.PIGEON_SPEED * (rdm.nextDouble()*10 - 5));
		y = (int) Math.round(Parametre.PIGEON_SPEED * (rdm.nextDouble()*10 - 5));
		setLocation(this.getX() + x, this.getY() + y);
	}

	public void setFear() {
		Image imv = new Image("./view/pigeonFeared.png");
		setImage(imv);
		this.iterationFeared = 100;
	}

	public int getFoodStock() {
		return this.foodStock;
	}
}


