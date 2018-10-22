package threads;

import model.*;
import view.*;

public class ThreadPigeon extends Thread {

	Pigeon pigeon;
	Food cible;

	public ThreadPigeon(Pigeon pi) {
		this.pigeon = pi;
		cible = null;
	}

	public void run() {
		while(true) {
			selectCible();
			if(cible != null) {
				deplacement();
			}
			try {this.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
		}
	}


	/** =========================================== methode utilise dans run ==========================================*/
	public void selectCible() {
		if(Park.foodVect.isEmpty()) { cible = null;}
		else {
			double distanceMin = pigeon.distance(Park.foodVect.get(0));
			cible = Park.foodVect.get(0);
			for (Food f : Park.foodVect) {
				if(pigeon.distance(f) < distanceMin) {
					distanceMin = pigeon.distance(f);
					cible = f;
				}
			}
		}
	}

	public void deplacement() {
		double d = pigeon.distance(cible);

		if(d !=0) {
			if(d > Parametre.PIGEON_SPEED) {
				double cosAngle = (cible.getX() - pigeon.getX()) / d;
				double sinAngle = (cible.getY() - pigeon.getY()) / d;

				int x = (int) Math.round(Parametre.PIGEON_SPEED*cosAngle);
				int y = (int) Math.round(Parametre.PIGEON_SPEED*sinAngle);
				this.pigeon.move(x, y);
			}else {
				this.pigeon.move(cible.getX(), cible.getY());
				System.out.println("================= cible atteinte ============");
			}
		}
	}
}

