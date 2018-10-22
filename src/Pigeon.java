import javafx.scene.image.ImageView;

public class Pigeon extends Element {

	public Pigeon() {
		super(new ImageView(View.imagePigeon));
		this.X = (int) (Math.random()*(Parametre.WIDTH - Parametre.PIGEON_SIZE));
		this.Y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.PIGEON_SIZE));
		updateImageView();
	}

}
