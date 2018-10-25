package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

public class Main extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private final int HEIGHT = Parametre.HEIGHT;
	private final int WIDTH = Parametre.WIDTH;

	ArrayList<Food> allFood = new ArrayList<>();
	ArrayList<Pigeon> allPigeon = new ArrayList<>();

	private Scene scene;
	private Pane pane;
	AnimationTimer gameLoop;

	@Override
	public void start(Stage primaryStage) {
		pane = new Pane();
		BackgroundSize backgroundSize = new BackgroundSize(Parametre.WIDTH, Parametre.HEIGHT, false, false, true, true);
		BackgroundImage imageParkView= new BackgroundImage(new Image("./view/park.png"),
	            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		pane.setBackground(new Background(imageParkView));
		scene = new Scene(this.pane, WIDTH, HEIGHT, Color.WHITE);

		primaryStage.setTitle("Pigeon Square");
		primaryStage.setScene(scene);
		primaryStage.show();

		addElement();
		initMouse();
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
		            } else if (food.checkEaten(allPigeon)) {
		            	iterator.remove();
		            	pane.getChildren().remove(food);
		            	System.out.println("Food Eaten");
		            }
				}
				/*if (allFood.isEmpty()) {
					addFood();
				}*/
			}
		};
		gameLoop.start();
	}

	public void initMouse() {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            double x = event.getX();
	            double y = event.getY();
	            addFood(x - Parametre.FOOD_SIZE, y - Parametre.FOOD_SIZE);
	        }
	    });
	}

	public void addElement(){
		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			addFood();
		}

		for (int i = 0; i < Parametre.NB_PIGEON; i++) {
			addPigeon();
		}
		//addPigeon();
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

	public void addFood(double x, double y) {
		Image imageFood = new Image("./view/food.png");
		Food food = new Food(pane, x, y, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imageFood);
		allFood.add(food);
		Thread threadFood = new Thread(food);
		threadFood.start();
	}

}