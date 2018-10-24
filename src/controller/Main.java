package controller;

import javafx.animation.AnimationTimer;
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
import java.util.Iterator;
import java.util.Vector;

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private final int HEIGHT = Parametre.HEIGHT;
	private final int WIDTH = Parametre.WIDTH;

	ArrayList<Food> allFood = new ArrayList<>();
	ArrayList<Pigeon> allPigeon = new ArrayList<>();
	ArrayList<Integer> index = new ArrayList<>();

	private Scene scene;
	private Pane pane;
	AnimationTimer gameLoop;

	@Override
	public void start(Stage primaryStage) {
		pane = new Pane();
		BackgroundImage imageParkView= new BackgroundImage(new Image("./view/park.png"),
	            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(imageParkView));
		scene = new Scene(this.pane, WIDTH, HEIGHT, Color.WHITE);

		addElement();

		primaryStage.setTitle("Pigeon Square");
		primaryStage.setScene(scene);
		primaryStage.show();

		startGame();
	}

	public void startGame() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Iterator<Food> iterator = allFood.iterator();

				while (iterator.hasNext()){
					Food food = iterator.next();
		            if(!food.getFresh()){
		                iterator.remove();
		            }
				}

				for (int i = 0; i < allFood.size(); i++) {
					if (allFood.get(i).getEaten() == true) {
						allFood.get(i).setFresh(false);
						pane.getChildren().remove(allFood.get(i));
					}
				}

				if (allFood.isEmpty()) {
					addFood();
				}
			}
		};
		gameLoop.start();
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
		Pigeon pigeon = new Pigeon(pane, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imagePigeon, allFood);
		allPigeon.add(pigeon);
		Thread threadPigeon = new Thread(pigeon);
		threadPigeon.start();
	}

	public void addFood() {
		Image imageFood = new Image("./view/food.png");
		Food food = new Food(pane, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imageFood);
		allFood.add(food);
		Thread threadFood = new Thread(food);
		threadFood.start();
	}

	/*public void deleteElement(ArrayList<Integer> index, ArrayList<Food> allFood) {
		index.sort();
		for (int i = 1; i < index.size(); i++) {
			if (index.get(i) > index.get(0)) {

			} else {
				allFood.remove(index.get(i));
			}
		}
	}*/

}