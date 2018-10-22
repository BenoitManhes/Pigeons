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

    private Vector<Pigeon> pigeonVect;
    private Vector<Food> foodVect;

    private Stage primaryStage;
    private Scene scene;
    private Group root;
    private Group pigeonsGroup;

    @Override
    public void start(Stage primaryStage) {
        // gestion des images
        Class<?> clazz = this.getClass();

        InputStream inputPark = clazz.getResourceAsStream("/view/park.png");
        imagePark = new Image(inputPark,WIDTH,HEIGHT,false,true);
        ImageView imageParkView = new ImageView(imagePark);

        InputStream inputPigeon = clazz.getResourceAsStream("/view/pigeon.png");
        imagePigeon = new Image(inputPigeon,Parametre.PIGEON_SIZE,Parametre.PIGEON_SIZE,false,true);



        this.root = new Group();
        this.root.getChildren().addAll(imageParkView);
        this.scene = new Scene(this.root, WIDTH, HEIGHT, Color.WHITE);

        init();
        this.root.getChildren().addAll(this.pigeonsGroup);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pigeon Square");
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();

    }


    public void init(){
        this.foodVect = new Vector<>();
        this.pigeonVect = new Vector<>();
        this.pigeonsGroup = new Group();

        for (int i = 0; i < Parametre.NB_PIGEON; i++) {
            Pigeon pi = new Pigeon();

            this.pigeonVect.add(pi);
            this.pigeonsGroup.getChildren().addAll(pi.getImageView());
        }


    }


}