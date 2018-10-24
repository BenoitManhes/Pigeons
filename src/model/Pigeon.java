package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pigeon extends Element implements Runnable {

	int x;
	int y;
	ArrayList<Food> allFood;
	Food cible = null;
	Pane pane;

	public Pigeon(Pane pane, int width, int height, Image img, ArrayList<Food> allFood) {
		super(pane, width, height, img);
		this.x = (int) (Math.random()*(Parametre.WIDTH - Parametre.PIGEON_SIZE));
		this.y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.PIGEON_SIZE));
		this.setLocation(x, y);
		this.allFood = allFood;
		this.pane = pane;
	}

	public void run() {
		while(true) {
			if (!allFood.isEmpty()) {
				selectCible();
				if (!move()) {
					cible.setEaten(true);
				}
			}
			try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	public void selectCible() {
		double distanceMin = distance(allFood.get(0));
		cible = allFood.get(0);

		for (Food food : allFood) {
			if (distance(food) < distanceMin && food.getEaten() == false) {
				distanceMin = distance(food);
				cible = food;
			}
		}
	}

	public boolean move() {
		double d = this.distance(cible);

		if (d != 0) {
			if (d > Parametre.PIGEON_SPEED) {
				double cosAngle = (cible.getX() - getX()) / d;
				double sinAngle = (cible.getY() - getY()) / d;

				int x = (int) Math.round(Parametre.PIGEON_SPEED * cosAngle);
				int y = (int) Math.round(Parametre.PIGEON_SPEED * sinAngle);

				setLocation(this.getX() + x, this.getY() + y);

				return true;
			} else {
				setLocation(cible.getX(), cible.getY());
				System.out.println("je mange");
				return false;
			}
		} else {
			System.out.println("je mange");
			return false;
		}
	}
}


