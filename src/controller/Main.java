package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
import java.util.concurrent.TimeUnit;

public class Main extends Application {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		launch(args);
		for(Thread thread : threads) {
			thread.stop();
		}
	}

	private final int HEIGHT = Parametre.HEIGHT;
	private final int WIDTH = Parametre.WIDTH;

	ArrayList<Food> allFood = new ArrayList<>();
	ArrayList<Pigeon> allPigeon = new ArrayList<>();
	static ArrayList<Thread> threads = new ArrayList<>();

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
					
					if (food.checkEaten(allPigeon)) {
						iterator.remove();
						pane.getChildren().remove(food);
						System.out.println("Food Eaten");
					}
					if ( food.isaDetruire()) {
						iterator.remove();
						pane.getChildren().remove(food);
						System.out.println("food detruite");
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
				if(event.getButton() == MouseButton.PRIMARY) {
					double x = event.getX();
					double y = event.getY();
					addFood(x - Parametre.FOOD_SIZE, y - Parametre.FOOD_SIZE);
				}else if(event.getButton() == MouseButton.SECONDARY) {
					double x = event.getX();
					double y = event.getY();
					fearPigeons(x,y);
				}
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
		threads.add(threadPigeon);
	}

	public void addFood() {
		Image imageFood = new Image("./view/food.png");
		Food food = new Food(pane, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imageFood);
		allFood.add(food);
		Thread threadFood = new Thread(food);
		threadFood.start();
		threads.add(threadFood);
	}

	public void addFood(double x, double y) {
		Image imageFood = new Image("./view/food.png");
		Food food = new Food(pane, x, y, Parametre.PIGEON_SIZE, Parametre.PIGEON_SIZE, imageFood);
		allFood.add(food);
		Thread threadFood = new Thread(food);
		threadFood.start();
		threads.add(threadFood);
	}

	public void fearPigeons(double x, double y) {
		//get all pigeons
		for(Pigeon pigeon : allPigeon) {
			pigeon.setFear(x,y);
			//change image to pigeonFeared
		}		
	}

}