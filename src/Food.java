import javafx.scene.image.ImageView;

public class Food extends Element{
	
	boolean fresh;

	public Food(int x, int y) {
		super(x,y,new ImageView());
		this.fresh = true;
	}

}
