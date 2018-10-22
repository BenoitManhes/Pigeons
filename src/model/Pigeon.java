package model;

import view.*;
import javafx.scene.image.ImageView;

public class Pigeon extends Element {

	public Pigeon() {
		super(new ImageView(Park.imagePigeon));
		this.X = (int) (Math.random()*(Parametre.WIDTH - Parametre.PIGEON_SIZE));
		this.Y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.PIGEON_SIZE));
		updateImageView();
	}
	
	public void move(int x, int y) {
		this.X += x;
		this.Y += y;
		System.out.println("X : "+this.X+"  | Y : "+this.Y);
		updateImageView();
	}

}
