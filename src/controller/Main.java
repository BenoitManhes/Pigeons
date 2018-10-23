package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Food;
import model.Parametre;
import model.Pigeon;

import java.util.ArrayList;
import java.util.Vector;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private final int HEIGHT = Parametre.HEIGHT;
	private final int WIDTH = Parametre.WIDTH;
	//public static Image imagePigeon;
	//public static Image imagePark;
	//public static Image imageFood;

	//public static Vector<Pigeon> pigeonVect;
	//public static Vector<Food> foodVect;

	ArrayList<Food> allFood = new ArrayList<>();
	ArrayList<Pigeon> allPigeon = new ArrayList<>();

	private Scene scene;
	private Pane root;
	/*private Group pigeonsGroup;
	private Group foodGroup;*/

	@Override
	public void start(Stage primaryStage) {
		//gestion des images
		//Class<?> clazz = this.getClass();

		//InputStream inputPark = clazz.getResourceAsStream("/view/park.png");
		//imagePark = new Image(inputPark,WIDTH,HEIGHT,false,true);
		//ImageView imageParkView = new ImageView(imagePark);

		//InputStream inputPigeon = clazz.getResourceAsStream("/view/pigeon.png");
		//imagePigeon = new Image(inputPigeon,Parametre.PIGEON_SIZE,Parametre.PIGEON_SIZE,false,true);

		//InputStream inputFood = clazz.getResourceAsStream("/view/food.png");
		//imageFood = new Image(inputFood,Parametre.FOOD_SIZE,Parametre.FOOD_SIZE,false,true);

		root = new Pane();
		BackgroundImage imageParkView= new BackgroundImage(new Image("./view/park.png"),
	            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	    root.setBackground(new Background(imageParkView));
		scene = new Scene(this.root, WIDTH, HEIGHT, Color.WHITE);

		addElement();

		/*this.root.getChildren().addAll(this.pigeonsGroup);
		this.root.getChildren().addAll(this.foodGroup);*/

		/*Image imagePigeon = new Image("./view/pigeon.png");
		Pigeon pigeon = new Pigeon(root, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imagePigeon);
		allPigeon.add(pigeon);*/

		primaryStage.setTitle("Pigeon Square");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void addElement(){
		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			addFood();
		}

		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			addPigeon();
		}
	}

	public void addPigeon() {
		Image imagePigeon = new Image("./view/pigeon.png");
		Pigeon pigeon = new Pigeon(root, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imagePigeon, allFood);
		allPigeon.add(pigeon);
		Thread threadPigeon = new Thread(pigeon);
		threadPigeon.start();
	}

	public void addFood() {
		Image imageFood = new Image("./view/food.png");
		Food food = new Food(root, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imageFood);
		allFood.add(food);
		Thread threadFood = new Thread(food);
		threadFood.start();
	}

}