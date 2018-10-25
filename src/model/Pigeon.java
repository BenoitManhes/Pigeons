package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Pigeon extends Element implements Runnable {

	double x;
	double y;
	ArrayList<Food> allFood;
	Food cible;
	double[] cibleFear;
	Pane pane;
	boolean fear;

	public Pigeon(Pane pane, int width, int height, Image img, ArrayList<Food> allFood) {
		super(pane, width, height, img);
		this.x =  (Math.random()*(Parametre.WIDTH - Parametre.PIGEON_SIZE));
		this.y =  (Math.random()*(Parametre.HEIGHT - Parametre.PIGEON_SIZE));
		this.setLocation(x, y);
		this.allFood = allFood;
		this.pane = pane;
		this.fear = false;
		this.cibleFear = new double[2];
	}

	public void run() {
		while(true) {
			if(this.fear) {
				move(cibleFear[0], cibleFear[1], Parametre.PIGEON_SPEED*1.5);
				checkCibleFear();
			}else {
				cible = null;
				if (!allFood.isEmpty()) {
					selectCible();
				}
				if (cible != null) {
					move(cible.getX(), cible.getY(), Parametre.PIGEON_SPEED);
				}
				
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

	public void move(double xCible, double yCible, double speed) {
		double d = this.distance(xCible, yCible);

		if (d != 0) {
			if (d > speed) {
				double cosAngle = (xCible - getX()) / d;
				double sinAngle = (yCible - getY()) / d;

				double x =  Math.round(speed * cosAngle);
				double y =  Math.round(speed * sinAngle);
				setLocation(this.getX() + x, this.getY() + y);
			} else {
				setLocation(xCible, yCible);
			}
		}
		//System.out.println(this.getX() + " / " + this.getY());
	}
	
	// check si le pigeon a atteint la cible de fuite
	public void checkCibleFear() {
		if(this.getX() == cibleFear[0] && this.getY() == cibleFear[1]) {
			this.fear =false;
			Image imv = new Image("./view/pigeon.png");
			setImage(imv);
		}
	}

	public void setFear(double xClick, double yClick) {
		Image imv = new Image("./view/pigeonFeared.png");
		setImage(imv);
		this.fear = true;
		this.setFearPosition(xClick, yClick);
	}

	public void setFearPosition(double xClick, double yClick) {
		// calcul orientation a l opposé de l element effrayant
		double SignDeltaX = (this.x - xClick)/Math.abs(this.x - xClick);
		double SignDeltaY = (this.y - yClick)/Math.abs(this.y - yClick);
		
		// calcul des coordonnes du point de fuite de maniere aleatoire mais oppose a l element effrayant 
		int deltaD = Parametre.DISTANCE_FEAR_MAX-Parametre.DISTANCE_FEAR_MIN;
		double xFear = this.getX() + (Math.random()*(deltaD) + Parametre.DISTANCE_FEAR_MIN)*SignDeltaX;
		double yFear = this.getY() + (Math.random()*(deltaD) + Parametre.DISTANCE_FEAR_MIN)*SignDeltaY;
		
		// encadrement des coordonnes de fuite pour eviter de sortir du parc
		xFear = Math.max(0, xFear); xFear = Math.min(Parametre.width-Parametre.PIGEON_SIZE, xFear);
		yFear = Math.max(0, yFear); yFear = Math.min(Parametre.height-Parametre.PIGEON_SIZE, yFear);
		
		this.cibleFear[0] = xFear;
		this.cibleFear[1] = yFear;
	}
}


