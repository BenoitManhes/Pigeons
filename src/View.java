import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Observable;
import java.util.Vector;

public class View extends Application {

	public static void main(String[] args) {

		launch(View.class, args);
	}

	private final int HEIGHT = Parametre.HEIGHT;
	private final int WIDTH = Parametre.WIDTH;
	public static Image imagePigeon;
	public static Image imagePark;
	public static Image imageFood;

	private Vector<Element> pigeonVect;
	private Vector<Element> foodVect;

	private Stage primaryStage;
	private Scene scene;
	private Group root;
	private Group pigeonsGroup;
	private Group foodGroup;

	@Override
	public void start(Stage primaryStage) {
		// gestion des images
		Class<?> clazz = this.getClass();

		InputStream inputPark = clazz.getResourceAsStream("/view/park.png");
		imagePark = new Image(inputPark,WIDTH,HEIGHT,false,true);
		ImageView imageParkView = new ImageView(imagePark);

		InputStream inputPigeon = clazz.getResourceAsStream("/view/pigeon.png");
		imagePigeon = new Image(inputPigeon,Parametre.PIGEON_SIZE,Parametre.PIGEON_SIZE,false,true);

		InputStream inputFood = clazz.getResourceAsStream("/view/food.png");
		imageFood = new Image(inputFood,Parametre.FOOD_SIZE,Parametre.FOOD_SIZE,false,true);



		this.root = new Group();
		this.root.getChildren().addAll(imageParkView);
		this.scene = new Scene(this.root, WIDTH, HEIGHT, Color.WHITE);

		init();
		addFood();
		this.root.getChildren().addAll(this.pigeonsGroup);
		this.root.getChildren().addAll(this.foodGroup);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Pigeon Square");
		this.primaryStage.setScene(this.scene);
		this.primaryStage.show();

	}


	public void init(){
		this.foodVect = new Vector<>();
		this.pigeonVect = new Vector<>();
		this.pigeonsGroup = new Group();
		this.foodGroup = new Group();

		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			Pigeon pi = new Pigeon();
			chooseID(pi, this.pigeonVect);
			this.pigeonVect.add(pi);
			this.pigeonsGroup.getChildren().addAll(pi.getImageView());
		}


	}

	public void addFood() {
		Food food = new Food();
		chooseID(food, this.foodVect);
		this.foodVect.add(food);
		this.foodGroup.getChildren().addAll(food.getImageView());
	}

	public static void chooseID(Element e, Vector<Element> v) {
		boolean ipTrouve = false;
		int ip = 0;
		if(!v.isEmpty()) {
			while(!ipTrouve) {
				ipTrouve = true;
				ip++;
				for (Element element : v) {
					if(element.getId() == ip) ipTrouve = false;
				}
				if(!ipTrouve) ip++;
			}
		}
		e.setId(ip);
	}


}