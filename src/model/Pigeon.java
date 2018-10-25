package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pigeon extends Element implements Runnable {

	int x;
	int y;
	ArrayList<Food> allFood;
	Food cible;
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
			cible = null;
			if (!allFood.isEmpty()) {
				selectCible();
			}
			if (cible != null) {
				move();
			}
			try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	public void selectCible() {
		double distanceMin = Parametre.HEIGHT^2 + Parametre.WIDTH^2;

		for (int i = 0; i < allFood.size(); i++) {
			System.out.println(allFood.size());
			if (distance(allFood.get(i)) < distanceMin) {
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
}


