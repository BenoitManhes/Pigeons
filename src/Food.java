import javafx.scene.image.ImageView;

public class Food extends Element{
	
	boolean fresh;

	public Food() {
		super(new ImageView(View.imageFood));
		this.X = (int) (Math.random()*(Parametre.WIDTH - Parametre.FOOD_SIZE));
		this.Y = (int) (Math.random()*(Parametre.HEIGHT - Parametre.FOOD_SIZE));
		this.fresh = true;
		updateImageView();
	}

}
