package model;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Food extends Element implements Runnable {

	int x;
	int y;
	boolean fresh;

	public Food(Pane pane, int width, int height, Image img) {
		super(pane, width, height, img);
		this.x = (int) (Math.random()*(Parametre.WIDTH - Parametre.FOOD_SIZE));
		this.y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.FOOD_SIZE));
		this.setLocation(x, y);
		this.fresh = true;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(16);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
