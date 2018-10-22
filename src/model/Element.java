package model;
import javafx.scene.image.ImageView;

public abstract class Element {
	
	protected int X;
	protected int Y;
	protected ImageView imageView;
	protected int id;

	public Element(ImageView imv) {
		this.imageView = imv;
	}
	
	public Element(int x, int y, ImageView imv) {
		this.X = x;
		this.Y = y;
		this.imageView = imv;
		updateImageView();
	}
	

	public void updateImageView(){
		this.imageView.setX(this.X);
		this.imageView.setY(this.Y);
	}
	
	public double distance(Element e) {
		double x = Math.abs(e.getX()-this.getX());
		double y = Math.abs(e.getY()-this.getY());
		return Math.sqrt(x*x + y*y);
	}
	
	/**======================================= getter et setter =============================================================*/
	public int getX() {return X;}

	public void setX(int x) {
		X = x;
		updateImageView();
	}

	public int getY() {return Y;}

	public void setY(int y) {
		Y = y;
		updateImageView();
	}

	public void setImageView(ImageView imv){ this.imageView = imv;}

	public ImageView getImageView() {
		return imageView;
	}
	public int getId() {return id;}

	public void setId(int id) { this.id = id;}
	

}
