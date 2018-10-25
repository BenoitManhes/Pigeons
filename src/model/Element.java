package model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Element extends ImageView {

	public Element(Pane pane, int width, int height, Image imv) {
		this.setImage(imv);
		this.setFitHeight(height);
		this.setFitWidth(width);
		pane.getChildren().add(this);
	}

	public void setLocation(double x, double y){
		this.setX(x);
		this.setY(y);
	}

	public double distance(Element e) {
		double x = Math.abs(e.getX()-this.getX());
		double y = Math.abs(e.getY()-this.getY());
		return Math.sqrt(x*x + y*y);
	}

	public double distance(Element e,double a, double b) {
		double x = Math.abs(e.getX()-this.getX()+a);
		double y = Math.abs(e.getY()-this.getY()+b);
		return Math.sqrt(x*x + y*y);
	}
	
	public double distance(double xCible, double yCible) {
		double x = Math.abs(xCible-this.getX());
		double y = Math.abs(yCible-this.getY());
		return Math.sqrt(x*x + y*y);
	} 
}
